package com.lwrs.app.controller;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.service.UserService;
import com.lwrs.app.service.WxService;
import com.lwrs.app.utils.CookieHelper;
import com.lwrs.app.utils.UserIdMask;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("oauth")
    @ResponseBody
    public String oauthLogin(@RequestParam("code") String code,
        @RequestParam(value = "state", required = false) String state, HttpServletResponse response) throws IOException {
        if(StringUtils.isEmpty(code)){
            response.sendRedirect("/");
        }
        Long userId = userService.wxLoginOrRegister(code);
        if(null == userId){
            response.sendRedirect("/");
        }

        CookieHelper.addCookie(response, Constants.USER_COOKIE_NAME, UserIdMask.maskUserId(userId));
        if(StringUtils.isEmpty(state)){
            response.sendRedirect("/");
        }else {
            response.sendRedirect(state);
        }
        return "";
    }
}
