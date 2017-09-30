package com.lwrs.app.controller;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.service.UserService;
import com.lwrs.app.utils.ServletHelper;
import com.lwrs.app.utils.UserIdMask;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param code
     * @param state  跳转url
     * @return
     * @throws IOException
     */
    @GetMapping("/ajax/oauth")
    @ResponseBody
    public String oauthLogin(@RequestParam("code") String code,
        @RequestParam(value = "state", required = false) String state) throws IOException {
        if(StringUtils.isEmpty(code)){
            ServletHelper.sendRedirect("/");
        }
        Long userId = userService.wxLoginOrRegister(code);
        if(null == userId){
            ServletHelper.sendRedirect("/");
        }

        ServletHelper.addCookie(Constants.USER_COOKIE_NAME, UserIdMask.maskUserId(userId));
        if(StringUtils.isEmpty(state)){
            ServletHelper.sendRedirect("/");
        }else {
            ServletHelper.sendRedirect(state);
        }
        return "";
    }
}
