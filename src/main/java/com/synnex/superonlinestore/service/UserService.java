package com.synnex.superonlinestore.service;

import com.synnex.superonlinestore.dao.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Author: Dustin Li
 * @Date: 11/15/18 16:53
 */
@Service
public interface UserService {

    User save(User user);

    User validateLogin(String loginid,String pwd);

    public User findByloginid(String loginid);

    int updateStatusByloginid(String loginid, String status);

    int updatePwdByloginid(String loginid, String pwd);

    int updateInfoByloginid(String loginid, String info);

}

