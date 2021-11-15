package com.robot.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Wuph
 * @Date: create in 2021/11/11/ 16:27
 * @Description
 */
@Controller
@RequestMapping("/guest")
public class GuestController {

    @GetMapping("/page/welcome-1.html")
    public String getWelcome(){
        return "page/welcome-1";
    }

    @GetMapping("/index")
    public String getIndex1(){
        return "index";
    }

    @GetMapping("/login")
    public String getLoginIndex(){
        return "page/login-1";
    }

}
