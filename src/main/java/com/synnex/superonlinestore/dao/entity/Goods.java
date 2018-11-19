package com.synnex.superonlinestore.dao.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Auther: kobef
 * @Date: 11/15/18 17:34
 * @Description: 商品实体类
 */
@Data
@Entity
@Table(name = "db_Goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid")
    private int gid;

    @Column(name = "price")
    private double price;

    /*
    * 商品名称
    */
    @Column(name = "title")
    private String title;

    /*
    * 商品详情
    * */
    @Column(name = "detail")
    private String detail;

    //库存
    @Column(name = "stock")
    private int stock;

    //商品上下架状态
    @Column(name = "status")
    private String status;

    //图片地址
    @Column(name = "pic")
    private String pic;

}
