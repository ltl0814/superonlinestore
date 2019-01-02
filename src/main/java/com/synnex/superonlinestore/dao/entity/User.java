package com.synnex.superonlinestore.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "名字不能为空！")
    private String username;

    @Length(min = 4,message = "用户ID长度至少为4位！")
    private String loginid;

    @Length(min = 6,message = "密码长度至少为6位！")
    private String pwd;

    private String status ="1";

    @Email(message = "邮件格式不正确！")
    private String email;

    private String info;


    public User(String username, String loginid, String pwd, @Email String email) {
        this.username = username;
        this.loginid = loginid;
        this.pwd = pwd;
        this.email = email;
    }
}
