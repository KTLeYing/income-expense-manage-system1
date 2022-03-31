package com.mzl.incomeexpensemanagesystem1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;

/**
 * @ClassName :   TestController
 * @Description: TODO
 * @Author: v_ktlema
 * @CreateDate: 2022/3/30 13:59
 * @Version: 1.0
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(Model m){
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        return "hello";  //视图重定向hello.jsp
    }
    @GetMapping("/hello1")
    public String hello1(Model m){
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        return "/hello";  //视图重定向hello.jsp
    }

}
