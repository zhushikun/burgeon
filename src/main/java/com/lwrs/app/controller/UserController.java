package com.lwrs.app.controller;

import com.lwrs.app.domain.dto.RespBaseDto;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.service.impl.UserServiceImpl;
import com.lwrs.app.utils.UserLoginContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return RespBaseDto.of(RespCode.INVALID_PARAM).toString();
        }
        Integer userId = UserLoginContext.getUserId();
        Validate.notNull(userId, "uploadAvatar, user not login");
        RespBaseDto resp = userService.uploadAvatar(file, userId);
        return resp.toString();
    }

    /**
     * 修改手机号，alias
     * @return
     */
    @PostMapping("basic/modify")
    @ResponseBody
    public String modifyBasicInfo(@RequestParam(value = "phone", required = false) String phone,
        @RequestParam(value = "alias", required = false) String alias){
        if(StringUtils.isEmpty(phone) && StringUtils.isEmpty(alias)){
            return RespBaseDto.of(RespCode.INVALID_PARAM).toString();
        }
        return userService.modifyBasicInfo(phone, alias).toString();
    }

}
