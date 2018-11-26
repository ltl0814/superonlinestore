package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
      goods.setPrice(7);
      goods.setTitle("烤鸡腿");
      goods.setDetail("很好恰");
      goods.setStatus("1");
      goods.setStock(60);
     Goods goods1= goodsRepository.save(goods);
     System.out.println();
    }
    @Test
    public void findDESC(){
       List<Goods> goodsList= goodsRepository.getRecentGoods();
        System.out.println();
    }

    @Test
    public void  Like(){
        List<Goods> goodsList=goodsRepository.getGoodsByName("蜜辣");
        System.out.println();
    }
    @Test
    public void test22(){
        System.out.println(goodsRepository.findByGid(1));
    }

    @Test
    public void orderByStock(){
        List<Goods> goodsList=goodsRepository.getGoodsByStock();
        System.out.println();
    }

    @Test
    public void testQueryOnsales(){
        //List<Goods> goodsList = goodsRepository.findAllByStatus("0");
        List<Goods> goodsList = goodsRepository.queryAllByStatus("1");
        assertEquals(6,goodsList.size());
    }
}