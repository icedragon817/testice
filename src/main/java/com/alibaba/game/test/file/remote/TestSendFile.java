package com.alibaba.game.test.file.remote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.game.test.HttpRequest;
import com.alibaba.game.test.TestG;

public class TestSendFile {
    public static void main(String[] args) {

        int [] ia = new int[3];
        int [] ib = new int[3];

        for (int i = 0;i<3; i++) {
            ia[i] = i+1;
            ib[i] = (i+1) * 2;
        }

        int[] c= new int[ia.length+ib.length];
        System.arraycopy(ia, 0, c, 0, ia.length);
        System.arraycopy(ib, 0, c, ia.length, ib.length);

        //File f = new File("D:\\t\\test1");
        File f = new File("D:\\worksplace\\qa_support\\qa_web\\js\\controller\\qa\\data_list.js");
        List<Map> ips = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            int length = 0;
            //加快写入效率，每次读取4096字节
            //byte[] b = new byte[409600];
            byte[] b = new byte[40960000];

            long start = System.currentTimeMillis();
            //fis.read(b);
            //int length;
            int total = 0;
            while((length = fis.read(b)) != -1) {
                total += length;
                byte [] wb = new byte[length];
                System.arraycopy(b, 0, wb, 0, length);
                //fos.write(b, 0, length);

                String rs = HttpRequest.sendPost("https://sh-mcd-video-collect.ejoy.com:10443/game-video/video/upload.do", wb);
                System.out.println("数据已写入 " + total);
            }
            //String rs = HttpRequest.sendPost("http://30.97.198.4:8087/game-video/video/upload.do", b);
            //String rs = HttpRequest.sendPost("http://30.97.198.4:8081/test/test/uploadFile.do", b);
            System.out.println(b.length + ", cost " + (System.currentTimeMillis() - start) + "ms");
            System.out.println(TestG.compress(b).length);
            //System.out.println(rs);
            long end = System.currentTimeMillis();
            fis.close();
            //文件写入成功
            System.out.println("文件读取完毕，耗时：" + (end - start) + "ms, 读取行数：" + ips.size());
        } catch (IOException e) {
        }
    }
}
