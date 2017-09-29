package com.lwrs.app.service.job;

import com.lwrs.app.service.WxService;
import com.lwrs.app.utils.UnionTransThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobTrigger {

    @Autowired
    private WxService wxService;

    /**
     * 公众号 交互access_token
     * appToken 2个小时失效； 提前10分钟 刷新一下
     * @return
     */
//    @Scheduled(initialDelay = 0, fixedRate = 1000 * 6600)
    public void refreshGZHAccToken(){
        run("refreshGZHAccToken", ()-> wxService.refreshGzhAppToken());
    }


    private void run(String methodName ,Runnable runnable){
        UnionTransThreadFactory.of("JobTrigger#" + methodName , null)
            .newThread(runnable)
            .start();
    }

}
