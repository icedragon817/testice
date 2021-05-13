package com.alibaba.game.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.game.utils.FileUtils;
import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.alibaba.xxpt.gateway.shared.client.http.PostClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

public class TestDingUser {

    public static String accessToken;

    public static void main(String[] args) {

        //String accessToken = getAliToken();
        accessToken = "c88f8e08d7f13873a1b0fcd463e8e6c9";

        //{"name":"张辰浩","department":{"errcode":0,"sourceIdentifier":"71141","userPermits":"","userPerimits":"","outerDept":false,"errmsg":"ok","deptManagerUseridList":"","parentid":54813196,"groupContainSubDept":false,"outerPermitUsers":"","outerPermitDepts":"","deptPerimits":"","createDeptGroup":false,"name":"运维中心","id":90159463,"autoAddUser":false,"deptHiding":false,"deptPermits":"","order":90159463},"userid":"230470"}
        //UC 12027009  "parentid" -> "1"
        //互娱 91703492  "parentid" -> "1"
        //77193127 102116307 94420358 12027050 12027022
        //大文娱业务线 12027050  "parentid" -> "12027009"
        //互动娱乐事业部 12028959  "parentid" -> "55211396"
        //技术中台 53176489  "parentid" -> "12028959"
        //{"sourceIdentifier":"72824","createDeptGroup":false,"name":"技术中台","id":93945256,"autoAddUser":false,"parentid":55211396}
        //运维中心 112616108  "parentid" -> "93945256"
        //互动娱乐事业部IEU 55211396  "parentid" -> "12027050"
        //游戏研发线 21325719
        //工作室群 93937094
        //崇德 22530364
        //质量管理部 "id" -> "94165086"
        //质量保障中心 "id" -> "28559288"
        //M5 62700957 "parentid" -> "94018088"
        //91703492 53912490 53893662
        String departMentId = "91703492";

        List users = new ArrayList();

        genDepartmentUser(users, departMentId, true);

        System.out.println(departMentId + "下总计：" + users.size() + "人");
        String name = "黎斌龙";
        boolean isFind = false;
        for (Object obj : users) {
            Map info = (Map)obj;
            String jname = (String)info.get("name");
            if (name.equals(jname)) {
                System.out.println(info.toString());
                isFind = true;
                break;
            }
        }
        if (!isFind) {
            System.out.println("departmentId:"+departMentId+" 部门下查无此人");
        }

        System.out.println(accessToken);
    }

    private static void genDepartmentUser(List users, String departMentId, boolean flag) {

        String rst = getDepentMentLs(accessToken, departMentId);
        String rst1 = getDepentMentDetail(accessToken, departMentId);
        String rstuser = getDepentMentMember(accessToken, departMentId);
        JSONObject rs = JSON.parseObject(rst);
        JSONObject rs1 = JSON.parseObject(rst1);
        JSONObject rsu = JSON.parseObject(rstuser);
        if (rs.getInteger("errcode") != 0) {
            accessToken = getAliToken();
            genDepartmentUser(users, departMentId, flag);
            return;
        }

        System.out.println("正在统计 departmentId:"+departMentId + " " + rs1.getString("name") + "人数");
        addUser(users,rsu,rs1);
        if (!flag) {
            return;
        }
        JSONArray list = rs.getJSONArray("department");
        System.out.println("departmentId:"+departMentId+" 下拥有子部门 " + list.size() + "个");
        if (list == null) {
            return;
        }
        for (Object obj : list) {
            Map info = (Map)obj;
            String parentId = info.get("parentid").toString();
            if (parentId.equals(departMentId)) {
                genDepartmentUser(users, info.get("id").toString(), true);
            }
        }

    }

    private static void addUser(List users, JSONObject rsu, JSONObject department) {
        JSONArray list = rsu.getJSONArray("userlist");
        if (list == null) {
            return;
        }
        for (Object obj : list) {
            Map map = (Map)obj;
            map.put("department", department);
            users.add(map);
            FileUtils.writeStr(map.toString(), "D://alibaba_user_huyu");
        }
    }

    private static String getDepentMentMember(String accessToken, String departMentId) {
        String url = "https://oapi.dingtalk.com/user/simplelist";
        //String url = "https://oapi.dingtalk.com/user/list";

        String paramStr = "access_token="+accessToken+"&department_id="+departMentId;

        return HttpRequest.sendGet(url, paramStr);
    }

    private static String getDepentMentLs(String accessToken, String departMentId) {
        String url = "https://oapi.dingtalk.com/department/list";
        String paramStr = "access_token="+accessToken+"&id="+departMentId;

        return HttpRequest.sendGet(url, paramStr);
    }

    private static String getDepentMentDetail(String accessToken, String departMentId) {
        String url = "https://oapi.dingtalk.com/department/get";
        String paramStr = "access_token="+accessToken+"&id="+departMentId;
        return HttpRequest.sendGet(url, paramStr);
    }

    private static String getAliToken() {
        ExecutableClient client = ExecutableClient.getInstance();
        client.setProtocal("https");
        client.setDomainName("u-api.alibaba-inc.com");
        client.setAccessKey("octopod-ZV36814RVh5d3j1yIJbI3q");
        client.setSecretKey("uf550Fj3i1Ys76EH8bpa223R4SZ0AoWh3c24Zn8m");
        client.init();

        String api = "/token/corpAccessToken.json";
        PostClient postClient = ExecutableClient.getInstance().newPostClient(api);
        postClient.addParameter("appCode", "passport");
        postClient.addParameter("corpId", "dingd8e1123006514592");
        String jsonString = postClient.post();
        Map responseMap = JSON.parseObject(jsonString, Map.class);
        String errorMsg = (String)responseMap.get("errorMsg");
        if (StringUtils.isBlank(errorMsg) || !StringUtils.equalsIgnoreCase(errorMsg, "ok")) {
            throw new RuntimeException("调用钉钉获取企业access_token失败！");
        }
        String access_token = (String)responseMap.get("content");
        return access_token;
    }
}
