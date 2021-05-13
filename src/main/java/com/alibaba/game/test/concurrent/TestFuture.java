package com.alibaba.game.test.concurrent;

import java.util.concurrent.FutureTask;

public class TestFuture {

    public static void main(String[] args) {
        TestCall call = new TestCall();
        FutureTask futureTask = new FutureTask(call);
        try {
            new Thread(futureTask).start();
            int index = 0;
            while (index < 6) {
                index++;
                System.out.println("已等待" + index + "秒");
                if (futureTask.isDone()) {
                    break;
                }
                Thread.sleep(1000);
            }
            if (!futureTask.isDone()) {
                System.out.println("尚无返回，结束 ");
                futureTask.cancel(true);
            } else {
                System.out.println("调用成功，结果 " + futureTask.get());
            }
            System.out.println("futureTask 是否结束？" + futureTask.isCancelled());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
