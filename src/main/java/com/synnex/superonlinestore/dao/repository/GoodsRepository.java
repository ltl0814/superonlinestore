package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entiry.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: kobef
 * @Date: 11/15/18 17:54
 * @Description:
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
    
}
