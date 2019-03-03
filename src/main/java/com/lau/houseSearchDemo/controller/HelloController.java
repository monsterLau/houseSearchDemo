package com.lau.houseSearchDemo.controller;

import com.lau.houseSearchDemo.domain.User;
import com.lau.houseSearchDemo.mapper.UserMapper;
import com.lau.houseSearchDemo.service.UserServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/success")
    public String toSuccess(){
        return "success";
    }

    @GetMapping("/fail")
    public String faild(){
        return "fail";
    }

    @GetMapping("/page")
    public String page(){
        return "page";
    }
}
