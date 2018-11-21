package com.synnex.superonlinestore.dao.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsDetail implements Serializable{
    private String title;
    private int gid;
    private int count;
    private double suatotal;
    private String pic;

    public GoodsDetail(String title, int gid, int count, double suatotal, String pic) {
        this.title = title;
        this.gid = gid;
        this.count = count;
        this.suatotal = suatotal;
        this.pic = pic;
    }
}
