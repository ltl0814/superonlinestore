package com.synnex.superonlinestore.controller;

import com.synnex.superonlinestore.dao.entity.User;
import com.synnex.superonlinestore.service.UserService;
import com.synnex.superonlinestore.util.JsonEntity;
import com.synnex.superonlinestore.util.VerifyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Dustin Li
 * @Date: 11/16/18 15:17
 */
@Api(value = "用户Api", description = "用户注册登陆修改密码等")
@RestController
@RequestMapping("/public/api")
@Slf4j
public class UserController {
    private static final String LockStatus ="2";
    @Autowired
    UserService userServiceImp;
    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation(value = "用户登录", produces = "application/json")
    @PostMapping("/user/auth")
    public JsonEntity userLogin(@RequestParam String loginId, @RequestParam String pwd,@RequestParam String verifyCode, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JsonEntity je = null;
        User user = userServiceImp.findByloginid(loginId);
        log.info("进入用户登录验证");
        if (null!=user){
            if (LockStatus.equals(user.getStatus())){
                return je = new JsonEntity("账号已冻结！还有："+redisTemplate.getExpire(user.getUsername(), TimeUnit.MINUTES)+"分钟解封！",false);
            }else {
                Boolean flag = userServiceImp.validateLogin(loginId,pwd,user);
                if (flag==true){
                    session.setAttribute(user.getLoginid(),user);
                    return je = new JsonEntity("登录成功",true,user);
                }else {
                    return je = new JsonEntity("登录失败",false,"密码错误！连续错误三次将冻结账号10分钟");

                }
            }
        }else {
            return je = new JsonEntity("登录失败",false,"账号不存在！");
        }
    }

    @ApiOperation(value = "用户注册",produces = "application/json")
    @PostMapping("/user")
    public JsonEntity userRegist(@Valid User user, BindingResult result) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JsonEntity je;
        if (result.hasErrors()){
            List<String> errlist = new ArrayList<>();
            List<ObjectError> ls=result.getAllErrors();
            for (ObjectError error:ls) {
                   errlist.add(error.getDefaultMessage());
            }
            return je = new JsonEntity("注册失败",errlist);
        }
        User u = userServiceImp.findByloginid(user.getLoginid());
        if (null!=u){
           return je = new JsonEntity("注册失败",false,"账号已存在！");
        }else {
            User s = userServiceImp.save(user);
            if (null!=s){
            je = new JsonEntity("注册成功",true,"创建成功");
            je.setData(s);
            return je;
            }else {
                return je = new JsonEntity("创建失败",false);
            }
        }
    }

    @ApiOperation(value = "注册loginId重复校验",produces = "application/json")
    @GetMapping("/user/{loginid}")
    public JsonEntity registChecking(@PathVariable("loginid") String loginid) {
        JsonEntity je;
        log.info("loginId:::"+loginid);
        User u = userServiceImp.findByloginid(loginid);
        if (null!=u){
            je = new JsonEntity("用户名已存在",false,null);
        }else{
            je = new JsonEntity("用户名可用",true,null);
        }
        return je;
    }

    @ApiOperation(value = "修改密码",produces = "application/json")
    @PostMapping("/user/{loginId}/pwd")
    public JsonEntity updatePwd(@PathVariable String loginId,@RequestParam String oldPwd,@RequestParam String newPwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = userServiceImp.findByloginid(loginId);
        if (null!=user){
            String status = user.getStatus();
            if (status.equals(LockStatus)){
                return new JsonEntity("账号处于冻结状态",false);
            }else {
                return userServiceImp.updatePwdByloginid(loginId,user.getPwd(),oldPwd,newPwd);
            }
        }else {
            return new JsonEntity("账号不存在！",false);
        }

    }

    @ApiOperation(value = "修改用户信息",produces = "application/json")
    @PutMapping("/user/update")
    public JsonEntity updateEmail(User user,HttpSession session){
        return userServiceImp.updateUserByloginid(user,session);
    }

    @ApiOperation(value = "用户退出",produces = "application/json")
    @GetMapping("/user/out")
    public JsonEntity loginOut(@RequestParam String loginId,HttpSession session){
        JsonEntity je;
        User user = userServiceImp.findByloginid(loginId);
        if (null!=user){
            userServiceImp.deleteSession(loginId,session);
            return je=new JsonEntity("退出成功",true);
        }else {
            return je=new JsonEntity("非法用户",false);
        }
    }

    @ApiOperation(value = "获取session中的用户信息",produces = "application/json")
    @GetMapping("/user/session")
    public JsonEntity getUserFromSession(HttpSession session){
        JsonEntity je;
        List<Object> list = new ArrayList<>();
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            list.add(session.getAttribute(key));
        }
        return je=new JsonEntity("获取成功！",true,list);
    }

    @ApiOperation("生成验证码")
    @GetMapping("/user/getCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception{
        HttpSession session=request.getSession();
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.createImage();
        //将验证码存入Session方便以后的验证
        session.setAttribute("imageCode",objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    @ApiOperation("验证码的校验")
    @PostMapping("/user/validate")
    public JsonEntity validateVerifyCode(HttpSession session,String verifyCode){
        JsonEntity je;
        String imageCode = (String) session.getAttribute("imageCode");
        log.info(verifyCode);
        log.info(imageCode);
        if (verifyCode.equalsIgnoreCase(imageCode)){
            je = new JsonEntity("验证码正确！",true);
            return je;
        }else {
            je = new JsonEntity("错误的验证码！",false);
            return je;
        }
    }

    @ApiOperation("测试全局异常")
    @GetMapping("/exception/test")
    public void testException(){
        System.out.println(100/0);
    }

    @ApiOperation("获取所有用户")
    @GetMapping("/user/all")
    public List<User> getAllUsers(){
        List<User> list;

        list = userServiceImp.getAllUsers();

        return list;
    }
}



