package com.synnex.superonlinestore.controller;

import com.synnex.superonlinestore.dao.entity.DetailResult;
import com.synnex.superonlinestore.service.CartService;
import com.synnex.superonlinestore.util.JsonEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "购物车管理", description = "购物车的增删查改结算")
public class CartController {

    @Autowired
    CartService cartService;

    @ApiOperation(value = "加入购物车")
    @RequestMapping(value = "/public/api/user/{uid}/cart/{gid}", method = {RequestMethod.POST})
    public JsonEntity addToCart(@PathVariable("uid")int uid, @PathVariable("gid")int gid,int qty) {
        log.info("加入商品:"+gid+"到购物车");
        cartService.addToCart(uid,gid,qty);
        return new JsonEntity("加入购物成功",true);
    }

    @ApiOperation(value = "修改商品数量")
    @RequestMapping(value = "/public/api/user/{uid}/cart/{gid}", method = {RequestMethod.PUT})
    public JsonEntity editCount(@PathVariable("uid")int uid,@PathVariable("gid") int gid,int newCount) {
        log.info("修改商品："+gid+"数量为："+newCount);
        cartService.editCount(uid, gid, newCount);
        DetailResult result = cartService.queryCart(uid);
        return new JsonEntity("修改商品数量成功",true,result);
    }


    @ApiOperation(value = "删除单项商品")
    @RequestMapping(value = "/public/api/user/{uid}/cart/{gid}", method = {RequestMethod.DELETE})
    public JsonEntity deletaOneFromCart(@PathVariable("uid")int uid,@PathVariable("gid")int gid) {
        log.info("删除商品："+gid);
        cartService.deletaOneFromCart(uid,gid);
        return new JsonEntity("删除成功",true);
    }

    @ApiOperation(value = "删除全部商品")
    @RequestMapping(value = "/public/api/user/{uid}/cart", method = {RequestMethod.DELETE})
    public JsonEntity deleteAllFromCart(@PathVariable("uid")int uid) {
        log.info("删除全部商品");
        cartService.deleteAllFromCart(uid);
        return new JsonEntity("删除成功",true);
    }


    @ApiOperation(value = "结算")
    @RequestMapping(value = "/public/api/user/{uid}/cart/list", method = {RequestMethod.GET})
    public JsonEntity payCart(@PathVariable("uid")int uid) {
        log.info("结算");
        boolean result = cartService.payCart(uid);
        if (result) {
            return new  JsonEntity("结算成功",true);
        } else {
            return new  JsonEntity("库存不足",false);
        }

    }

    @ApiOperation(value = "查询购物车")
    @RequestMapping(value = "/public/api/user/{uid}/cart", method = {RequestMethod.GET})
    public JsonEntity queryCart(@PathVariable("uid")int uid) {
        log.info("查询购物车");
        DetailResult result = cartService.queryCart(uid);
        return new JsonEntity("查询成功",true,result);

    }
}
