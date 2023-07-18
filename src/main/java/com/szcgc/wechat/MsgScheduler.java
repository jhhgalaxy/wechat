package com.szcgc.wechat;

import com.szcgc.wechat.smart.entity.Unsendmessage;
import com.szcgc.wechat.smart.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chenxinli
 * @Date: 2020/4/26 9:50
 * @Description: 定时发送信息
 */
@Component
public class MsgScheduler {
    private ScheduledExecutorService executor;

    @Autowired
    private MessageService messageService;

    public MsgScheduler() {
        initialize();
    }

    public void destroy() {
        executor.shutdownNow();
        executor = null;
    }

    public void initialize() {
        // executor=Executors.newScheduledThreadPool(2);
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public boolean isRunning() {
        return executor != null && !executor.isShutdown();
    }

    public void sendMsg() {
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                //// TODO: 2020/4/26 遍历待发列表+发送信息+将待发列表转移到已发列表
                List<Unsendmessage> list = messageService.send();
                messageService.transferUnsendToSent(list);
            }
        }, 10, 5, TimeUnit.MINUTES);

    }
}
