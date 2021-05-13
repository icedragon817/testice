package com.alibaba.game.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author binlong.lbl
 * @description: Socket工具类
 * @data: 15:13 2018/12/17
 */
public class SocketUtils {

    private static Logger logger = LoggerFactory.getLogger(SocketUtils.class);

    public static String send(String host, int port, String params) {

        String result = null;
        try {
            //创建Socket对象
            Socket socket=new Socket(host,port);
            //根据输入输出流和服务端连接
            //获取一个输出流，向服务端发送信息
            OutputStream outputStream=socket.getOutputStream();

            //将输出流包装成打印流
            PrintWriter printWriter=new PrintWriter(outputStream);

            printWriter.print(params);
            printWriter.flush();

            //通道打开延迟100毫秒
            Thread.sleep(100);
            //关闭输出流
            socket.shutdownOutput();
            long start = System.currentTimeMillis();
            //获取一个输入流，接收服务端的信息
            InputStream inputStream=socket.getInputStream();
            //包装成字符流，提高效率
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            //缓冲区
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String info="";
            //临时变量
            String temp=null;

            //System.out.println(System.currentTimeMillis() - start + "ms");
            //System.out.println(bufferedReader.readLine());
            //System.out.println(System.currentTimeMillis() - start + "ms");
            while((temp=bufferedReader.readLine())!=null){
                info+=temp;
            }

            result = info;
            printWriter.close();
            outputStream.close();
            inputStream.close();
            bufferedReader.close();
            socket.close();

            System.out.println("客户端接收服务端发送信息："+info);

        } catch (IOException e) {
            logger.error("IO异常", e);
        } catch (InterruptedException e) {
            logger.error("线程异常", e);
        }
        return result;
    }
}
