package com.alibaba.game.test.concurrent;

import java.util.concurrent.Callable;

public class TestCall implements Callable {
    @Override
    public String call() throws Exception {
        System.out.println("开始执行");
        Thread.sleep(5000);
        System.out.println("执行结束");
        return "success";
    }
}
