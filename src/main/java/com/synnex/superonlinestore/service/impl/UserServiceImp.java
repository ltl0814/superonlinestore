package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.User;
import com.synnex.superonlinestore.dao.repository.UserRepository;
import com.synnex.superonlinestore.service.UserService;
import com.synnex.superonlinestore.util.Md5SaltTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Dustin Li
 * @Date: 11/15/18 16:53
 */
@Component
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public User save(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPwd(Md5SaltTool.getEncryptedPwd(user.getPwd()));
        return userRepository.save(user);
    }

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
                if(count<3){
                    redisTemplate.opsForValue().set(user.getUsername(),++count,10, TimeUnit.MINUTES);
                    return false;
                }else {
                    return false;
                }
            }else {
                redisTemplate.opsForValue().set(user.getUsername(),++count,10,TimeUnit.MINUTES);
                return false;
            }
        }
    }

    @Override
    public User findByloginid(String loginid) {
        return null;
    }

    @Override
    public int updateStatusByloginid(String loginid, String status) {
        return 0;
    }

    @Override
    public int updatePwdByloginid(String loginid, String pwd) {
        return 0;
    }

    @Override
    public int updateInfoByloginid(String loginid, String info) {
        return 0;
    }
}
