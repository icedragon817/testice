package com.alibaba.game.test.socket;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.game.utils.FileUtils;

public class SocketClientThread implements Runnable {

    /**
     * 线程编号
     */
    protected int index;

    public static SocketClientThread getInstance(int index) {
        SocketClientThread thread = new SocketClientThread();
        thread.setIndex(++index);
        return thread;

    }

    @Override
    public void run() {

        System.out.println("当前线程池" + index);
        int writeIndex = 0;
        while (true) {

            Date now = new Date();
            writeIndex++;
            String msg = "当前时间： " + now + ", 当前线程池号： " + index + ", 当前写入index: " + writeIndex;
            msg += sendMsg();
            System.out.println(msg);
            FileUtils.writeStr(msg, "D://socket_test");

            if (writeIndex == 10) {
                break;
            }
        }
    }

    /**
     * Socket客户端
     */
    public String sendMsg() {
        long start = System.currentTimeMillis();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("secretKey","PbnCSrFyahdKlyf74etlEy9");
        //map.put("Type","QueryUserRank");
        //map.put("UserToken","5c14a161e1cb33677feaace7");

        String json = JSONObject.toJSONString(map);
        //SocketUtils.send("30.97.216.5", 5980, json);
        //SocketUtils.send("101.132.107.156", 8000, json);
        String rs = SocketUtils.send("47.101.59.99", 8000, json);
        //String rs = SocketUtils.send("127.0.0.1", 8989, json);

        long end = System.currentTimeMillis();
        System.out.println("result:" + rs + " , total cost " + (end - start) + "ms");

        return rs;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
