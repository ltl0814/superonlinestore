package com.synnex.superonlinestore.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lambert Zhang
 * @create 2018-11-16 17:06
 * @description 自定义异常处理类
 */
@Data
@NoArgsConstructor
public class MyException extends RuntimeException {
    public int code;
    public String errMsg;
    public MyException(int code,String errMsg){
        this.code =  code ;
        this.errMsg = errMsg ;
    }
}
