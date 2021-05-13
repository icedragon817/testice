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

public class TestCreateCsv {

    public static void main(String[] args) {

        String idPath = "D:\\data\\odps_clt_release_64\\bin\\coin_detail_100080001008507.csv";
        //String codePath = "D:\\t\\test\\tmp_active_code2.csv";
        String tmpPath = "D:\\data\\odps_clt_release_64\\bin\\coin_detail.csv";

        List<Map> maps = readFromCsv(idPath);
        //List<Map> codes = readFromCsv(codePath);
        long start = System.currentTimeMillis();

        System.out.println("准备读取" );
        //FileUtils.writeStr("player_id,item_id1,item_num1,item_id2,item_num2,item_id3,item_num3,code1,code2", tmpPath);
        FileUtils.writeStr("serverid,roleid,type,amount,payamount,total,itemtype,reason,subreason,tm,ts", tmpPath);
        int total = 0;
        for (int i=0; i< maps.size();i++) {
            String serverid = (String)maps.get(i).get("serverid");
            String servername = (String)maps.get(i).get("servername");
            String roleid = (String)maps.get(i).get("roleid");
            String rolename = (String)maps.get(i).get("rolename");
            String type = (String)maps.get(i).get("type");
            Integer amount = Integer.parseInt((String)maps.get(i).get("amount"));
            int payamount = amount;
            total += amount;
            if (total < 0) {
                payamount = amount - total;
                total = 0;
            }
            String itemtype = (String)maps.get(i).get("itemtype");
            String reason = (String)maps.get(i).get("reason");
            String subreason = (String)maps.get(i).get("subreason");
            String tm = (String)maps.get(i).get("tm");
            String ts = (String)maps.get(i).get("ts");
            //String code1 = (String)codes.get(i*2).get("col1");
            //String code2 = (String)codes.get(i*2+1).get("col1");
            String str = serverid + "," + roleid
                    + "," + type + "," + amount + "," + payamount + "," + total
                    + "," + itemtype + "," + reason + "," + subreason + "," + tm + "," + ts;
            FileUtils.writeStr(str, tmpPath);
        }

        long end = System.currentTimeMillis();
        System.out.println("IP导出完成，耗时：" + (end - start) + "ms");

    }

    private static List<Map> readFromCsv(String path) {

        File f = new File(path);
        List<Map> infos = new ArrayList<>();
        long start = System.currentTimeMillis();

        System.out.println("准备读取" );
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            //加快写入效率，每次读取4096字节
            String line;
            int index = 0;
            List keys = null;
            while ((line = reader.readLine()) != null) {
                index++;
                if (index == 1) {
                    keys = genCsvKey(line);
                } else {
                    genCsvInfo(infos, line, keys);
                }
            }
            fis.close();
            long end = System.currentTimeMillis();
            //文件写入成功
            System.out.println("文件读取完毕，耗时：" + (end - start) + "ms, 读取行数：" + infos.size());
        } catch (IOException e) {
        }
        return infos;
    }

    private static List genCsvKey(String line) {
        String [] info = line.split(",") ;
        List<String> keys = new ArrayList<>();
        for (String key : info) {
            keys.add(key);
        }
        return keys;
    }

    private static void genCsvInfo(List<Map> infos, String line, List<String> keys) {
        if (line.trim().isEmpty()) {
            return;
        }
        Map map = new HashMap();
        String [] info = line.split(",");
        map.put("serverid", info[0]);
        map.put("servername", info[1]);
        map.put("roleid", info[2]);
        map.put("rolename", info[3]);
        map.put("type", info[4]);
        map.put("amount", info[5]);
        map.put("itemtype", info[6]);
        map.put("reason", info[7]);
        map.put("subreason", info[8]);
        map.put("tm", info[9]);
        map.put("ts", info[10]);
//        String num = info[1];
//        if (info.length > 4) {
//            num = info[info.length-1];
//        }
//        map.put("col2", num);
        infos.add(map);

    }

}
