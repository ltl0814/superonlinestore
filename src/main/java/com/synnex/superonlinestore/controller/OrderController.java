package com.synnex.superonlinestore.controller;

import com.synnex.superonlinestore.dao.entity.DetailResult;
import com.synnex.superonlinestore.dao.entity.Order;
import com.synnex.superonlinestore.service.OrderService;
import com.synnex.superonlinestore.util.JsonEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(value = "订单管理", description = "订单的的查改")
public class OrderController {
    @Autowired
    OrderService orderService;

    @ApiOperation(value = "查询某个用户的订单")
    @RequestMapping(value = "/public/api/user/{uid}/order", method = {RequestMethod.GET})
    public JsonEntity userOrder(@PathVariable("uid")int uid){
        log.info("查询用户："+uid+"的订单");
        List<DetailResult> list = orderService.userOrder(uid);
        return new JsonEntity("查询成功",true,list);
    }

    @ApiOperation(value = "查询所有订单")
    @RequestMapping(value = "/public/api/backend/order", method = {RequestMethod.GET})
    public JsonEntity allOrder(){
        log.info("查询所有订单");
        List<DetailResult> list = orderService.allOrder();
        return new JsonEntity("查询成功",true,list);
    }

    @ApiOperation(value = "修改订单状态")
    @RequestMapping(value = "/public/api/backend/order/{uid}/{oid}", method = {RequestMethod.PUT})
    public JsonEntity editOrder(@PathVariable("oid")int oid,@PathVariable("uid")int uid){
        log.info("修改订单状态");
        orderService.editOrder(oid, uid);
        return new JsonEntity("修改成功",true);
    }

    @ApiOperation(value = "通过订单号查询订单")
    @GetMapping("/pulic/api/user/order/{oid}")
    public JsonEntity findOrder(@PathVariable("oid") int oid){
        log.info("查询订单编号："+oid);
        List<DetailResult> order = orderService.findOrder(oid);
        return new JsonEntity("查询成功",true,order);
    }

    @ApiOperation("删除订单")
    @DeleteMapping("/public/api/user/{oid}/order")
    public JsonEntity deleteOrder(@PathVariable("oid")int oid){
        log.info("删除订单号："+oid);
        orderService.deleteOrder(oid);
        return new JsonEntity("删除成功",true);
    }
}
