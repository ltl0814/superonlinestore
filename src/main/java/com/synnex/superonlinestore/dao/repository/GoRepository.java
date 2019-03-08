package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Db_Go;
import com.synnex.superonlinestore.dao.entity.GoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface GoRepository extends JpaRepository<Db_Go, GoPK> {

    List<Db_Go> findAllByOid(int oid);

    Db_Go findByOidAndGid(int oid,int gid);

    @Transactional
    @Modifying
    @Query(value = "delete from db_go where oid=?1",nativeQuery = true)
    void deleteByOid(int oid);
}
