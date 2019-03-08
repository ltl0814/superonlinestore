package com.synnex.superonlinestore.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DetailResult implements Serializable{
    private List<GoodsDetail> list;
    private double sum;
    private String  recipients;
    private String  status;
    private int oid;
    private String orderCreated;
    public DetailResult(){

    }

    public DetailResult(List<GoodsDetail> list, double sum, String recipients, String status, int oid,String orderCreated) {
        this.list = list;
        this.sum = sum;
        this.recipients = recipients;
        this.status = status;
        this.oid = oid;
        this.orderCreated = orderCreated;
    }
    public DetailResult(List<GoodsDetail> list, double sum, String recipients, String status, int oid) {
        this.list = list;
        this.sum = sum;
        this.recipients = recipients;
        this.status = status;
        this.oid = oid;
    }

}
