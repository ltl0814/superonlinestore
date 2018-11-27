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

    @Override
<<<<<<< HEAD
   @Cacheable(value = "goods",key = "'findAll'+#pageable.pageSize+#pageable.pageNumber")
    public Page<Goods> getAllGoods(Specification specification, Pageable pageable) {
       Page<Goods> page=goodsRepository.findAll(specification,pageable);
=======
  // @Cacheable(value = "goods",key = "'findAll'"+#pageable.)
    public Page<Goods> getAllGoods(Pageable pageable) {
       Page<Goods> page=goodsRepository.findAll(pageable);
>>>>>>> f008373b84fa17a98c783182c433f52e7ad78ccd
        return page;
    }

    @Override
    @CacheEvict(value = "goods",allEntries = true,beforeInvocation = true)
    public Goods addGoods(Goods goods) {
        Goods goods1=goodsRepository.save(goods);
        return goods1;
    }

    @Override
    @CacheEvict(value = "goods",allEntries = true,beforeInvocation = true)
    public Goods saveGoods(Goods goods) {
        Goods goods1=goodsRepository.saveAndFlush(goods);
        return goods1;
    }

    @Override
    @CacheEvict(value = "goods",allEntries = true,beforeInvocation = true)
    public void deleteGoods(Integer gid){
        goodsRepository.deleteById(gid);
    }

    @Override
    @Cacheable(value = "goods",key = "'findByGidList'+#GidList")
    public List<Goods> findAllByGidList(List<Integer> GidList) {
        List<Goods> goodsList=goodsRepository.findAllByGidIn(GidList);
        return goodsList;
    }

    @Override
<<<<<<< HEAD
    @Cacheable(value = "goods",key = "'findById'+#gid")
    public Goods findone(int gid) {
        Goods goods=goodsRepository.findByGid(gid);
=======
   // @Cacheable(value = "goods",key = "#gId")
    public Goods findone(Integer gId) {
        Goods goods=goodsRepository.findByGid(gId);
>>>>>>> f008373b84fa17a98c783182c433f52e7ad78ccd
        return goods;
    }

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

    @Override
    @Cacheable(value = "goods",key = "'Mosthot'")
    public List<Goods> getHotGoods() {
        return goodsRepository.getGoodsByStock();
    }


}
