package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.CartPK;
import com.synnex.superonlinestore.dao.entity.Db_Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Db_Cart, CartPK> {
}
