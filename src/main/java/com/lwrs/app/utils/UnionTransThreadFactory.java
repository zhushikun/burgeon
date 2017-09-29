package com.lwrs.app.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class UnionTransThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    private Integer priority;

    public static UnionTransThreadFactory of(String name, Integer priority){
        return new UnionTransThreadFactory(name, priority);
    }

    private UnionTransThreadFactory(String name, Integer priority) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
            Thread.currentThread().getThreadGroup();

        name = StringUtils.isBlank(name) ? "pool-" + poolNumber.getAndIncrement() : name;
        namePrefix = name + "-thread-";
        this.priority = priority;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
            namePrefix + threadNumber.getAndIncrement(),
            0);
        if (t.isDaemon())
            t.setDaemon(false);

        priority = (null == priority) ? Thread.NORM_PRIORITY :
            priority > Thread.MAX_PRIORITY || priority < Thread.MIN_PRIORITY ?
                (priority > Thread.MAX_PRIORITY ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY) : priority;
        t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
