package com.alibaba.game.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author binlong.lbl
 * @description: TCP客户端
 * @data: 11:09 2018/12/11
 */
public class TestClient {

    /**
     * Socket客户端
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Map<String,Object> map = new HashMap<String, Object>();
        //map.put("secretKey","PbnCSrFyahdKlyf74etlEy9");
        //map.put("secretKey","MxsSucBGjhAjWWGkbTv7");
        map.put("secretKey","PbnCSrFyahdKlyf74etlEy9");
        map.put("type","QueryUserRank");
        //map.put("uid",123232);
        //map.put("UserToken","5c14a161e1cb33677feaace7");

        String json = JSONObject.toJSONString(map) + "\n";
        //SocketUtils.send("30.97.216.5", 5980, json);
        //SocketUtils.send("127.0.0.1", 8989, json);
        //SocketUtils.send("47.101.59.99", 8000, json);
        //SocketUtils.send("47.101.71.174", 8000, json);
        //SocketUtils.send("30.97.216.13", 5980, json);
        //SocketUtils.send("47.100.124.146", 19327, json);
        //SocketUtils.send("106.14.150.81", 10950, json);
        //SocketUtils.send("30.97.196.11", 9297, json);
        String rs = SocketUtils.send("30.97.216.5", 10140, json);
        //SocketUtils.send("30.97.216.13", 6620, json);

        JSONArray gameData = JSON.parseArray(rs);

        //SECf9d2502fd9c6ff5c592f3fef3e7b38cb00d202c909b94d6fad0d0de0d61c1efd
        //https://oapi.dingtalk.com/robot/send?access_token=5182b279463b1de0e051031790206c51836e04867e5d0ec44dab042533d09abc
        long end = System.currentTimeMillis();
        System.out.println("total cost " + (end - start) + "ms");
    }

    /**
     * 判断是否断开连接，断开返回true,没有返回false
     * @param socket
     * @return
     */
    public static Boolean isServerClose(Socket socket){
        try{
            socket.sendUrgentData(0xFF);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        }catch(Exception se){
            se.printStackTrace();
            return true;
        }
    }

    /**
     * 发送数据，发送失败返回false,发送成功返回true
     * @param csocket
     * @param message
     * @return
     */
    public Boolean Send(Socket csocket,String message){
        try{
            PrintWriter out = new PrintWriter(csocket.getOutputStream(), true);
            out.println(message);
            return true;
        }catch(Exception se){
            se.printStackTrace();
            return false;
        }
    }

    private static void reciver(Socket socket) throws IOException {

        boolean hasReciver = false;
        while (!hasReciver) {
            //获取一个输入流，接收服务端的信息
            InputStream inputStream=socket.getInputStream();
            //包装成字符流，提高效率
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            //缓冲区
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String info="";
            //临时变量
            String temp=null;
            while((temp=bufferedReader.readLine())!=null){
                info+=temp;
                System.out.println("客户端接收服务端发送信息："+info);
            }

            if (!info.isEmpty()) {
                hasReciver = true;
            }
            //关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
        }

    }
}
