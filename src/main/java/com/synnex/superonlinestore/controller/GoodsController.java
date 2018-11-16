package com.synnex.superonlinestore.controller;

import com.synnex.superonlinestore.service.GoodsService;
import com.synnex.superonlinestore.util.JsonEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.synnex.superonlinestore.dao.entiry.Goods;

import java.util.List;

/**
 * @Auther: kobef
 * @Date: 11/16/18 17:15
 * @Description:
 */
@RestController
@Api(value = "商品管理", description = "curd")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    //后台查询全部商品
    @ApiOperation(value = "后台查询全部商品", produces = "application/json")
    @GetMapping("/public/api/backend/goods")
    public JsonEntity getAllGoods(){
        return goodsService.getAllGoods();
    }

    //后台添加商品
    @ApiOperation(value = "后台添加商品", produces = "application/json")
    @PostMapping("/public/api/backend/goods")
    public JsonEntity addGoods(Goods goods){
        return goodsService.addGoods(goods);
    }

    //后台查询单个商品
    @ApiOperation(value = "后台查询单个商品", produces = "application/json")
    @GetMapping("/public/api/backend/goods/{gid}")
    public JsonEntity getGoods(@PathVariable("gid") Integer gid){
        return goodsService.findone(gid);
    }

    //后台编辑单个商品
    @ApiOperation(value = "后台商品编辑", produces = "application/json")
    @PutMapping("/public/api/backend/goods")
    public JsonEntity setGoods(Goods goods){
        return goodsService.saveGoods(goods);
    }

    //查询商品列表，通过LIST
    @ApiOperation(value = "根据gIdList查询商品列表", produces = "application/json")
    @GetMapping("/public/api/goods")
    public JsonEntity GetGoodsListByGid(@RequestParam List<Integer> gIdList){
        return goodsService.findAllByGidList(gIdList);
    }
}
