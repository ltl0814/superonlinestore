package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @Author: Dustin Li
 * @Date: 11/15/18 16:30
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    //通过ID查询用户
    User findByloginid(String loginid);

    //通过UID查询用户
    User findByUid(int uid);

    //通过loginid更改用户状态
    @Transactional
    @Modifying
    @Query(value = "update db_user set status=?2 where loginid=?1",nativeQuery = true)
    int updateStatusByloginid(String loginid, String status);

    //通过用户id修改用户密码
    @Transactional
    @Modifying
    @Query(value = "update db_user set pwd=?2 where loginid=?1",nativeQuery = true)
    int updatePwdByloginid(String loginid, String pwd);

    //通过用户id修改用户的信息
    @Transactional
    @Modifying
    @Query(value = "update db_user set username=?2,status=?3,email=?4 where loginid=?1",nativeQuery = true)
    int updateUserByloginid(String loginid, String username,String status,String email);

}
