package com.synnex.superonlinestore.dao.repository;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void testOrder(){
        ValueOperations<Integer,Integer> op = redisTemplate.opsForValue();
        op.set(1,1);
    }
}
