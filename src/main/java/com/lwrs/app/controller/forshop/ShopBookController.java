package com.lwrs.app.controller.forshop;

import com.lwrs.app.domain.dto.resp.BaseResp;
import com.lwrs.app.domain.dto.resp.ShopViewBookResp;
import com.lwrs.app.service.ShopOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

/**
 * 商户后台
 */
@Controller
@RequestMapping("/backstage")
public class ShopBookController {
    @Autowired
    private ShopOwnerService shopOwnerService;

    /**
     * 首页
     * @return
     */
    @GetMapping("/home")
    public String checkAccess(){
        return "backstage/backstage-home";
    }

    /**
     * 登录验证
     * @param alias
     * @param code
     * @return
     */
    @GetMapping("/access-check")
    @ResponseBody
    public BaseResp accessCheck(@RequestParam("alias") String alias, @RequestParam("code")String code){
        return shopOwnerService.accessCheck(alias, code);
    }

    /**
     * 获取预定列表
     * @return
     */
    @GetMapping("/ajax/book-list")
    @ResponseBody
    public ShopViewBookResp getBookList(){
        return shopOwnerService.getBookList();
    }
}
