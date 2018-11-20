package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.CartPK;
import com.synnex.superonlinestore.dao.entity.Db_Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartRepository  extends JpaRepository<Db_Cart, CartPK> {
    Db_Cart findByUidAndGid(int uid,int gid);
    List<Db_Cart> findAllByUid(int uid);
    int deleteByUidAndGid(int uid,int gid );
    int deleteByUid(int uid);
}
