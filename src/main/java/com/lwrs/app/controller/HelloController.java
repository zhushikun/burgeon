package com.lwrs.app.controller;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.db.entity.UserDB;
import com.lwrs.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by drjr on 17-7-4.
 */
@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/index"})
    public String index(Model model){
        return "index";
    }


    @GetMapping(value = {"/","/home"})
    public String home(Model model){
        return "home";
    }




    @RequestMapping("/my")
    public String my(Model map, HttpSession session){
        return "my/my";
    }

}
