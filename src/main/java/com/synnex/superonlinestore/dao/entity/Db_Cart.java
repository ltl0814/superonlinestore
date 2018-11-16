package com.synnex.superonlinestore.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(CartPK.class)
@Table(name = "db_cart")
public class Db_Cart implements Serializable {
    @Id
    @Column(name = "uid")
    private int uid;
    @Id
    @Column(name = "gid")
    private int gid;
    @Column(name = "count")
    private int count;
    @Column(name = "subtotal")
    private double subtotal;

}
