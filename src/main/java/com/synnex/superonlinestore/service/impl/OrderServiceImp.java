package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.Db_Go;
import com.synnex.superonlinestore.dao.entity.DetailResult;
import com.synnex.superonlinestore.dao.entity.Goods;
import com.synnex.superonlinestore.dao.entity.GoodsDetail;
import com.synnex.superonlinestore.dao.entity.Order;
import com.synnex.superonlinestore.dao.entity.User;
import com.synnex.superonlinestore.dao.repository.CartRepository;
import com.synnex.superonlinestore.dao.repository.GoRepository;
import com.synnex.superonlinestore.dao.repository.GoodsRepository;
import com.synnex.superonlinestore.dao.repository.OrderRepository;
import com.synnex.superonlinestore.dao.repository.UserRepository;
import com.synnex.superonlinestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
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
    @Autowired
    UserServiceImp userServiceImp;

    @Override
    public List<DetailResult> userOrder(int uid) {
        List<Order> orders = orderRepository.findAllByUid(uid);
        List<DetailResult> results = new ArrayList<>();
        helper(orders,results);
        return results;
    }

    @Override
    @Cacheable(value = "orders",key = "'allOrders'")
    public List<DetailResult> allOrder() {
        List<Order> orders = orderRepository.findAll();
        List<DetailResult> results = new ArrayList<>();
        helper(orders,results);
        return results;
    }

    @Override
    @CacheEvict(value = "orders",allEntries = true)
    public void editOrder(int oid, String status) {
        Order order = orderRepository.findByOid(oid);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public List<DetailResult> findOrder(int oid) {
        Order order = orderRepository.findByOid(oid);
        List<Order> orders = new ArrayList<>();
        List<DetailResult> results = new ArrayList<>();
        orders.add(order);
        helper(orders,results);
        return results;
    }

    @Override
    @CacheEvict(value = "orders",allEntries = true)
    public void deleteOrder(int oid) {
        goRepository.deleteByOid(oid);
        orderRepository.deleteById(oid);
    }

    public void helper(List<Order> orders,List<DetailResult> results){
        for (Order order:orders) {
            User user = userRepository.findByUid(order.getUid());
            List<GoodsDetail> goodsDetails = new ArrayList<>();
            List<Db_Go> gos = goRepository.findAllByOid(order.getOid());
            List<Integer> gids = new ArrayList<>();
            for(Db_Go db_go: gos ) {
                gids.add(db_go.getGid());
            }
            List<Goods> goods = goodsRepository.findAllByGidIn(gids);
            double sum = 0;
            for(int i = 0; i < gos.size(); i++){
                Db_Go go = gos.get(i);
                Goods good = goods.get(i);
                GoodsDetail detail = new GoodsDetail(good.getTitle(),good.getGid(),go.getCount(),go.getSubtotal(),good.getPic(),good.getPrice());
                goodsDetails.add(detail);
                sum += detail.getSuatotal();
            }
            Timestamp createTime = order.getCreateTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=sdf.format(createTime);
            DetailResult result = new DetailResult(goodsDetails,sum,user.getUsername(),order.getStatus(),order.getOid(),time);
            results.add(result);
        }
    }
}