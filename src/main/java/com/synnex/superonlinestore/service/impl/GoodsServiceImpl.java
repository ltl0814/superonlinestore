package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.Goods;
import com.synnex.superonlinestore.dao.repository.GoodsRepository;
import com.synnex.superonlinestore.service.GoodsService;
import com.synnex.superonlinestore.util.JsonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public JsonEntity getAllGoods(Pageable pageable) {
       Page<Goods> page=goodsRepository.findAll(pageable);
       JsonEntity jsonEntity=new JsonEntity("查询全部商品成功",true);
       jsonEntity.setData(page);
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

    @Override
    public JsonEntity getRecentGoods() {
        List<Goods> goodsList=goodsRepository.getRecentGoods();
        return new JsonEntity("查询最新商品列表前十成功",true,goodsList);
    }


    @Override
    public JsonEntity getByLikeName(String name) {
        /**
         *
         * 功能描述: 根据商品名字模糊查询商品
         *
         * @param: [name]模糊查询名字
         * @return: com.synnex.superonlinestore.util.JsonEntity
         * @auther: kobef
         * @date: 11/20/18 14:51
         */
        return new JsonEntity("根据商品名字模糊查询商品成功",
                true , goodsRepository.getGoodsByName(name));
    }

    @Override
    public JsonEntity getHotGoods() {
        return new JsonEntity("查询最热商品前10条成功",
                true,goodsRepository.getGoodsByStock());
    }


}
