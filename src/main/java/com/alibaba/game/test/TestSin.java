package com.alibaba.game.test;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.game.utils.ParamsUtils;

import com.sun.jndi.toolkit.url.UrlUtil;

public class TestSin {
    public static void main(String[] args) throws MalformedURLException {

        String json = "{\"operate\":\"script\",\"data\":\"{\\n    \\\"10.0.1.21\\\": {\\n        \\\"status\\\": "
            + "\\\"failed\\\", \\n        \\\"result\\\": {\\n            \\\"_ansible_parsed\\\": true, \\n         "
            + "   \\\"changed\\\": false, \\n            \\\"_ansible_no_log\\\": false, \\n            \\\"cmd\\\": "
            + "\\\"/opt/get_lastest_server\\\", \\n            \\\"failed\\\": true, \\n            \\\"rc\\\": 8,"
            + " \\n            \\\"invocation\\\": {\\n                \\\"module_args\\\": {\\n                    "
            + "\\\"warn\\\": true, \\n                    \\\"executable\\\": null, \\n                    "
            + "\\\"_uses_shell\\\": false, \\n                    \\\"_raw_params\\\": \\\"/opt/get_lastest_server"
            + ".sh\\\", \\n                    \\\"removes\\\": null, \\n                    \\\"creates\\\": null, "
            + "\\n                    \\\"chdir\\\": null\\n                }\\n            }, \\n            "
            + "\\\"msg\\\": \\\"[Errno 8] Exec format error\\\"\\n        }\\n    }\\n}\",\"taskId\":306}\n";

        JSONObject object = JSON.parseObject(json);
        object = object.getJSONObject("data");
        object = object.getJSONObject("10.0.1.21");
        object = object.getJSONObject("result");
        Object cmd = object.get("cmd");
        if (cmd == null || (cmd instanceof String && ((String) cmd).indexOf("sh") <= 0)
            || (cmd instanceof JSONArray && ((JSONArray)cmd).size() > 1 && !"python".equals(((JSONArray)cmd).get(0)))) {
            System.out.println("错误");
        }

        //String testjson = "{\"ABCabc123\":{\"17\":{\"19\":{\"total_number\":205,\"ts1\":1545880410, \" ts2\":{}}}}}";
        String testjson = "{\"common\":{\"wings_sg\":{\"total_number\":203,\"ts\":1551861720}}}";
        Object vlist = JSONPath.read(testjson, "$.common.\"wings_sg\".total_number");

        //System.out.println(vlist);

        JSONObject data = JSONObject.parseObject(testjson);
        data = data.getJSONObject("ABCabc123");
        while (data.keySet().size() > 0) {
            Set<String> keySet = data.keySet();
            boolean isData = false;
            String keyProperties = null;
            System.out.println("ketSet size " + keySet.size());
            for (String key : keySet) {
                System.out.println(key);
                keyProperties = key;
                if (key.equals("ts")) {
                    isData = true;
                    break;
                }
            }
            if (isData) {
                break;
            } else {
                System.out.println(data.toJSONString());
                data = data.getJSONObject(keyProperties);
            }
        }
        System.out.println(data.toJSONString());
        double tz = 77;
        double h = 1.7;
        double vv = tz / (h*h);
        System.out.println("体重指数：" + vv);
        int low = 0;
        while (vv > 24) {
            low++;
            tz = tz - 1;
            vv = (tz - low) / (h*h);
        }
        System.out.println("需要减重：" + low + "kg, 体重指数到达：" + vv);
        String testparm = "testjkjdakfdkajkaaajkj122kj,afk211哈哈,\"ts\": 124312563";
        System.out.println(testparm.replaceAll("[1-9]{1}", "11111"));
        byte[] tes = testparm.getBytes();
        byte[] test1 = new byte[tes.length - 1];
        for (int i = 0; i< test1.length; i++) {
            test1[i] = tes[i];
        }
        System.out.println(tes.length);
        System.out.println(testparm.length());
        System.out.println(new String(test1));
        System.out.println(new String(tes));

        int i = 1000;
        i = ++i/3;
        System.out.println(i);
        System.out.println("i=" + i++);
        System.out.println(i);
        int a = 10;
        //for (int i=0;i<=360;i++) {
        //
        //    System.out.println(getSin(i, a));
        //}
    }

    private static Map<String, String> paramToMap(String paramStr) {
        String[] params = paramStr.split("&");
        Map<String, String> resMap = new HashMap<String, String>();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length >= 2) {
                String key = param[0];
                String value = param[1];
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                resMap.put(key, value);
            }
        }
        return resMap;
    }
    public static int getSin(int i,int v) {

        Double r = (1 + Math.sin(i * Math.PI / 180)) * 100;
        return r.intValue();
    }
}
