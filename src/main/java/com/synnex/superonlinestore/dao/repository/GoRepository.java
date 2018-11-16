package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Db_Go;
import com.synnex.superonlinestore.dao.entity.GoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoRepository extends JpaRepository<Db_Go, GoPK> {
}
