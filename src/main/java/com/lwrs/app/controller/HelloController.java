package com.lwrs.app.controller;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.db.entity.UserDB;
import com.lwrs.app.service.impl.UserServiceImpl;
import com.lwrs.app.utils.UserLoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by drjr on 17-7-4.
 */
@Controller
public class HelloController {


    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = {"/","/home"})
    public String index(Model model){
//        List<User> userList = userService.getAllUser();
//        model.addAttribute("userList", userList);
        Integer userId = UserLoginContext.getUserId();
        model.addAttribute("name", (null == userId)? "" : userId);
        return "home";
    }


    @PostMapping("/loginPost")
    public @ResponseBody Map<String, Object> loginPost(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        UserDB userDB = new UserDB();
        userDB.setName(account);
        userDB.setPwd(password);
        // 设置session
        session.setAttribute(Constants.USER_KEY, 123123);
        session.setMaxInactiveInterval(30 * 60);

        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }


    @RequestMapping("/my")
    public String my(Model map, HttpSession session){
        UserDB userDB = (UserDB) session.getAttribute(Constants.USER_KEY);
        map.addAttribute("user", userDB);
        return "my/my";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(Constants.USER_KEY);
        return "redirect:/login";
    }


}
