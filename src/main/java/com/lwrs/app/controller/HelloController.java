package com.lwrs.app.controller;

import com.lwrs.app.db.entity.User;
import com.lwrs.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by drjr on 17-7-4.
 */
@RestController
public class HelloController {


    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/hello")
    public List<User> hello() {
//        zkLockTest.testZkLock("/hahaha");


        return userService.getAllUser();
    }

}
