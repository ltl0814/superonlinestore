package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Autowired
    GoRepository goRepository;
    @Test
    public void testOrder(){
        goRepository.deleteByOid(141);

    }
}
