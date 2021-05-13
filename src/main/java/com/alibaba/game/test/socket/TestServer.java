package com.alibaba.game.test.socket;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSON;

import com.aliyuncs.utils.StringUtils;

/**
 * @author binlong.lbl
 * @description: TCP服务端
 * @data: 11:10 2018/12/11
 */
public class TestServer {

    public static int index = 0;

    public static int start = 0;
    public static String pCode = null;
    public static String sCode = null;
    public static String gCode = null;

    /**
           * Socket服务端
           */
    public static void main(String[] args) {

        try {
            String path = TestServer.class.getResource("TestServer.class").toString();
            if(path.contains("jar")) {
                path = path.replace("jar:file:/", "");
                path = path.substring(0, path.indexOf("testice.jar"));
                path = "/" +  path;
            } else {
                path = path.replace("file:/", "");
                path = path.substring(0, path.indexOf("Test"));
                path = "D:/test/";
                path = "D:/worksplace/testice/out/artifacts/testice_jar/";
            }

            System.out.println("基础地址："+path);

            Properties p = new Properties();
            p.load(new FileInputStream(path + "config.properties"));
            pCode = p.getProperty("pCode");
            sCode = p.getProperty("sCode");
            gCode = p.getProperty("gCode");
            start = Integer.parseInt(p.getProperty("start"));

            ServerSocket serverSocket = new ServerSocket(8989);
            System.out.println("服务端已启动，等待客户端连接..");
            while (true) {
                // 侦听并接受到此套接字的连接,返回一个Socket对象
                Socket socket = serverSocket.accept();
                SocketThread socketThread = new SocketThread(socket);
                socketThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //try {
        //    ServerSocket serverSocket=new ServerSocket(8888);
        //    System.out.println("服务端已启动，等待客户端连接..");
        //    //侦听并接受到此套接字的连接,返回一个Socket对象
        //    Socket socket=serverSocket.accept();
        //
        //    // 根据输入输出流和客户端连接
        //    //得到一个输入流，接收客户端传递的信息
        //    InputStream inputStream=socket.getInputStream();
        //    //提高效率，将自己字节流转为字符流
        //    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        //    //加入缓冲区
        //    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        //    String temp=null;
        //    String info="";
        //    while((temp=bufferedReader.readLine())!=null){
        //        info+=temp;
        //        System.out.println("已接收到客户端连接");
        //        System.out.println("服务端接收到客户端信息："+info+",当前客户端ip为："+socket.getInetAddress().getHostAddress());
        //    }
        //    //获取一个输出流，向服务端发送信息
        //    OutputStream outputStream=socket.getOutputStream();
        //    //将输出流包装成打印流
        //    PrintWriter printWriter=new PrintWriter(outputStream);
        //    printWriter.print("你好，服务端已接收到您的信息");
        //    printWriter.flush();
        //    //关闭输出流
        //    socket.shutdownOutput();
        //    //关闭相对应的资源
        //    printWriter.close();
        //    outputStream.close();
        //    bufferedReader.close();
        //    inputStream.close();
        //    socket.close();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }

    static class SocketThread extends Thread {
        private Socket socket;

        public SocketThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            // 根据输入输出流和客户端连接
            try {
                InputStream inputStream = socket.getInputStream();
                // 得到一个输入流，接收客户端传递的信息
                // 提高效率，将自己字节流转为字符流
                InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream);
                // 加入缓冲区
                BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
                String temp = null;
                String info = "";
                while ((temp = bufferedReader.readLine()) != null) {
                    info += temp;
                    System.out.println("已接收到客户端连接");
                    System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为："
                        + socket.getInetAddress().getHostAddress());
                }
                // 获取一个输出流，向服务端发送信息
                OutputStream outputStream = socket.getOutputStream();
                // 将输出流包装成打印流
                PrintWriter printWriter = new PrintWriter(outputStream);
                Double val = start + ((1 + Math.sin(index * Math.PI / 180)) * 100);
                Long ts = System.currentTimeMillis() / 1000;
                ts = ts - ts % 30 + index * 30;
                index++;
                Map map = new HashMap();
                Map data = new HashMap();
                data.put("total_number", val.intValue());
                data.put("ts", ts);
                if (StringUtils.isNotEmpty(pCode)) {
                    if (StringUtils.isNotEmpty(sCode)) {
                        Map sMap = new HashMap();
                        if (StringUtils.isNotEmpty(gCode)) {
                            Map gMap = new HashMap();
                            gMap.put(gCode, data);
                            sMap.put(sCode, gMap);
                        } else {
                            sMap.put(sCode, data);
                        }
                        map.put(pCode, sMap);
                    } else {
                        map.put(pCode, data);
                    }
                }
                //String data = "{\"P00000\":{\"P00001\":{\"total_number\":"+ val.intValue()+",\"ts\":"+ts+"}}}";
                String params = JSON.toJSONString(map);
                printWriter.print(params);

                Thread.sleep(1000);
                printWriter.flush();

                socket.shutdownOutput();// 关闭输出流

                //OutputStream os1 = socket.getOutputStream();
                //PrintWriter p1 = new PrintWriter(os1);
                //Thread.sleep(2000);
                //p1.print("over");
                //p1.flush();

                // 关闭相对应的资源
                bufferedReader.close();
                inputStream.close();

                printWriter.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
