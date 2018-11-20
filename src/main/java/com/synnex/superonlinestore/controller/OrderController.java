package com.synnex.superonlinestore.controller;

import com.synnex.superonlinestore.dao.entity.DetailResult;
import com.synnex.superonlinestore.service.OrderService;
import com.synnex.superonlinestore.util.JsonEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.jar.JarEntry;

@RestController
@Api(value = "订单管理", description = "订单的的查改")
public class OrderController {
    @Autowired
    OrderService orderService;

    @ApiOperation(value = "查询某个用户的订单")
    @RequestMapping(value = "/public/api/uer/{uid}/order", method = {RequestMethod.GET})
    public JsonEntity userOrder(@PathVariable("uid")int uid){
        List<DetailResult> list = orderService.userOrder(uid);
        return new JsonEntity("查询成功",true,list);
    }

    @ApiOperation(value = "查询所有订单")
    @RequestMapping(value = "/public/api/backend/order", method = {RequestMethod.GET})
    public JsonEntity allOrder(){
        List<DetailResult> list = orderService.allOrder();
        return new JsonEntity("查询成功",true,list);
    }

    @ApiOperation(value = "修改订单状态")
    @RequestMapping(value = "/public/api/backend/order/{uid}/{oid}", method = {RequestMethod.PUT})
    public JsonEntity editOrder(@PathVariable("oid")int oid,@PathVariable("uid")int uid){
        orderService.editOrder(oid, uid);
        return new JsonEntity("修改成功",true);
    }
}
