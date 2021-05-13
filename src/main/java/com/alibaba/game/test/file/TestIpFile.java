package com.alibaba.game.test.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.alibaba.game.utils.FileUtils;

public class TestIpFile {

    public static void main(String[] args) {
        File f = new File("D:\\t\\test\\ips_cidr_chn.txt");
        List<Map> ips = new ArrayList<>();
        long start = System.currentTimeMillis();

        System.out.println("准备读取" );
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            int length = 0;
            //加快写入效率，每次读取4096字节
            byte[] b = new byte[4096];
            String line;
            FileUtils.writeStr("ip_end,ip_start,desc", "D:\\t\\test\\ips_cidr_chn.csv");
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String [] infos = line.split("\t");
                if (infos.length > 1) {
                    if (infos[0].matches("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}/[0-9]{1,2}")) {
                        index++;
                        FileUtils.writeStr(genIpv4Info(infos, index), "D:\\t\\test\\ips_cidr_chn.csv");
                    }else {
                    }
                }
            }
            fis.close();
            long end = System.currentTimeMillis();
            //文件写入成功
            System.out.println("文件读取完毕，耗时：" + (end - start) + "ms, 读取行数：" + ips.size());
        } catch (IOException e) {
        }

        long end = System.currentTimeMillis();
        System.out.println("IP导出完成，耗时：" + (end - start) + "ms");

    }

    private static String genIpv4Info(String [] infos, int index) {
        String cidr = infos[0];
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
        int mask = 32 - type;
        String [] verifyInfos = cidr.replaceAll("/.*", "").split("\\.");
        String result = "";
        try {
            long verifyNum = Long.parseLong(verifyInfos[0])<<24 | Long.parseLong(verifyInfos[1])<<16
                | Long.parseLong(verifyInfos[2])<<8 | Long.parseLong(verifyInfos[3]);

            String min = getMinIp(verifyNum, mask);
            String max = getMaxIp(verifyNum, mask);
            //String desc = getDesc(infos);
            result = max + "," + min + "," + index;
        } catch (Exception e) {
            System.out.println("error, cidr: " + cidr);
            e.printStackTrace();
        }

        return result;
    }

    private static String getDesc(String[] infos) {
        String result = "" ;
        String pattern = "[\\u4E00-\\u9FA5]+";
        for (String info : infos) {
            if (Pattern.matches(pattern, info)) {
                result += info;
            }
        }

        return result;
    }

    private static String getMaxIp(long verifyNum, int mask) {
        long s = verifyNum >> mask;
        long ipNum = s;
        for (int i=0; i<mask; i++) {
            ipNum = (ipNum << 1) + 1;
        }
        String rs = getStrFromIp(ipNum);
        return rs;
    }

    private static String getMinIp(long verifyNum, int mask) {
        long ipNum = (verifyNum >> mask << mask) + 1;
        String rs = getStrFromIp(ipNum);
        return rs;
    }

    private static String getStrFromIp(long ipNum) {
        long v1 = ipNum >> 24;
        long v2 = (ipNum ^ (v1 << 24)) >> 16;
        long v3 = (ipNum ^ (v1 << 24) ^ (v2 << 16)) >> 8;
        long v4 = (ipNum ^ (v1 << 24) ^ (v2 << 16) ^ (v3 << 8));
        return v1 + "." + v2 + "." + v3 + "." + v4;
    }
}
