package com.synnex.superonlinestore.dao.repository;

import com.synnex.superonlinestore.dao.entity.Goods;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: kobef
 * @Date: 11/15/18 17:54
 * @Description:
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> , JpaSpecificationExecutor<Goods> {

    public List<Goods> findAllByGidIn(List<Integer> GidList);


    public List<Goods> queryAllByStatus(String status);

    @Modifying
    @Query(value = "update db_goods set status = 0 where gid = ?1",nativeQuery = true)
    public int saleOutProductByGid(Integer gid);

    @Query(value = "select * from db_goods order by create_time desc limit 0,12",nativeQuery = true)
    public List<Goods> getRecentGoods();

    @Query(value = "select * from db_goods where title like CONCAT('%',?1,'%') ",nativeQuery = true)
    public List<Goods> getGoodsByName( String name);

    @Query(value = "select * from db_goods order by stock limit 0,12",nativeQuery = true)
    public List<Goods> getGoodsByStock();
    
    Goods findByGid(int gid);
}
