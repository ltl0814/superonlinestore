package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.User;
import com.synnex.superonlinestore.dao.repository.UserRepository;
import com.synnex.superonlinestore.service.UserService;
import com.synnex.superonlinestore.util.Md5SaltTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: Dustin Li
 * @Date: 11/15/18 16:53
 */
@Component
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        try {
            user.setPwd(Md5SaltTool.getEncryptedPwd(user.getPwd()));
            userRepository.save(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User validateLogin(String loginid, String pwd) {
    return null;
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
