package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: kobef
 * @Date: 11/15/18 18:13
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest {
    @Autowired
    GoodsRepository goodsRepository;
    @Test
    public void addGoods(){
      Goods goods=  new Goods();
      goods.setPrice(5.2);
      goods.setTitle("蜜辣鸡翅");
      goods.setDetail("很舒服");
      goods.setStatus("1");
      goods.setStock(50);
     Goods goods1= goodsRepository.save(goods);
    }
    @Test
    public void test22(){
        System.out.println(goodsRepository.findByGid(1));
    }

}