package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.User;
import com.synnex.superonlinestore.dao.repository.UserRepository;
import com.synnex.superonlinestore.service.UserService;
import com.synnex.superonlinestore.util.JsonEntity;
import com.synnex.superonlinestore.util.Md5SaltTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Dustin Li
 * @Date: 11/15/18 16:53
 */
@Component
public class UserServiceImp implements UserService {

    private static final int max = 2;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisTemplate redisTemplate;

    //添加用户
    @Override
    @CacheEvict(value = "users",allEntries = true)
    public User save(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPwd(Md5SaltTool.getEncryptedPwd(user.getPwd()));
        return userRepository.save(user);
    }

    //验证用户登录
    @Override
    public Boolean validateLogin(String loginid, String pwd, User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Boolean flag;
        flag = Md5SaltTool.validPassword(pwd,user.getPwd());
        if (flag){
            redisTemplate.opsForValue().set(user.getUsername(),0);
            return true;
        }else {
            Integer count = (Integer) redisTemplate.opsForValue().get(user.getUsername());
            if (null!=count){
                if(count==max){
                    userRepository.updateStatusByloginid(loginid,"2");
                    new Thread(()->{
                        try {
                            Thread.sleep(1000*60*10);
                            userRepository.updateStatusByloginid(loginid,"1");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    return false;
                }else {
                    redisTemplate.opsForValue().set(user.getUsername(),++count,10, TimeUnit.MINUTES);
                    return false;
                }
            }else {
                count=0;
                redisTemplate.opsForValue().set(user.getUsername(),++count,10,TimeUnit.MINUTES);
                return false;
            }
        }
    }

    //通过loginID查询用户
    @Override
    @CacheEvict(value = "users",allEntries = true)
    public User findByloginid(String loginid) {
        return userRepository.findByloginid(loginid);
    }

    //更改用户状态
    @Override
    @CacheEvict(value = "users",allEntries = true)
    public int updateStatusByloginid(String loginid, String status) {
        return userRepository.updateStatusByloginid(loginid,status);
    }

    //修改用户密码
    @Override
    @CacheEvict(value = "users",allEntries = true)
    public JsonEntity updatePwdByloginid(String loginId, String pwdInDb, String oldPwd, String newPwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Boolean flag = Md5SaltTool.validPassword(oldPwd,pwdInDb);
        if (flag){
            newPwd=Md5SaltTool.getEncryptedPwd(newPwd);
             userRepository.updatePwdByloginid(loginId,newPwd);
             return new JsonEntity("修改成功",true);
        }else {
             return new JsonEntity("修改失败！输入原密码不正确！",false);
        }
    }

    //修改用户信息
    @Override
    @CacheEvict(value = "users",allEntries = true)
    public JsonEntity updateUserByloginid(User user,HttpSession session) {
        int i = userRepository.updateUserByloginid(user.getLoginid(),
                                            user.getUsername(),
                                            user.getStatus(),
                                            user.getEmail());
        if (i==1){
            User u = userRepository.findByloginid(user.getLoginid());
            session.setAttribute(user.getLoginid(),u);
            return new JsonEntity("修改成功",true);
        }else {
            return new JsonEntity("修改失败",false);
        }
    }

    //去除用户的session
    @Override
    public void deleteSession(String loginId, HttpSession session) {
        session.removeAttribute(loginId);
    }

    //得到所有用户信息
    @Override
    @Cacheable(value = "users",key = "all")
    public List<User> getAllUsers(){
      return  userRepository.findAll();
    }
}
