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

    User findByloginid(String loginid);

    @Transactional
    @Modifying
    @Query(value = "update db_user set status=?2 where loginid=?1",nativeQuery = true)
    int updateStatusByloginid(String loginid, String status);

    @Transactional
    @Modifying
    @Query(value = "update db_user set pwd=?2 where loginid=?1",nativeQuery = true)
    int updatePwdByloginid(String loginid, String pwd);

    @Transactional
    @Modifying
    @Query(value = "update db_user set info=?2 where loginid=?1",nativeQuery = true)
    int updateInfoByloginid(String loginid, String info);


}
