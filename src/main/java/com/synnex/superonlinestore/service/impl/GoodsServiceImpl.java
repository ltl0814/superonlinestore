package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entiry.Goods;
import com.synnex.superonlinestore.dao.repository.GoodsRepository;
import com.synnex.superonlinestore.service.GoodsService;
import com.synnex.superonlinestore.util.JsonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: kobef
 * @Date: 11/16/18 11:20
 * @Description:
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    @Override
    public JsonEntity getAllGoods() {
       List<Goods> goodsList=goodsRepository.findAll();
       JsonEntity jsonEntity=new JsonEntity("查询全部商品成功",true);
       jsonEntity.setData(goodsList);
        return jsonEntity;
    }

    @Override
    public JsonEntity addGoods(Goods goods) {
        Goods goods1=goodsRepository.save(goods);
        JsonEntity jsonEntity=new JsonEntity("添加成功",true);
        jsonEntity.setData(goods1);
        return jsonEntity;
    }

    @Override
    public JsonEntity saveGoods(Goods goods) {
        Goods goods1=goodsRepository.saveAndFlush(goods);
        JsonEntity jsonEntity=new JsonEntity("修改成功",true);
        jsonEntity.setData(goods1);
        return jsonEntity;
    }

    @Override
    public JsonEntity deleteGoods(Integer gid) {
        goodsRepository.deleteById(gid);
        return new JsonEntity("删除成功",true);
    }

    @Override
    public JsonEntity findAllByGidList(List<Integer> GidList) {
        List<Goods> goodsList=goodsRepository.findAllByGidIn(GidList);
        JsonEntity jsonEntity=new JsonEntity("查询商品列表成功",true);
        jsonEntity.setData(goodsList);
        return jsonEntity;
    }

    @Override
    public JsonEntity findone(Integer gId) {
        Optional<Goods> optional =goodsRepository.findById(gId);
        JsonEntity jsonEntity=new JsonEntity("查询某个商品成功",true);
        Goods goods=optional.get();
        jsonEntity.setData(goods);
        return jsonEntity;
    }


}
