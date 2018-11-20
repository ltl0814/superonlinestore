package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Goods;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: kobef
 * @Date: 11/15/18 17:54
 * @Description:
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
    public List<Goods> findAllByGidIn(List<Integer> GidList);

    @Query(value = "select * from db_goods order by create_time desc limit 0,10",nativeQuery = true)
    public List<Goods> getRecentGoods();

    @Query(value = "select * from db_goods where title like CONCAT('%',?1,'%') ",nativeQuery = true)
    public List<Goods> getGoodsByName( String name);
    
}
