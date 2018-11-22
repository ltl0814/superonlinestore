package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Db_Cart;
import com.synnex.superonlinestore.dao.entity.Goods;
import com.synnex.superonlinestore.dao.entity.Order;
import com.synnex.superonlinestore.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    @Autowired
    OrderService orderService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void testOrder(){
        List<Db_Cart> list = cartRepository.findAllByUid(5715);

    }
}
