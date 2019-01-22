package com.synnex.superonlinestore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dustin Li
 * @create 2018-11-16 17:10
 */
@ControllerAdvice
@Slf4j
public class MyControllerAdvice {
    //跳转的指定页面
    private final String errorHtml="error";

    /**
     * 捕获全局系统异常(系统异常则跳转指定页面)
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ModelAndView javaExceptionHandler(Exception e){
        e.printStackTrace();
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("error");
       modelAndView.addObject("msg",e.getMessage());
       return modelAndView;
    }
}
