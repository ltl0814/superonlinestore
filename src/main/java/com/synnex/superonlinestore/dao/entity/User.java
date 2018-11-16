package com.synnex.superonlinestore.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * @Author: Dustin Li
 * @Date: 11/15/18 16:05
 */


@Data
@NoArgsConstructor
@Access(AccessType.FIELD)
@Entity
@Table(name = "db_user" )
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    private String username;

    private String loginid;

    private String pwd;

    private String status ="1";

    @Email
    private String email;

    private String info;


    public User(String username, String loginid, String pwd, @Email String email) {
        this.username = username;
        this.loginid = loginid;
        this.pwd = pwd;
        this.email = email;
    }
}
