package com.synnex.superonlinestore.service;

import com.synnex.superonlinestore.dao.entity.User;
import com.synnex.superonlinestore.util.JsonEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Author: Dustin Li
 * @Date: 11/15/18 16:53
 */
@Service
public interface UserService {

    //创建用户
    User save(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    //登录验证
    Boolean validateLogin(String loginid,String pwd,User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    //通过id查询用户
    User findByloginid(String loginid);

    //修改用户状态
    int updateStatusByloginid(String loginid, String status);

    //修改密码
    JsonEntity updatePwdByloginid(String loginid, String oldwd, String newpwd) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    //修改用户信息
    JsonEntity updateUserByloginid(User user,HttpSession session);

    void deleteSession(String loginId,HttpSession session);

    List<User> getAllUsers();
}

