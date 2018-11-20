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
        Goods goods;
        //判断库存
        boolean flag = true;
        for (GoodsDetail good: goodsDetails) {
            goods = goodsRepository.findByGid(good.getGid());
            if(good.getCount() > goods.getStock()){
                flag = false;
                break;
            }
        }
        if(flag){
            //生成订单
            Order order = new Order(uid,new Timestamp(System.currentTimeMillis()),"已支付",result.getSum(),result.getRecipients());
            order =  orderRepository.save(order);
            //修改中间表并更新库存
            for (GoodsDetail good: goodsDetails) {
                Db_Go go = new Db_Go(good.getGid(),order.getOid(),good.getCount(),good.getSuatotal());
                goRepository.save(go);
                goods = goodsRepository.findByGid(good.getGid());
                goods.setStock(goods.getStock() - good.getCount());
                goodsRepository.save(goods);
            }
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
        List<GoodsDetail> goodsDetails = new ArrayList<>();
        double sum = 0;
        for(int i = 0; i < list.size();i++){
            Db_Cart db_cart = list.get(i);
            Goods goods = goodsRepository.findByGid( db_cart.getGid() );
            GoodsDetail detail = new GoodsDetail(goods.getTitle(),goods.getGid(),db_cart.getCount(),db_cart.getSubtotal(),goods.getPic());
            goodsDetails.add(detail);
            sum += db_cart.getSubtotal();
        }
        DetailResult result = new DetailResult(goodsDetails,sum,user.getUsername(),"目前在购物车",0);
        return result;
    }
}
