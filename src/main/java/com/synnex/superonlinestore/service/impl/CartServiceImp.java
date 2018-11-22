package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.*;
import com.synnex.superonlinestore.dao.repository.*;
import com.synnex.superonlinestore.service.CartService;
import com.synnex.superonlinestore.util.JsonEntity;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    GoRepository goRepository;
    @Override
    public void addToCart(int uid, int gid) {
        Db_Cart cart = cartRepository.findByUidAndGid(uid,gid);
        Goods goods = goodsRepository.findByGid(gid);
        if(cart == null){
            cart = new Db_Cart();
            cart.setUid(uid);
            cart.setGid(gid);
            cart.setCount(1);
            cart.setSubtotal(goods.getPrice());
        }else {
            cart.setCount(cart.getCount()+1);
            cart.setSubtotal(cart.getSubtotal()+goods.getPrice());
        }
        cartRepository.save(cart);
    }

    @Override
    public void editCount(int uid, int gid,int newCount) {
        Db_Cart cart = cartRepository.findByUidAndGid(uid,gid);
        Goods goods = goodsRepository.findByGid(gid);
        if (newCount < 1) cartRepository.deleteByUidAndGid(uid, gid);
        else {
            cart.setCount(newCount);
            cart.setSubtotal(newCount * goods.getPrice());
            cartRepository.save(cart);
        }
    }



    @Override
    public void deletaOneFromCart(int uid ,int gid) {
        cartRepository.deleteByUidAndGid(uid,gid);
    }

    @Override
    public void deleteAllFromCart(int uid) {
        cartRepository.deleteByUid(uid);
    }

    @Override
    public boolean payCart(int uid) {
        DetailResult result = queryCart(uid);
        List<GoodsDetail> goodsDetails = result.getList();

        List<Integer> gids = new ArrayList<>();
        for(GoodsDetail detail: goodsDetails )gids.add(detail.getGid());
        List<Goods> goods = goodsRepository.findAllByGidIn(gids);

        //判断库存
        boolean flag = true;
        for (int i = 0;i < goodsDetails.size();i++) {
            Goods good = goods.get(i);
            GoodsDetail goodsDetail = goodsDetails.get(i);
            if(goodsDetail.getCount() > good.getStock()){
                flag = false;
                break;
            }
        }

        if(flag){
            //生成订单
            Order order = new Order(uid,new Timestamp(System.currentTimeMillis()),"已支付",result.getSum(),result.getRecipients());
            order =  orderRepository.save(order);
            //修改中间表并更新库存
            List<Db_Go> goss = new ArrayList<>();
            List<Goods>  goodss = new ArrayList<>();
            for (int i = 0;i < goodsDetails.size();i++) {
                Goods good = goods.get(i);
                GoodsDetail goodsDetail = goodsDetails.get(i);
                Db_Go go = new Db_Go(good.getGid(),order.getOid(),goodsDetail.getCount(),goodsDetail.getSuatotal());
                good.setStock(good.getStock() - goodsDetail.getCount());
                goss.add(go);
                goodss.add(good);
            }
            goRepository.saveAll(goss);
            goodsRepository.saveAll(goodss);
            //清空购物车
            deleteAllFromCart(uid);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public DetailResult queryCart(int uid) {
        List<Db_Cart> list = cartRepository.findAllByUid(uid);
        User user = userRepository.findByUid(uid);
        List<Integer> gids = new ArrayList<>();
        for(Db_Cart db_cart: list )gids.add(db_cart.getGid());
        List<Goods> goods = goodsRepository.findAllByGidIn(gids);
        List<GoodsDetail> goodsDetails = new ArrayList<>();
        double sum = 0;
        for(int i = 0; i < list.size();i++){
            Db_Cart db_cart = list.get(i);
            Goods good = goods.get(i);
            GoodsDetail detail = new GoodsDetail(good.getTitle(),good.getGid(),db_cart.getCount(),db_cart.getSubtotal(),good.getPic(),good.getPrice());
            goodsDetails.add(detail);
            sum += db_cart.getSubtotal();
        }
        DetailResult result = new DetailResult(goodsDetails,sum,user.getUsername(),"目前在购物车",0);
        return result;
    }
}
