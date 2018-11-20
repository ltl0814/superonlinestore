<<<<<<< HEAD
package com.synnex.superonlinestore.controller;

import com.synnex.superonlinestore.util.JsonEntity;
import com.synnex.superonlinestore.util.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lambert Zhang
 * @create 2018-11-16 17:10
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 捕获全局系统异常(系统异常则跳转指定页面)
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonEntity javaExceptionHandler(Exception e){
        Map map = new HashMap();
        map.put("code","000000");
        map.put("errMsg",e.getMessage());
        return new JsonEntity(null,false,map);
}

    /**
     * 捕获全局自定义异常（自定义异常则抛出信息）
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public JsonEntity myErrorHandler(MyException me){
        Map map = new HashMap();
        map.put("code",me.getCode());
        map.put("errMsg",me.getErrMsg());
        return new JsonEntity(null,false,map);
    }
}
=======
//package com.synnex.superonlinestore.controller;
//
//import com.synnex.superonlinestore.util.JsonEntity;
//import com.synnex.superonlinestore.util.MyException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Lambert Zhang
// * @create 2018-11-16 17:10
// */
//@ControllerAdvice
//public class MyControllerAdvice {
//
//    /**
//     * 捕获全局系统异常(系统异常则跳转指定页面)
//     */
//    @ResponseBody
//    @ExceptionHandler(value = Exception.class)
//    public JsonEntity javaExceptionHandler(Exception e){
//        Map map = new HashMap();
//        map.put("code","000000");
//        map.put("errMsg",e.getMessage());
//        return new JsonEntity(null,false,map);
//    }
//
//    /*
//      * 捕获全局自定义异常（自定义异常则抛出信息）
//     */
//    @ResponseBody
//    @ExceptionHandler(value = MyException.class)
//    public JsonEntity myErrorHandler(MyException me){
//        Map map = new HashMap();
//        map.put("code",me.getCode());
//        map.put("errMsg",me.getErrMsg());
//        return new JsonEntity(null,false,map);
//    }
//}
>>>>>>> 36bf361d6989c02e3ec2a2b4b024f52722f66322
