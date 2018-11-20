package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

    @Autowired
    OrderRepository orderRepository;
    @Test
    public void testOrder(){
        Order order = orderRepository.findByOidAndUid(133,5715);
        System.out.println(order);
    }
}
