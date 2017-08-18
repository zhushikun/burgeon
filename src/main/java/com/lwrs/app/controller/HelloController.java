package com.lwrs.app.controller;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.db.entity.User;
import com.lwrs.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by drjr on 17-7-4.
 */
@Controller
public class HelloController {


    @Autowired
    private UserService userService;

    @GetMapping(value = {"/","/home"})
    public String index(Model model){
        model.addAttribute("name", "index");
        return "home";
    }


    @PostMapping("/loginPost")
    public @ResponseBody Map<String, Object> loginPost(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (!"123456".equals(password)) {
            map.put("success", false);
            map.put("message", "密码错误");
            return map;
        }

        User user = new User();
        user.setName(account);
        user.setPwd(password);
        // 设置session
        session.setAttribute(Constants.SESSION_KEY, user);

        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }


    @RequestMapping("/my")
    public String my(Model map, HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_KEY);
        map.addAttribute("user", user);
        return "my/my";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(Constants.SESSION_KEY);
        return "redirect:/login";
    }


}
