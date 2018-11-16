package com.synnex.superonlinestore.Util;

import lombok.Data;

@Data
public class JsonEntity {
    private String msg;
    private boolean status;
    private Object data;

    public JsonEntity(String msg, boolean status) {
        this.msg = msg;
        this.status = status;
    }

    public JsonEntity(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }
}
