package com.lwrs.app.controller.forshop;

import com.lwrs.app.domain.dto.req.BackStageLoginReq;
import com.lwrs.app.domain.dto.resp.BaseResp;
import com.lwrs.app.domain.dto.resp.ShopViewBookResp;
import com.lwrs.app.service.ShopOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param loginReq
     * @return
     */
    @PostMapping("ajax/access-check")
    @ResponseBody
    public BaseResp accessCheck(@RequestBody() BackStageLoginReq loginReq){
        return shopOwnerService.accessCheck(loginReq.getAlias(), loginReq.getCode());
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
