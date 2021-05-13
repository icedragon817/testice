package com.alibaba.game.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequest {

    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

	public static void main(String[] args) throws UnsupportedEncodingException {

	    List tlist = new ArrayList();
	    for (Object o : tlist) {
            System.out.println(o);
        }
        long start = System.currentTimeMillis();
        //发送 GET 请求
//        String s=HttpRequest.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
//        System.out.println(s);
        
        //发送 POST 请求
//        String sr=HttpRequest.sendPost("http://localhost:8080/ds_platform/HotSearch/findHotSeach", "");
//		 System.out.println(sr);

        //String msg = "alertName=i-uf6c4h06k5gawplr620k_1_memory_usedutilization"
        //    + "&alertState=ALERT"
        //    + "&curValue=9.6"
        //    + "&dimensions=%7BuserId%3D1010927501920546%2C+instanceId%3Di-uf6c4h06k5gawplr620k%7D"
        //    + "&expression=%24Average%3E%3D6"
        //    + "&instanceName=sh-test-mobacd-apiclient-0-4-1%2F101.132.193.178"
        //    + "&metricName=Host.mem.usedutilization"
        //    + "&metricProject=acs_ecs"
        //    + "&namespace=acs_ecs"
        //    + "&signature=s0sOPtB3EDQ%2BGFYHrY%2FY7w6EIFg%3D"
        //    + "&timestamp=1532233980000"
        //    + "&userId=1010927501920546";

		Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> data = new HashMap<>();
        //map.put("body",data);

        //List<Integer> serverIds = new ArrayList<>();
        //serverIds.add(166);
        //serverIds.add(142);
        //map.put("name", "新加坡机房".getBytes());
        //map.put("serverIds", serverIds);
        //map.put("msg", "just test");
        //map.put("tag", "sonar");
        //map.put("url", "http://127.0.0.1:8080/wings-notify/message/ansibleCallback.do");
        //map.put("serverType", "justgogo");
        //map.put("token", "GazfCecDRsyg1orrVFdQgns86//buIVA3CLhUxz6zzfPKuFKHXKmc+uSNRdGkiQg");
        //map.put("token", "zvz5BQpGb+lpRFFiih8RS7ixExGLpdrnaU66yndLQqw=");
        String txt = "aaaaaaaaaaaaaaaaaa\uD83D\uDE97111\uD83D\uDE97aaaaaaaaaaaaaaaa哈哈aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String finaltxt = txt + txt  + txt  + txt  + txt  + txt  + txt  + txt  + txt  + txt ;
        String finaltext = finaltxt + finaltxt  + finaltxt  + finaltxt  + finaltxt  + finaltxt  + finaltxt;

        finaltext = "每当迈入浩如烟海的图书馆时，眼前的“书山”不由让我们震惊：在这贯穿古今、兼容中西的著作中，我们该怎样阅读？阅读什么？是深阅读还是浅阅读？在我们眼中，这些都不重要，我觉得最重要的，就是用心阅读，用心体会！用心阅读，我们能体验中华五千年的香韵，能品味唐诗宋词元曲明清小说的清幽和旷达，能体验李太白那“安能摧眉折腰事权贵，使我不得开心颜”的豪迈，体验文天祥“人生自古谁无死，留取丹心照汗青”的凛然正气，更能体验李清照那“凄凄惨惨戚戚”的忧愁与悲愤。阅读，每个人都有自己的方法，有的人读死书，有的人死读书，有的人品其文化内涵，有的人知其“大意”，有的人浅阅读，有的人深阅读，然而不管怎样阅读，我们只要用心，便能品出其文墨芳香。五柳先生的阅读方法也许值得我们借鉴，在这个竞争激烈、快节奏的社会中，我们已经没有太多的时间去阅读，不求甚解已成为大多数人的步调。但是，他也没有完全地用放任自流的方法去面对阅读，在阅读过程中“每有会意，便欣然望食”。无论怎样，我们要有用心去面对的信心。，一般就不存在“用心阅读”的可能。此外，文中“不求甚解已成为大多数人的步调”、“那么整个世界都将在我们的眼中”等语句，也应作修改。";
        finaltext = "起风了。白云远遁，沙尘泛起，站在六楼也看不多远。还好，柳树绿了。\n"
            + "\n"
            +
            "将上班路上骑行十多分钟的这几句感受，写在了手机上，顺手发给了一个朋友。头脑里还是柳树绿的样子，那不是绿意氤氲，那绿已经具体到打开的叶片，小小的，支棱着，颜色有点发黄，让每一根枝条都显得柔嫩，让一棵大树看上去也好像刚长出来似的。\n"
            + "\n"
            +
            "此时，榆树在忙着打骨朵，它的花细小，毛绒绒的一小粒，是接近树皮那样的略有点泛紫的颜色，虽然一串串挤在枝上，却不显眼。而杨树还看不出什么，好像还在沉睡，只有路边改良的无絮杨，挂出一树紫色的穗子，满树却不见一星绿色。\n"
            + "\n"
            +
            "常用柳绿花红描述春天，而在我们这里只有柳绿，大自然中的花红并没有同时到来，所以柳绿是可以和春天画等号的。的确，柳树完全可以把春天扶起来，不但最先绿了，还高大，远远地就能让人看见，尤其是柳丝在风中的摆动是那么柔美，是春天该有的样子。\n"
            + "\n"
            + "又走在走廊，随意向外看了一眼，看见了马路两边的柳树，不禁惊呼：柳树更绿了。仅仅过去两个小时，那绿色的扩张就能被明显地感觉到。我看了看手机上的指示：22，它知道怎样呼应气温，是多么积极的生长啊。\n"
            + "\n"
            +
            "下午放假，午睡之后想读几页书，可是，风声让我心神不宁，抬头看向窗外，天空发黄，细瞅才会看见隐约的蓝色，淡的发白。开春以后，最少要刮两场大风的，第一场把江刮开，把江鱼刮向市场，再刮向餐桌。第二场就是今天这样的风，一样大，一样昏天暗地，但暖，能刮起来的沙尘更多。这样的风一旦刮起就不是一天两天，它会在某个你睡去的晚上停下，明早你会发现世界到处都绿了，有淅淅沥沥的雨淋下。那是春天的第一场雨，让温度计里的液柱掉下一截，昨天脱下的绒衣又得穿上，而欣喜在眼中含着。那时，柳树就会在满眼绿色中被同一了。\n"
            + "\n"
            +
            "记得有几条小街两旁的柳树非常好看，我是路过碰上的，眼睛一下子亮了：哦，春天！我没有停下，不足十米的路几步就跨过去了，它们却如此固执地留在了我的心里。如果有谁问某条大街上有什么树，我得想上一会才能回答，且是不十分确定的口气，要是问那几条小街上的，我会脱口而出：柳树。我得去看看。\n"
            + "\n"
            +
            "它们还在，还那样绿着，再次印证了它们在我心中的美好。有一条不足百米长的小街，东西走向，与一条南北大街垂直。那天，是下班后，我在大街东侧由南向北走，在路口等灯时向西看了一眼，就看见了小街上的两行柳树，看见了一树一树相连的嫩黄，两行嫩黄在高处几近两手相牵，夕阳在与它们等高的那端，光线透过来，很梦幻的样子。今天我再次来到这里，同样的时令同样的时间，没有夕阳，有的是沙尘，弄得天空昏黄一片，我看见的柳树还是那么新鲜光亮，它们的美一分没减。\n"
            + "\n"
            + "那是一种自在的美，不需要湖水映照，不需要白云照拂，不需要阳光描绘。春天来了，无论长在哪里，不管天空脸色如何，柳树都要长出一个好看的自己。 ";

        byte[] gzipbyte = compress(finaltext, "utf-8");
        String gzipstr = new String(gzipbyte, "iso-8859-1");
        System.out.println(finaltext.getBytes("utf-8").length);
        System.out.println(finaltext.length());
        System.out.println(gzipstr.length());
        byte[] ungzipbyte = uncompress(gzipstr.getBytes("iso-8859-1"));
        System.out.println(ungzipbyte.length);
        String ungzipstr = new String(ungzipbyte,"utf-8");
        //System.out.println(ungzipstr);
        System.out.println(ungzipstr.length());
        //map.put("context", gzipstr);
        map.put("account_token", "zvz5BQpGb+lpRFFiih8RS7ixExGLpdrnaU66yndLQqw=");
        //map.put("token", "zvz5BQpGb+lpRFFiih8RS7ixExGLpdrnaU66yndLQqw=");
        map.put("cmd", "is_join_beta_test");
        //map.put("name", "\uD83D\uDE97111\uD83D\uDE97");
        map.put("name", "amaing_003");
        map.put("uid", "11111");

        map.put("zone", "test");
        map.put("size", 5235464);
        map.put("accountId", "afdaet");
        map.put("roleId", 10086);
        map.put("type", 1);
        map.put("version", "276.1");
        map.put("battleTime", System.currentTimeMillis());
        map.put("battleType", 1);

        List matchData = new ArrayList();
        Map matchInfo = new HashMap();
        matchInfo.put("zoneId", 1001);
        matchInfo.put("parentId", 1000);
        matchInfo.put("ip", "127.0.0.1");
        matchInfo.put("port", "8555");
        List tls = new ArrayList();
        Map tmap1 = new HashMap();
        tmap1.put("min", 0);
        tmap1.put("max", 1000);
        tmap1.put("timeConsuming", 15);
        tls.add(tmap1);
        Map tmap2 = new HashMap();
        tmap2.put("min", 1000);
        tmap2.put("max", 10000);
        tmap2.put("timeConsuming", 85);
        tls.add(tmap2);
        matchInfo.put("matchInfo", tls);
        matchData.add(matchInfo);
        map.put("data", matchData);
        map.put("mmr", "2655");
        //map.put("instanceId", "i-uf6c4h06k5gawplr620k");
        //map.put("templateId", "1");
        //map.put("metric", "memory_usedutilization");
        //map.put("state", "loginbycode93153196834586847");

        //钉钉 link消息
        //Map link = new HashMap();
        //link.put("text", "Wings平台将为阿里游戏插上翅膀，集合所有周边系统，飞到想去的地方！");
        //link.put("title", "Wings");
        //link.put("messageUrl", "https://cn-wings-test.ejoy.com/web/#/login");
        //link.put("picUrl", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532091398211&di=ecb43aa117425bbe12b58d43f9048aa9&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F23%2F26%2F30Q58PICWC9_1024.jpg");

        //map.put("link", link);
        //map.put("msgtype", "link");

        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //String text = "### 实例：i-uf6c4h06k5gawplr620k触发报警\n\n >";
        //text += "- 报警状态：ALTER\n >";
        //text += "- 报警时间：" + df.format(new Date()) + "\n >";
        //text += "- 监控项：内存使用率\n >";
        //text += "- 报警条件：\n >";
        //text += "           5分钟平均值\n";
        //text += "           />=6%\n";
        //text += "           连续1次\n";
        //text += "- 当前值：9.6\n >";
        //text += "- 实例所属项目：王者荣耀\n\n >";
        //text += "##### 登录wings查看 [报警历史](https://cn-wings-test.ejoy.com/web/#/Geyes/alarmHistory/10)";
        //Map markdown = new HashMap();
        //markdown.put("title", "实例：i-uf6c4h06k5gawplr620k触发报警");
        //markdown.put("text", text);
        //Map at = new HashMap();
        //at.put("isAtAll", true);
        //
        //map.put("markdown", markdown);
        //map.put("msgtype", "markdown");
        //map.put("at", at);

        String json = JSONObject.toJSONString(map);
        System.out.println(json);

        if (1>3) {
            List<Thread> threads = new ArrayList<>();
            for (int j=0;j<10;j++) {
                System.out.println("秒：" + Calendar.getInstance().get(Calendar.SECOND) + ", 线程数量：" + threads.size());
                for (int i=0;i<100;i++) {
                    String accountId = "test_" + (i>=10 ? i : "0" + i);
                    Map newMap = new HashMap();
                    newMap.put("accountId", accountId);
                    newMap.put("token", "zvz5BQpGb+lpRFFiih8RS7ixExGLpdrnaU66yndLQqw=");
                    newMap.put("account_token", "zvz5BQpGb+lpRFFiih8RS7ixExGLpdrnaU66yndLQqw=");
                    newMap.put("cmd", "is_join_tbt_test");
                    String param = JSONObject.toJSONString(newMap);
                    Thread thread = new CollectorThread(i, param);
                    thread.start();
                    threads.add(thread);
                }

                Thread t = new AnThread(threads);
                t.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //等待所有线程执行完毕
            for (Thread t : threads) {
                while (true) {
                    if (!t.isAlive()) {
                        break;
                    }
                }
            }
            try {
                Thread.sleep(200000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//
////		json = "\"params\":{\"verificationType\":\"0\",\"supplierName\":\"test1219_001\",\"productName\":\"test1219_001\"" +
////                ",\"userdesc\":\"test1219_001\",\"logoPath\":\"test1219_001\",\"limit\":\"5\"}";
//        System.out.println(json);
		//String sr=HttpRequest.sendPost("http://101.132.107.73:8081/octopod-file/file/newDir.do", json);
//        int i = 0;
//        while (true) {
//            String sr=HttpRequest.sendPost("https://oapi.dingtalk.com/robot/send?access_token=4298ba3b801be1b2c8069953bb3d28529ce3be80bfe5395fb257b274e3b95eae", json);
//
//            System.out.println(sr);
//            System.out.println("已发送：" + i + "条");
//            if (++i >= 30) {
//                break;
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        //String sr=HttpRequest.sendPost("http://localhost:8081/test/test/collectBug.do", json);
        //String sr=HttpRequest.sendPost("http://47.102.145.147:8080/test_socket/test/testRank.do", json);
        //String sr=HttpRequest.sendPost("http://30.97.198.4:8087/game-video/video/info.do", json);
        //String sr=HttpRequest.sendPost("https://mcd-video-collect.ejoy.com:10443/game-video/video/info.do", json);
        String sr=HttpRequest.sendPost("https://zhuanlan.zhihu.com/p/100704265/video/info.do", json);
//        String sr=HttpRequest.sendPost("https://cn-wings.ejoy.com:444/wings-geyes/metric/alarmCallback.do", json);
//        String sr=HttpRequest.sendPost("http://localhost:8181/test/message/sendMsg.do", json);
//        String sr=HttpRequest.sendPost("https://cn-wings-test.ejoy.com/wings-file/config/previewForApi.do", json);
//        String sr=HttpRequest.sendPost("http://47.102.145.147:8080/game-support/game/getAddress.do", json);
//        String sr=HttpRequest.sendPost("http://47.102.145.147:8080/test_socket/test/testRank.do", json);
//        String sr=HttpRequest.sendPost("https://sea-list-mcd-test.ejoy.com:11080/game-support/match/zoneList.do", json);
//        String sr=HttpRequest.sendPost("http://30.97.198.9:8085/game/game/getAddress.do", json);
//        String sr=HttpRequest.sendPost("http://30.97.198.9:8085/game/game/getAddress.do", json);
//        String sr=HttpRequest.sendPost("https://sea-list-mcd.ejoy.com:8081/game-support/match/zoneList.do", json);//t10121
//        String sr=HttpRequest.sendPost("http://30.97.198.9:8085/game/match/zoneList.do", json);
//        String sr=HttpRequest.sendPost("https://sea-list-mcd-pbt2-test.ejoy.com:8081/game-support/match/zoneList.do", json);
        //String sr=HttpRequest.sendPost("https://sea-list-mcd-test.ejoy.com:8081/game-support/match/zoneList.do", json);
//        String sr=HttpRequest.sendPost("https://sea-list-mcd-pbt2-test.ejoy.com:8081/game-support/game/getAddress.do", json);
//        String sr=HttpRequest.sendPost("https://47.75.128.218:8081/game-support/login/login.do", json);
//        String sr=HttpRequest.sendPost("https://sea-list-mcd-test.ejoy.com:8081/game-support/match/zoneList.do", json);
//        String sr=HttpRequest.sendPost("http://47.102.145.147:8080/game-support/game/uploadFeedback.do", json);
//        String sr=HttpRequest.sendPost("http://localhost:8085/game/game/getAddress.do", json);
//        String sr=HttpRequest.sendPost("http://47.102.145.147:8080/game-support/game/getAddress.do", json);
//        String sr=HttpRequest.sendPost("http://sea-list-mcd.ejoy.com:8500/game-support/game/getAddress.do", json);
//        String sr=HttpRequest.sendPost("https://sea-list-mcd-test.ejoy.com:8081/game-support/game/getAddress.do", json);
//        String sr=HttpRequest.sendPost("http://47.102.145.147:8080/game-support/game/getAddress.do", json);
//        String sr=HttpRequest.sendPost("http://47.244.147.197:8080/game-support/game/getAddress.do", json);
        //String sr=HttpRequest.sendPost("http://localhost:8080/wings-file/config/previewForApi.do", json);
//        String sr=HttpRequest.sendPost("https://cn-wings-test.ejoy.com/wings-geyes/metric/alarmCallback.do", json);

        //String sr=HttpRequest.sendPost("http://106.14.9.101:8081/Geyes/metric/alarmCallback.do", json);
		//String sr=HttpRequest.sendPost("http://localhost:8081/notify/message/sendAlarmMsg.do", json);
		//String sr=HttpRequest.sendPost("https://cn-wings-test.ejoy.com/wings-notify/test/test.do", json);
		//String sr=HttpRequest.sendPost("http://101.132.86.124/octopod-file/file/queryProjectVersion.do", json);

        System.out.println(sr);
        long end = System.currentTimeMillis();
        System.out.println("请求花费时间："+(end - start) + "ms");

    }
	
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            if (!param.isEmpty()) {
                urlNameString += "?" + param;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1) mouliang");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("X-Forwarded-For", "*/*, 106.11.71.58");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)  mouliang");
            conn.setRequestProperty("Content-type", "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(300000);
            //conn.setRequestProperty("token", "4282CB29671B46D330F24C0B1AAB321261AE08ADF88ED086F152F7F72AFC90700E907A9C9CA8FD9C5C1B7F3FFD5BD8F1");
            //conn.setRequestProperty("userToken", "b94871b0-a25e-4035-94a2-06d4dfd0b4b8");
            //conn.setRequestProperty("buildVersion", "22");
            //conn.setRequestProperty("platform", "ios");
            //conn.setRequestProperty("guestId", "5EB5-F8-41-12DB4607");
            // 获取URLConnection对象对应的输出流
            out = new PrintStream(conn.getOutputStream());
            // 发送请求参数
            //out.print(param.getBytes("utf-8"));
            out.print(param);
            //String finaltext = "Mahangin. Ang mga puting ulap ay malayo, ang alikabok ay tumataas, at hindi malayo sa ika-anim na palapag. Sa kabutihang palad, ang wilow ay berde. Sinulat ko ang mga damdaming ito sa mobile phone nang higit sa sampung minuto sa paraan upang magtrabaho, at ipinadala ito sa isang kaibigan. Isip o wilow berde hitsura, ito ay hindi siksik halaman, ito ay nai-inilarawan upang buksan ang berdeng dahon, maliit, cocked forward, ang isang maliit na kulay-dilaw na kulay, kaya na ang bawat isa sa mga sanga ay naging malambot, ipaalam sa isang puno Mukhang lumaki na lang. Sa puntong ito, elm busy naglalaro Guduo, ang mga maliliit na mga bulaklak, ang isang malambot na mga Bolitas na, ang ilang mga maliit na pan-lilang kulay na malapit sa ganoong uri ng bark, kahit na isang string ng masikip na sanga, ngunit hindi kahanga-hanga. Ang mga alamo Hindi rin makita kung ano ang, kung natutulog pa, hindi lamang pinabuting sa baybay-daan Yang Xu, nakikipag-hang-out sa isang puno purple borlas, Ilex ngunit hindi maaaring makita ang isang green star. Karaniwang Liulv bonus ilarawan ang spring, at habang kami ay narito lamang Liulv likas na katangian ng bonus ay hindi dumating sa parehong panahon, ito ay posible at sa tagsibol at nag-iiwan sa mabubunot parallel. Sa katunayan, ang spring willow ay maaaring makatulong sa mga ito, hindi lamang ang unang berde, at din ang taas, malayo magagawang upang makita, lalo na ng mga sauce sa gumiwang-giwang sa ang hangin ay kaya malambot, na ganito ang hitsura ng isang spring.";
            //param = "{'uid':10001,'name':'test001',context:'" + finaltext + "'}";
            //byte[] finalBytes = compress(param, "utf-8");
            //System.out.println("字节长度： " + finalBytes.length);
            //out.write(finalBytes);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            HttpsURLConnection httpcoin = (HttpsURLConnection) conn;
            in = new BufferedReader(
                    new InputStreamReader(httpcoin.getErrorStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param bytes
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, byte [] bytes) {
        PrintStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accessToken", "7VMuQjMUp0+YVRFMe5yhnHYksvttnFbXhOM3EbXR0fcbXmvOF4588bMVbnJ6Cjuw");
            //conn.setRequestProperty("accept", "*/*");
            //conn.setRequestProperty("X-Forwarded-For", "*/*, 106.11.71.58");
            //conn.setRequestProperty("connection", "Keep-Alive");
            //conn.setRequestProperty("user-agent",
            //    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)  mouliang");
            //conn.setRequestProperty("Content-type", "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(300000);
            out = new PrintStream(conn.getOutputStream());
            // 发送请求参数
            //out.print(bytes);
            out.write(bytes);
            System.out.println("字节长度： " + bytes.length);
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    static class CollectorThread extends Thread {

        private int index;
        private String json;

        public CollectorThread(int index, String json) {
            this.index = index;
            this.json = json;
        }

        @Override
        public void run() {
            //String sr=HttpRequest.sendPost("http://30.97.198.17:8080/game/game/getAddress.do", json);
            String sr=HttpRequest.sendPost("http://47.102.145.147:8080/game-support/game/getAddress.do", json);
            //String sr=HttpRequest.sendPost("http://localhost:8085/game/game/getAddress.do", json);
            //String sr=HttpRequest.sendPost("http://localhost:8085/game/game/verifyToken.do", json);
            //String sr=HttpRequest.sendPost("https://cn-wings-test.ejoy.com/wings-geyes/metric/alarmCallback.do", json);
            //String sr=HttpRequest.sendPost("http://30.17.2.49:8080/data-handle/data/importBattleLogInfo.do", json);
            //String sr=HttpRequest.sendPost("http://101.132.86.124/octopod-file/file/queryProjectVersion.do", json);

            System.out.println(sr);
        }
    }
    static class AnThread extends Thread {

        private List<Thread> threads;

        public AnThread(List<Thread> threads) {
            this.threads = threads;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println("当前线程数量：" + threads.size());
                int ac = 0;
                //等待所有线程执行完毕
                for (Thread t : threads) {
                    if (t.isAlive()) {
                        ac++;
                    }
                }
                System.out.println("正在运行的线程数量：" + ac);
                if (ac <= 0) {
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
