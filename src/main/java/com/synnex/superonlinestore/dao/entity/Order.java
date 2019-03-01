package com.synnex.superonlinestore.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "db_order")
public class Order  {

    @Id
    @Column( name = "oid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;
    @Column( name = "uid")
    private int uid;
    private Timestamp createTime;
    @Column( name = "status")
    private String status;
    @Column( name = "sum")
    private double sum;
    @Column( name = "recipients")
    private String recipients;

    public Order(){

    }
    public Order(int uid, Timestamp createTime, String status, double sum, String recipients) {
        this.uid = uid;
        this.createTime = createTime;
        this.status = status;
        this.sum = sum;
        this.recipients = recipients;
    }
}
