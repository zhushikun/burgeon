package com.lwrs.app.controller.job;

import com.lwrs.app.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobController {

    @Autowired
    private WxService wxService;

    /**
     * 公众号 交互access_token
     * appToken 2个小时失效； 提前10分钟 刷新一下
     * @return
     */
    @Scheduled(initialDelay = 0, fixedRate = 1000 * 6600)
    public void refreshAccToken(){
        wxService.refreshGzhAppToken();
    }


}
