package com.alibaba.game.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.RandomUtils;

public class TestSysc {

    /**
     * 线程池实例
     */
    private static ExecutorService pool;
    //初始化线程池
    static {
        ThreadFactory namedThreadFactory = Executors.defaultThreadFactory();
        pool = new ScheduledThreadPoolExecutor(10,namedThreadFactory);
    }

    public static void main(String[] args) {

        for (int i=0;i<10;i++) {

            Runnable command = new TestRunnable(i);
            pool.execute(command);
            //try {
            //    Future<?> future = pool.submit(command);
            //    future.get(2500, TimeUnit.MILLISECONDS);
            //} catch (TimeoutException e) {
            //    System.out.println("执行超时");
            //} catch (Exception e) {
            //    System.out.println("执行失败");
            //}
        }
    }

    static class TestRunnable implements Runnable {

        private int index;
        public TestRunnable (int index) {
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println("第" + index + "个任务开始执行");
            long start = System.currentTimeMillis();
            String result = "";
            try {
                result = doSomething(index);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("第" + index + "个任务执行完成,本次睡眠时间："+ result + " ms, 耗时：" + (end-start) + "ms");
        }

        public String doSomething(int index) throws ExecutionException, InterruptedException {
            ExecutorService threadpool = Executors.newCachedThreadPool();
            Future<String> futureData = threadpool.submit(new Callable<String>() {
                @Override
                public String  call() throws Exception {
                    int sleepMs = RandomUtils.nextInt(1000,2000);
                    try {
                        Thread.sleep(sleepMs);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return sleepMs+"";
                }
            });
            try {
                futureData.get(1500, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                System.out.println("任务"+ index + " 执行超时");
            }
            return futureData.get();
        }
    }
}
