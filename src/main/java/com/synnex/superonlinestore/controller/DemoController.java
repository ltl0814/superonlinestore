package com.synnex.superonlinestore.Controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/public/api")
public class DemoController {

    @RequestMapping("/test/{s}")
    public String test(@PathVariable String s){
        System.out.println(s);
        return s;
    }
}
