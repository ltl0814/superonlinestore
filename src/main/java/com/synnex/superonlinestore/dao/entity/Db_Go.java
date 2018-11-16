package com.synnex.superonlinestore.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(GoPK.class)
@Table(name = "db_go")
public class Db_Go implements Serializable {

    @Id
    @Column(name = "gid")
    private int gid;
    @Id
    @Column(name = "oid")
    private int oid;
    @Column(name = "count")
    private int count;
    @Column(name = "subtotal")
    private double subtotal;
}
