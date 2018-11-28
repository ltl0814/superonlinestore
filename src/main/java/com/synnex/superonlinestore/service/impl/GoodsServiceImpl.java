package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.Goods;
import com.synnex.superonlinestore.dao.repository.GoodsRepository;
import com.synnex.superonlinestore.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Auther: kobef
 * @Date: 11/16/18 11:20
 * @Description:
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;


    /**
     　　* @Description: 分页查询商品
     　  * @param specification 查询条件
       * @param pageable
     　　* @return org.springframework.data.domain.Page<com.synnex.superonlinestore.dao.entity.Goods>
     　　* @throws
     　　* @author kobef
     　　* @date 11/28/18 10:26
     　　*/
    @Override
    @Cacheable(value = "goods",key = "'findAll'+#pageable.pageSize+#pageable.pageNumber")
    public Page<Goods> getAllGoods(Specification specification, Pageable pageable) {
       Page<Goods> page=goodsRepository.findAll(specification,pageable);
        return page;
    }

    //添加商品
    @Override
    @CacheEvict(value = "goods",allEntries = true,beforeInvocation = true)
    public Goods addGoods(Goods goods) {
        Goods goods1=goodsRepository.save(goods);
        return goods1;
    }

    //修改商品信息
    @Override
    @CacheEvict(value = "goods",allEntries = true,beforeInvocation = true)
    public Goods saveGoods(Goods goods) {
        Goods goods1=goodsRepository.saveAndFlush(goods);
        return goods1;
    }

    //商品下架
    @Override
    @Transactional
    @CacheEvict(value = "goods",allEntries = true,beforeInvocation = true)
    public int deleteGoods(Integer gId){
        return goodsRepository.saleOutProductByGid(gId);
    }


    //根据gIdList查询获得商品list
    @Override
    @Cacheable(value = "goods",key = "'findByGidList'+#GidList")
    public List<Goods> findAllByGidList(List<Integer> gIdList) {
        List<Goods> goodsList=goodsRepository.findAllByGidIn(gIdList);
        return goodsList;
    }

    //根据gId查询获得商品
    @Override
    @Cacheable(value = "goods",key = "'findById'+#gId")
    public Goods findone(int gId) {
        Goods goods=goodsRepository.findByGid(gId);
        return goods;
    }

    //查询最新的12个商品
    @Override
    @Cacheable(value = "goods",key = "'latest'")
    public List<Goods> getRecentGoods() {
        List<Goods> goodsList=goodsRepository.getRecentGoods();
        return goodsList;
    }


    @Override
    @Cacheable(value = "goods",key = "'findByName'+#name")
    public List<Goods> getByLikeName(String name) {
        /**
         *
         * 功能描述: 根据商品名字模糊查询商品
         *
         * @param: [name]模糊查询名字
         * @return: com.synnex.superonlinestore.util.JsonEntity
         * @auther: kobef
         * @date: 11/20/18 14:51
         */
        return goodsRepository.getGoodsByName(name);
    }

    //查询最热的12个商品
    @Override
    @Cacheable(value = "goods",key = "'Mosthot'")
    public List<Goods> getHotGoods() {
        return goodsRepository.getGoodsByStock();
    }

}