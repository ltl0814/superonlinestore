package com.synnex.superonlinestore.service;

import com.synnex.superonlinestore.dao.entity.Goods;
import com.synnex.superonlinestore.util.JsonEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: kobef
 * @Date: 11/16/18 09:25
 * @Description: 商品服务
 */

public interface GoodsService {

    //获取全部商品列表
    public JsonEntity getAllGoods(Pageable pageable);

    //商品添加
    public JsonEntity addGoods(Goods goods);

    //商品编辑
    public JsonEntity saveGoods(Goods goods);

    //商品删除
    public JsonEntity deleteGoods(Integer gId);

    //查询商品列表
    public JsonEntity findAllByGidList(List<Integer> gIdList);

    //查询某个商品
    public JsonEntity findone(Integer gId);

    /**
     *
     * 功能描述: 按最新时间返回商品信息
     *
     * @param:
     * @return:
     * @auther: kobef
     * @date: 11/20/18 14:11
     */
    public JsonEntity getRecentGoods();


    public JsonEntity getByLikeName(String name);
}
