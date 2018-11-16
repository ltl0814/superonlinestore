package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.CartPK;
import com.synnex.superonlinestore.dao.entity.Db_Cart;
import com.synnex.superonlinestore.dao.entity.Db_Go;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {
    @Autowired
     GoRepository goRepository;

    @Autowired
    CartRepository cartRepository;
    @Test
    public void testGO(){
        Db_Go go = new Db_Go();
        go.setGid(1);
        go.setOid(1);
        go.setCount(1);
        go.setSubtotal(2.2);
        goRepository.save(go);
    }

    @Test
    public void testCar(){
        int a = cartRepository.deleteByUid(4);
        System.out.println(a);
    }
}
