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

public class TestForRead {

    public static void main(String[] args) {
        File f = new File("D:\\t\\test\\ips_cidr_chn.txt");
        List<Map> ips = new ArrayList<>();
        LinkedHashMap<Long, String> ipMap = new LinkedHashMap();
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            int length = 0;
            //加快写入效率，每次读取4096字节
            byte[] b = new byte[4096];

            long start = System.currentTimeMillis();
            String line;
            while ((line = reader.readLine()) != null) {
                String [] infos = line.split("\t");
                if (infos.length > 1) {
                    if (infos[0].matches("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}/[0-9]{1,2}")) {
                        genIpv4Info(infos[0], ipMap);
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

        ipMap = new LinkedHashMap();
        genIpv4Info("183.194.167.179/31", ipMap);

        long start = System.currentTimeMillis();
        String testip = "183.194.167.179";
        boolean result = true;
        String [] ipinfo = testip.split("\\.");
        long ipnum = Long.parseLong(ipinfo[0])<<24 | Long.parseLong(ipinfo[1])<<16
            | Long.parseLong(ipinfo[2])<<8 | Long.parseLong(ipinfo[3]);

        int index = 0;
        for (int i= 0; i<= 32; i++) {
            int mask = (32 - i);
            String crip = ipMap.get(ipnum >> mask);
            if(crip != null) {
                index++;
                int type = Integer.parseInt(crip.replaceAll(".*/", ""));
                if (type == i) {
                    System.out.println("IP已被拦截，拦截IP段：" + crip + ", 偏移：" + i);
                    result = false;
                    break;
                }
            }
        }
        //for (Map crip : ips) {
        //    if (verifyIp(ipnum, crip)) {
        //        result = false;
        //        System.out.println("IP已被拦截，拦截IP段：" + crip);
        //        break;
        //    }
        //}
        if (result) {
            System.out.println("IP未被拦截，正常访问！index == " + index);
        }

        long end = System.currentTimeMillis();
        System.out.println("IP校验完成，耗时：" + (end - start) + "ms");

    }

    private static boolean verifyIp(long ipnum, Map crip) {

        return verifyIpv4(ipnum, crip);
    }

    private static boolean verifyIpv6(int ipnum, String crip) {
        return false;
    }

    private static Boolean verifyIpv4(long ipnum, Map cidr) {
        return (ipnum & (int)cidr.get("mask")) == ((long)cidr.get("verify") & (int)cidr.get("mask"));
    }

    private static Map genIpv4Info(String cidr, LinkedHashMap map) {
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
        int mask = 32 - type;
        String [] verifyInfos = cidr.replaceAll("/.*", "").split("\\.");
        Map result = new HashMap();
        try {
            long verifyNum = Long.parseLong(verifyInfos[0])<<24 | Long.parseLong(verifyInfos[1])<<16
                | Long.parseLong(verifyInfos[2])<<8 | Long.parseLong(verifyInfos[3]);
            //result.put("verify", verifyNum & mask);
            //result.put("info", cidr);

            map.put(verifyNum >> mask, cidr);
        } catch (Exception e) {
            System.out.println("error, cidr: " + cidr);
            e.printStackTrace();
        }

        return result;
    }
}
