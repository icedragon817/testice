package com.alibaba.game.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestFunction {

    public static void main(String[] args) {

        String info = "{\"T10159\":{\"battle_online_number\":{\"203\":{\"sum_online_num\":0,"
            + "\"ai_count\":0,\"no_ai_count\":0},\"202\":{\"sum_online_num\":0,\"ai_count\":0,"
            + "\"no_ai_count\":0},\"201\":{\"sum_online_num\":0,\"ai_count\":0,"
            + "\"no_ai_count\":0},\"204\":{\"sum_online_num\":0,\"ai_count\":0,"
            + "\"no_ai_count\":0}},\"ts\":1558092177}}";
        String projectCode = "tpt001";
        Long sourceId = 1l;
        processSonarMsg(info, projectCode, sourceId);
    }

    public static int processSonarMsg(String result, String projectCode, Long sourceId) {

        JSONObject data = JSONObject.parseObject(result);
        data = genDetail(data);
        if (data == null) {
        } else {
            List<Map> datas = genAllDatas(data, projectCode, sourceId, "");

            System.out.println(datas.size());
        }

        return 0;
    }

    private static List<Map> genAllDatas(JSONObject data, String projectCode, Long sourceId, String extraKey) {
        Long nowTimes = System.currentTimeMillis() / 1000;
        List<Map> datas = new ArrayList<>();
        Long timestamp = null;
        Set<String> keySet = data.keySet();
        for (String key : keySet) {
            //时间磋特殊处理
            if ("ts".equals(key)) {
                timestamp = data.getLong(key);
                continue;
            }

            Object sonarInfo = data.get(key);
            if (sonarInfo instanceof JSONObject) {
                extraKey += key;
                datas.addAll(genAllDatas((JSONObject)sonarInfo, projectCode, sourceId, extraKey));
            } else {
                Map sonarData = new HashMap();
                sonarData.put("projectCode", projectCode);
                sonarData.put("sourceId", sourceId);
                sonarData.put("key", extraKey + key);
                sonarData.put("setValue", data.getInteger(key));
                datas.add(sonarData);
            }
        }

        return datas;
    }

    private static JSONObject genDetail(JSONObject data) {
        while (data.keySet().size() > 0) {
            Set<String> keySet = data.keySet();
            boolean isData = false;
            String keyProperties = null;
            for (String key : keySet) {
                keyProperties = key;
                if (key.equals("ts")) {
                    isData = true;
                    break;
                }
            }
            if (isData) {
                break;
            } else {
                System.out.println("keyProperties: " + keyProperties + ", 当前json:" + data.toJSONString());
                data = data.getJSONObject(keyProperties);
            }
        }
        return data;
    }
}
