package com.alibaba.game.test.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.game.utils.FileUtils;

public class TestReadTxt {

    public static String path_read = "D:\\t\\test\\tdata\\6_26_rank1000.txt";

    public static void main(String[] args) {

        File f = new File(path_read);

        long start = System.currentTimeMillis();

        System.out.println("准备读取" );
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            int length = 0;
            String line;
            FileUtils.writeStr("insert into star_ltzb_1000_user values ", "D:\\t\\test\\testsql.txt");
            int index = 0;
            while ((line = reader.readLine()) != null) {
                if (line != null && !line.isEmpty()) {
                    index++;
                    String str = "(" + line + ")";
                    if (index % 5000 == 0) {
                        str += ";";
                        FileUtils.writeStr(str, "D:\\t\\test\\testsql.txt");
                        FileUtils.writeStr("insert into star_ltzb_1000_user values ", "D:\\t\\test\\testsql.txt");
                    } else {
                        str += ",";
                        FileUtils.writeStr(str, "D:\\t\\test\\testsql.txt");
                    }

                }
                if (index % 1000 == 0) {
                    System.out.println(index);
                }

            }
            fis.close();
            long end = System.currentTimeMillis();
            //文件写入成功
            System.out.println("文件读取完毕，耗时：" + (end - start) + "ms, 读取行数：" + index);
        } catch (IOException e) {
        }

        long end = System.currentTimeMillis();
        System.out.println("写入完成，耗时：" + (end - start) + "ms");
    }
}
