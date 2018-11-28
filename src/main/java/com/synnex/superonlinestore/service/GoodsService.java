package com.synnex.superonlinestore.service;

import com.synnex.superonlinestore.dao.entity.Goods;
import com.synnex.superonlinestore.util.JsonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Auther: kobef
 * @Date: 11/16/18 09:25
 * @Description: 商品服务
 */

public interface GoodsService {

    //获取全部商品列表
    public Page<Goods> getAllGoods(Specification specification, Pageable pageable);

    //商品添加
    public Goods addGoods(Goods goods);

    //商品编辑
    public Goods saveGoods(Goods goods);

    //商品删除
    public int deleteGoods(Integer gId);

    //查询商品列表
    public List<Goods> findAllByGidList(List<Integer> gIdList);

    //查询某个商品
    public Goods findone(int gId);

    /**
     *
     * 功能描述: 按最新时间返回商品信息
     *
     * @param:
     * @return:
     * @auther: kobef
     * @date: 11/20/18 14:11
     */
    public List<Goods> getRecentGoods();


    public List<Goods> getByLikeName(String name);


    public List<Goods> getHotGoods();
}
