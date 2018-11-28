package com.synnex.superonlinestore.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: kobef
 * @Date: 11/15/18 17:15
 * @Description:
 */
@Data
public class JsonEntity implements Serializable {
    private String msg;
    private boolean status;
    private Object data;

    public JsonEntity(){

    }

    public JsonEntity(String msg, boolean status) {
        this.msg = msg;
        this.status = status;
    }

    public JsonEntity(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public JsonEntity(String msg,boolean status,Object data) {
        this.msg = msg;
        this.status = status;
        this.data = data;

    }
}
