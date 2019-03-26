package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findByOid(int oid);


    List<Order> findAllByUid(int uid);

    List<Order> findAll();


}
