package com.lwrs.app.controller;

import com.lwrs.app.domain.dto.BaseResp;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.service.UserService;
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

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 修改头像
     * @param file
     * @return
     */
    @PostMapping("/ajax/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return BaseResp.of(RespCode.INVALID_PARAM).toString();
        }
        Long userId = UserLoginContext.getUserId();
        Validate.notNull(userId, "uploadAvatar, user not login");
        BaseResp resp = userService.uploadAvatar(file, userId);
        return resp.toString();
    }

    /**
     * 修改手机号，alias
     * @return
     */
    @PostMapping("/ajax/basic/modify")
    @ResponseBody
    public String modifyBasicInfo(@RequestParam(value = "phone", required = false) String phone,
        @RequestParam(value = "alias", required = false) String alias){
        if(StringUtils.isEmpty(phone) && StringUtils.isEmpty(alias)){
            return BaseResp.of(RespCode.INVALID_PARAM).toString();
        }
        return userService.modifyBasicInfo(phone, alias).toString();
    }




//    @PostMapping("")

}
