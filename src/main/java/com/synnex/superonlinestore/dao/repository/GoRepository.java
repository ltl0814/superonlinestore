package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Db_Go;
import com.synnex.superonlinestore.dao.entity.GoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoRepository extends JpaRepository<Db_Go, GoPK> {
    List<Db_Go> findAllByOid(int oid);
    Db_Go findByOidAndGid(int oid,int gid);
}
