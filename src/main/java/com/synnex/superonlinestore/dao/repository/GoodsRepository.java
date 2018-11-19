package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: kobef
 * @Date: 11/15/18 17:54
 * @Description:
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
    public List<Goods> findAllByGidIn(List<Integer> GidList);
    
}
