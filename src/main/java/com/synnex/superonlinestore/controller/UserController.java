package com.synnex.superonlinestore.controller;

import com.synnex.superonlinestore.dao.entity.User;
import com.synnex.superonlinestore.service.impl.UserServiceImp;
import com.synnex.superonlinestore.util.JsonEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Dustin Li
 * @Date: 11/16/18 15:17
 */
@Api(value = "用户Api", description = "用户注册登陆修改密码等")
@RestController
@RequestMapping("/public/api")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation(value = "登录", produces = "application/json")
    @PostMapping("/user/auth")
    public JsonEntity UserLogin(@RequestParam String loginId,@RequestParam String pwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JsonEntity je;
        User user = userServiceImp.findByloginid(loginId);
        if (null!=user){
            if ("2"==user.getStatus()){
                return je = new JsonEntity("false","账号已冻结！还有："+redisTemplate.getExpire(user.getUsername(), TimeUnit.MINUTES)+"秒解封！");
            }else {
                Boolean flag = userServiceImp.validateLogin(loginId,pwd,user);
                if (flag==true){
                    return je = new JsonEntity("true",user);
                }else {
                    return je = new JsonEntity("false","密码错误！连续错误三次将冻结账号10分钟");
                }
            }
        }else {
            return je = new JsonEntity("false","账号不存在！");
        }
    }

    @ApiOperation(value = "注册",produces = "application/json")
    @PostMapping("/user")
    public JsonEntity UserRegist(User user) throws UnsupportedEncodingException ,NoSuchAlgorithmException{
        JsonEntity je;
        User u = userServiceImp.findByloginid(user.getLoginid());
        if (null!=u){
           return je = new JsonEntity("false","账号已存在！");
        }else {
            User s = userServiceImp.save(user);
            if (null!=s){
            je = new JsonEntity("true","创建成功");
            je.setData(s);
            return je;
            }else {
                return je = new JsonEntity("false","创建失败");
            }
        }
    }
}
