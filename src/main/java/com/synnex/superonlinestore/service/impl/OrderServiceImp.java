package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.*;
import com.synnex.superonlinestore.dao.repository.*;
import com.synnex.superonlinestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Cacheable(value = "goods2")
public class OrderServiceImp implements OrderService {
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
    public List<DetailResult> userOrder(int uid) {
        List<Order> orders = orderRepository.findAllByUid(uid);
        List<DetailResult> results = new ArrayList<>();
        helper(orders,results);
        return results;
    }

    @Override
    public List<DetailResult> allOrder() {
        List<Order> orders = orderRepository.findAll();
        List<DetailResult> results = new ArrayList<>();
        helper(orders,results);
        return results;
    }

    @Override
    public void editOrder(int oid, int uid) {
        Order order = orderRepository.findByOidAndUid(oid,uid);
        order.setStatus("已发货");
        orderRepository.save(order);
    }

    public void helper(List<Order> orders,List<DetailResult> results){
        for (Order order:orders) {
            User user = userRepository.findByUid(order.getUid());
            List<GoodsDetail> goodsDetails = new ArrayList<>();
            List<Db_Go> gos = goRepository.findAllByOid(order.getOid());
            List<Integer> gids = new ArrayList<>();
            for(Db_Go db_go: gos )gids.add(db_go.getGid());
            List<Goods> goods = goodsRepository.findAllByGidIn(gids);

            double sum = 0;
            for(int i = 0; i < gos.size(); i++){
                Db_Go go = gos.get(i);
                Goods good = goods.get(i);
                GoodsDetail detail = new GoodsDetail(good.getTitle(),good.getGid(),go.getCount(),go.getSubtotal(),good.getPic(),good.getPrice());
                goodsDetails.add(detail);
                sum += detail.getSuatotal();
            }
            DetailResult result = new DetailResult(goodsDetails,sum,user.getUsername(),"已支付",order.getOid());
            results.add(result);
        }
    }

}
