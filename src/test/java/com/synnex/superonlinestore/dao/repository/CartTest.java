package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.CartPK;
import com.synnex.superonlinestore.dao.entity.Db_Cart;
import com.synnex.superonlinestore.dao.entity.Db_Go;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {
    @Autowired
     GoRepository goRepository;

    @Autowired
    CartRepository cartRepository;
    @Test
    public void testGO(){

    }

    @Test
    public void testCar(){
        List<Db_Cart> list = cartRepository.findAllByUid(1);
        System.out.println(list.size());
    }
}
