package com.alibaba.game.test.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

import com.alibaba.game.test.socket.SocketClientThread;
import com.alibaba.game.utils.FileUtils;

public class TestForWrite {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ThreadFactory namedThreadFactory = Executors.defaultThreadFactory();
        ExecutorService pool = new ScheduledThreadPoolExecutor(50,namedThreadFactory);
        for (int i=0; i<1; i++) {
            pool.execute(ExecThread.getInstance(i));
        }
        pool.shutdown();
        while(true){
            if(pool.isTerminated()){
                System.out.println("所有的子线程都结束了！");
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时： " + (end-start) + "ms");
    }

    static class TestThread extends Thread {

        /**
         * 任务执行数量（服务器数量）
         */
        protected int index;

        public static TestThread getInstance(int index) {
            TestThread thread = new TestThread();
            thread.setIndex(++index);
            return thread;

        }

        @Override
        public void run() {
            System.out.println("当前线程池" + index);
            int writeIndex = 0;
            while (true) {

                Date now = new Date();
                if (now.getTime() % 1000 == index) {
                    writeIndex++;
                    String msg = "当前时间： " + now + ", 当前线程池号： " + index + ", 当前写入index: " + writeIndex;
                    System.out.println(msg);
                    FileUtils.writeStr(msg, "D://log_test");
                } else {
                    try {
                        Thread.sleep(1);
                        //this.notifyAll();
                        //this.wait(1);
                        yield();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (writeIndex == 10) {
                    break;
                }
            }
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
