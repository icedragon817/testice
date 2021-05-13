package com.alibaba.game.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.game.model.FileEntry;
import com.alibaba.game.model.Metric;

import com.aliyuncs.utils.StringUtils;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.tools.ant.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestG {

    private static Logger logger = LoggerFactory.getLogger(TestG.class);

    private static Pattern pattern = Pattern.compile("com.aligame.cdstudio.octopod.shiro\\S+");

    public static byte[] compress(byte[] bytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(bytes);
            gzip.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

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

    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {

        String datass = "{\n"
            + "\t\"players\": [{\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"daye\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 2,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 2,\n"
            + "\t\t\"uid\": \"10010\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}, {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"亮亮\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 2,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 2,\n"
            + "\t\t\"uid\": \"10008\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}, {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"mxj\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 2,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 2,\n"
            + "\t\t\"uid\": \"10003\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}, {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"scudderjiao\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 2,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 2,\n"
            + "\t\t\"uid\": \"10001\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}, {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"困惑X诺顿\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 2,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 2,\n"
            + "\t\t\"uid\": \"10000\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}, {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"隐隐约约\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 1,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 1,\n"
            + "\t\t\"uid\": \"10013\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}, {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"蔡玮\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 1,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 1,\n"
            + "\t\t\"uid\": \"10012\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}, {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"固执×丘吉尔\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 1,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 1,\n"
            + "\t\t\"uid\": \"10011\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}, {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"橙谦\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 1,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 1,\n"
            + "\t\t\"uid\": \"10009\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}, {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"老赵\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 1,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 1,\n"
            + "\t\t\"uid\": \"10007\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t}],\n"
            + "\t\"myrank\": 1,\n"
            + "\t\"my_rankinfo\": {\n"
            + "\t\t\"likes\": 0,\n"
            + "\t\t\"info\": {\n"
            + "\t\t\t\"win_num\": 0,\n"
            + "\t\t\t\"name\": \"亮亮\",\n"
            + "\t\t\t\"kill\": 0,\n"
            + "\t\t\t\"battle_num\": 0,\n"
            + "\t\t\t\"level\": 2,\n"
            + "\t\t\t\"attack_total\": 0,\n"
            + "\t\t\t\"defense_total\": 0,\n"
            + "\t\t\t\"death\": 0,\n"
            + "\t\t\t\"attack_win\": 0,\n"
            + "\t\t\t\"defense_win\": 0\n"
            + "\t\t},\n"
            + "\t\t\"score\": 2,\n"
            + "\t\t\"uid\": \"10008\",\n"
            + "\t\t\"score2\": 0,\n"
            + "\t\t\"score3\": 0\n"
            + "\t},\n"
            + "\t\"errorcode\": 0,\n"
            + "\t\"ranks\": [1, 1, 1, 1, 1, 6, 6, 6, 6, 6]\n"
            + "}";

        JSONObject gameData = JSON.parseObject(datass);

        JSONArray ranks = gameData.getJSONArray("ranks");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date current = new Date();
        System.out.println(simpleDateFormat.format(current));
        simpleDateFormat.applyPattern("yyyyMMdd HH:mm:ss");
        System.out.println(simpleDateFormat.format(current));
        if (3> 2)
            return;
        //String reg = "^^cn-(?!hongkong).*$";
        String reg = "cn-hongkong|ap-southeast-1|us-west-1";
        reg = reg != null ? reg.replaceAll("\\*","\\\\S+") : "\\S+";

        System.out.println("i=="+reg);

        List list = new ArrayList();
        for (int i = 0; i<100; i++) {
            list.add(i);
        }

        System.out.println(list.size());

        System.out.println("cn-shanghai".matches(reg));
        System.out.println("ap-xjp".matches(reg));
        System.out.println("cn-hongkong".matches(reg));
        System.out.println("ap-southeast-1".matches(reg));
        System.out.println("us-west-1".matches(reg));
        if (3>2)
            return;
        String txt = "aaaaaaaaaaaaaaaaaa\uD83D\uDE97111\uD83D\uDE97aaaaaaaaaaaaaaaa哈哈aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String finaltxt = txt + txt  + txt  + txt  + txt  + txt  + txt  + txt  + txt  + txt ;
        //String finaltext = finaltxt + finaltxt  + finaltxt  + finaltxt  + finaltxt  + finaltxt  + finaltxt  + finaltxt;

        finaltxt = "每当迈入浩如烟海的图书馆时，眼前的“书山”不由让我们震惊：在这贯穿古今、兼容中西的著作中，我们该怎样阅读？阅读什么？是深阅读还是浅阅读？在我们眼中，这些都不重要，我觉得最重要的，就是用心阅读，用心体会！用心阅读，我们能体验中华五千年的香韵，能品味唐诗宋词元曲明清小说的清幽和旷达，能体验李太白那“安能摧眉折腰事权贵，使我不得开心颜”的豪迈，体验文天祥“人生自古谁无死，留取丹心照汗青”的凛然正气，更能体验李清照那“凄凄惨惨戚戚”的忧愁与悲愤。阅读，每个人都有自己的方法，有的人读死书，有的人死读书，有的人品其文化内涵，有的人知其“大意”，有的人浅阅读，有的人深阅读，然而不管怎样阅读，我们只要用心，便能品出其文墨芳香。五柳先生的阅读方法也许值得我们借鉴，在这个竞争激烈、快节奏的社会中，我们已经没有太多的时间去阅读，不求甚解已成为大多数人的步调。但是，他也没有完全地用放任自流的方法去面对阅读，在阅读过程中“每有会意，便欣然望食”。无论怎样，我们要有用心去面对的信心。，一般就不存在“用心阅读”的可能。此外，文中“不求甚解已成为大多数人的步调”、“那么整个世界都将在我们的眼中”等语句，也应作修改。";

        String finaltext = finaltxt;
        //finaltext += finaltxt  + finaltxt  + finaltxt  + finaltxt  + finaltxt;
        System.out.println("finaltext.getBytes() len " + finaltext.getBytes().length);
        System.out.println("finaltext len " + finaltext.length());

        byte[] gzipbyte = compress(finaltext, "utf-8");
        byte b1 = gzipbyte[0];
        System.out.println("gzip len " + gzipbyte.length);
        String gzipstr = new String(gzipbyte, "ISO-8859-1");
        System.out.println("gzipstr len " + gzipstr.length());
        System.out.println(gzipstr);
        HashMap gzmap = new HashMap();
        gzmap.put("context", gzipstr);
        String gzjson = JSON.toJSONString(gzmap);
        byte[] gzjsonbyte = gzjson.getBytes("utf-8");
        String ogzjson = new String(gzjsonbyte, "utf-8");
        Map togzmap = JSONObject.parseObject(ogzjson);
        String str = (String)togzmap.get("context");
        System.out.println(str.getBytes("ISO-8859-1").length);
        byte[] ungzipbyte = uncompress(str.getBytes("iso-8859-1"));
        System.out.println("ungzip len " + ungzipbyte.length);
        String ungzipstr = new String(ungzipbyte,"utf-8");

        System.out.println(ungzipstr);
        System.out.println();
        if (3>2) {
            return;
        }
        String testtoday = "AIDIILA";
        System.out.println(testtoday.substring(0,2));
        Integer uid = 10110;
        Map mapinfo = new HashMap();
        mapinfo.put("uid", "10110");

        Boolean uidCompareReult = (uid == null && mapinfo.get("uid") != null)
            || (uid != null && !uid.equals(mapinfo.get("uid")));
        System.out.println(uidCompareReult);
        List<String> lstest = new ArrayList<>();
        lstest.add("abc");
        //lstest.add("abcd");
        //lstest.add("abcde");
        for (int i = 0; i<100; i++) {

            System.out.println(RandomUtils.nextInt(lstest.size()));
        }
        System.out.println(Arrays.toString(lstest.toArray()));
        Date nowdate = new Date();
        SimpleDateFormat ssdf = new SimpleDateFormat("yyyyMMddhhmmss");

        System.out.println(ssdf.format(nowdate));

        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        nowDate = sdf.parse("2019-03-01 23:30:30");
        System.out.println(String.valueOf(nowDate.getTime()));
        double aaaa = 1;
        for (int i = 0; i<200;i++) {
            aaaa = aaaa * 1.1;

            System.out.println(aaaa);
        }
        System.out.println(aaaa);

        Date lastTime = new Date(1111);
        Date lastTime1 = new Date();
        System.out.println(lastTime.before(lastTime1));
        Map testmap = new HashMap();
        testmap.put("aaa",2);
        testmap.remove("aaa");
        String teeee = "$.na.ab.dff.ga";
        String [] tearr = teeee.split("\\.");
        String ttttarrr = tearr[tearr.length - 1];
        Long timestamp = 0L;
        List<Long> pointers = new ArrayList();
        while (timestamp < 100) {
            pointers.add(timestamp);
            timestamp += 30;
        }

        String testjson = "{\"aaa\":\"bbb\",\"in\":{\"num\":2}}";
        //String testjson = "abcd";
        Object vlist = JSONPath.read(testjson, "$.na");
        //Object vlist = JsonPath.read(testjson, "$..num");
        //int  val = (int)JSONPath.read(testjson, "$..num");
        //System.out.println(val);

        double iai = 0.99;
        double r = 1;
        for (int m=0;m<100;m++) {
            r = iai * r;
        }
        System.out.println("概率：" + (1-r)*100);
        String le = "123456789abcdefghijklmnopqrstuvwxyz";
        System.out.println(le.substring(0,1));
        String testle = "";
        for (int i=0;i<10;i++) {
            String resultle = "当前序号：";
            int index = 0;
            if (StringUtils.isNotEmpty(testle)) {
                index = le.indexOf(testle) + 1;
            }
            resultle += le.toCharArray()[index];
            testle = le.toCharArray()[index] + "";
            System.out.println(resultle);
        }
        Integer aagad = null;
        System.out.printf("hhh" + aagad);
        List<String> liststr =  new ArrayList<>();
        liststr.add("A");
        liststr.add("B");
        liststr.add("c");
        liststr.add("");
        List<String> listss = liststr.stream().filter(StringUtils :: isNotEmpty).map(String :: toLowerCase)
            .collect(Collectors.toList());
        String aaaaa = "Tta123A|5DjlkZ";
        String [] aaddf = aaaaa.split("\\|");
        Metric metric = null;
        //try {
        //    metric = JSONObject.parseObject(testjson, Metric.class);
        //    System.out.println(metric.getProject());
        //} catch (Exception e) {
        //    logger.error("json转换失败");
        //}
        //System.out.println(testjson);
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("aaabbb");
        System.out.println(tl.get());
        tl.remove();
        System.out.println(tl.get());

        long sss = System.currentTimeMillis();
        for (int i=0;i<100000000;i++) {
            int a = 1+1;
        }
        logger.info((System.currentTimeMillis() - sss) + "");
        List<String> listint = new ArrayList<>();
        for (int i=1;i<=10;i++) {
            listint.add(i+"");
        }

        int ii = 0;
        do {
            String i = listint.get(ii);
            System.out.println("----------- " + i);
            if (i.equals("5") || i.equals("8")) {
                listint.remove(i);
                continue;
            }
            ii++;
        } while (ii < listint.size());

        double sa = 0.15,sb = 1;
        int indexa = 0;
        while (true) {
            indexa++;
            for (int i = 0; i< indexa; i++) {
                sb = sb * (1-sa);
            }
            if (sb < 0.001) {
                System.out.println("约到第" + indexa + "个，得病的几率超过99%");
                break;
            }
        }

        String aaaaaa = "bb%sddd%s";
        System.out.println(String.format(aaaaaa,"嘎嘎","啦啦"));
        Date now = new Date();

        Calendar ca = Calendar.getInstance();
        //ca.setTime(now);
        int minite =  ca.get(Calendar.MINUTE);
        if (minite <= 50) {
            ca.add(Calendar.MINUTE,-1 * minite);
            ca.add(Calendar.SECOND,-1 * ca.get(Calendar.SECOND));
            ca.add(Calendar.MILLISECOND,-1 * ca.get(Calendar.MILLISECOND));
            now = ca.getTime();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date tdate = df.parse("2018-04-27 18:00:00");
        System.out.println("now: " + now.getTime() + ", tdate: " + tdate.getTime());
        double ttt1 = 1/((double)999*5/100 + (double)95/100);
        int ttt = 0,count = 10;

        Date date = new Date(1526165340000L);
        Calendar ca1 = Calendar.getInstance();
        ca1.setTime(ca.getTime());
        ca1.setTime(date);
        do {
            System.out.println(++ttt);
            ttt = 10;
            if (ttt == 5) {
                break;
            }
        } while (ttt<count);
        String testRe = null;
        String testMatch = "abaabbb|ac|ccc";
        testRe = testRe != null ? testRe.replaceAll("\\*","\\\\S+") : "\\S+";
        System.out.println(testRe.replaceAll("\\*","\\\\S+"));
        System.out.println(testMatch.matches(testRe));
        long s = System.currentTimeMillis();
        List<Map> bb = new ArrayList();
        for (int i=0;i<10000;i++) {
            Map ttbb = new HashMap();
            ttbb.put("a","aa_test_" + i);
            ttbb.put("b","bb_test_" + i);
            bb.add(ttbb);
        }

        long e = System.currentTimeMillis();
        System.out.println(bb.size() + " total " + (e-s) + " ms" );
        s = System.currentTimeMillis();
        for (Map tm : bb) {
            if (tm.get("a") instanceof String ) {
                String tma = (String)tm.get("a");
                if (tma.equals("aa_test_9999")) {
                    tm.put("a","aa_test_10000");
                    System.out.println(bb.remove(tm));
                    break;
                }
            }
        }
        System.out.println(bb.remove("aa_test_9999") + "  " + bb.size());
        e = System.currentTimeMillis();
        System.out.println("total " + (e-s) + " ms" );
        String a = "com.aligame.cdstudio.octopod.shiro0DFAD";
        System.out.println(pattern.matcher(a).matches());
        String [] aas = new String[2];
        aas[0] = "aa";
        aas[1] = "bb";
        test("aa");
        String ab = "/aaa/dd5a.zip.gz.tar";
        String [] sts = ab.split("/");

        String format = "tar";
        System.out.println(ab.replace("." + format, ""));
        System.out.println(ab.substring(0,ab.lastIndexOf("/")));
        //
        //System.out.println(ab.substring(ab.indexOf("(") + 1,ab.indexOf(")")));
        //Map<String, Object> map = new HashMap<>();
        //map.put("pro", "16");
        //
        //Long a = (Long)map.get("pro");
        //System.out.println(a);

        Date future = new Date(now.getTime() + 1000*60*60*8);
        System.out.println(future);
        String py = "test.py";
        String p = py.substring(py.lastIndexOf(".") + 1);
        System.out.println(p);

        String json = "{";
        json += "    \"10.0.1.21\": {";
        json += "        \"status\": \"ok\",";
        json += "        \"result\": {";
        json += "            \"_ansible_parsed\": true,";
        json += "            \"stderr_lines\": [],";
        json += "            \"cmd\": [";
        json += "                \"md5sum\", ";
        json += "                \"/usr/bin/ls\"";
        json += "            ], ";
        json += "            \"end\": \"2017-12-22 17:17:21.569639\",";
        json += "            \"_ansible_no_log\": false, ";
        json += "            \"stdout\": \"918cb545b3458e1bf18b712b36af304f  /usr/bin/ls\", ";
        json += "            \"changed\": true, ";
        json += "            \"rc\": 0, ";
        json += "            \"start\": \"2017-12-22 17:17:20.559864\",";
        json += "            \"stderr\": \"\",";
        json += "            \"delta\": \"0:00:01.009775\",";
        json += "            \"invocation\": {";
        json += "                \"module_args\": {";
        json += "                    \"warn\": true, ";
        json += "                    \"executable\": null, ";
        json += "                    \"_uses_shell\": false,";
        json += "                    \"_raw_params\": \"md5sum /usr/bin/ls\", ";


        json += "                    \"creates\": null, ";
        json += "                    \"chdir\": null";
        json += "                }";
        json += "            }, ";
        json += "            \"stdout_lines\": [";
        json += "                \"918cb545b3458e1bf18b712b36af304f  /usr/bin/ls\"";
        json += "            ]";
        json += "        }";
        json += "    }";
        json += "}";

        JSONObject ob = null;
        if (json != null && !json.isEmpty()) {
            json = json.replaceAll("}\\{", ",");
            ob = JSON.parseObject(json);
        }
        logger.info(String.valueOf(ob));
        Set<String> ss = ob.keySet();

        ob = ob.getJSONObject("192.168.0.3");

        ob = ob.getJSONObject("result");
        String start = ob.getString("start");
        Date startTime = strToDate(start.substring(0,start.indexOf(".")),"yyyy-MM-dd HH:mm:ss");
        System.out.println(startTime);
        String timeStr = ob.getString("delta");
        String [] timeArr = timeStr.split(":");
        Long hourToMs = Long.parseLong(timeArr[0]) * 60 * 60 * 1000;
        Long minuteToMs = Long.parseLong(timeArr[1]) * 60 * 1000;
        Double secondToMs = Double.parseDouble(timeArr[2]) * 1000;
        Long time = hourToMs + minuteToMs +secondToMs.longValue();
        System.out.println(time);
        JSONArray arr = ob.getJSONArray("stdout_lines");
        String st = ob.getString("stdout").replaceAll("\n","\\\n");
        System.out.println(st);

    }

    public static void test(String ip, String ... args) {
        System.out.println();
    }

    public static Date strToDate(String dateStr, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
