package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.service.GoodsService;
import com.synnex.superonlinestore.util.JsonEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.synnex.superonlinestore.dao.entity.Goods;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: dustinl
 * @Date: 11/16/18 15:10
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceImplTest {
@Autowired
    GoodsService goodsService;
    @Test
    public void getAllGoods() {

      System.out.println();
    }

    @Test
    public void addGoods() {
        Goods goods=  new Goods();
        goods.setPrice(100);
        goods.setTitle("特色牛鞭");
        goods.setDetail("很舒服");
        goods.setStatus("1");
        goods.setStock(50);
        Goods G=goodsService.addGoods(goods);
        System.out.println();
    }

    @Test
    public void saveGoods() {
        Goods goods=  new Goods();
        goods.setGid(3);
        goods.setPrice(100);
        goods.setTitle("特色牛鞭");
        goods.setDetail("很舒服");
        goods.setStatus("1");
        goods.setStock(48);
      // JsonEntity goods1=goodsService.saveGoods(goods);
        System.out.println();
    }

    @Test
    public void deleteGoods() {

        System.out.println(goodsService.findone(1));
    }

    @Test
    public void findById(){
        List<Integer> list=new ArrayList<>();
        list.add(1);list.add(3);
      //  JsonEntity jsonEntity=goodsService.findAllByGidList(list);
        System.out.println();
    }

    @Test
    public void getOne(){
     Goods goods=goodsService.findone(1);
        System.out.println();
    }

    @Test
    public void testSaleOutProduct(){
        int i = goodsService.deleteGoods(3);
        System.out.println(i);
    }

}