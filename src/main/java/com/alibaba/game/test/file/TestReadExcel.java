package com.alibaba.game.test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.game.utils.FileUtils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestReadExcel {

    public static String url = "jdbc:mysql://rm-j6c9w51yq4ly3a370ao.mysql.rds.aliyuncs.com:3306/cd_game?user=ccd_game&password=6DduRkJ3fbV8W3ah&allowMultiQueries=true";

    public static String path_read = "D:\\t\\test\\tdata\\ios_account_0421.xlsx";
    public static String path_write = "D:\\t\\test\\tdata\\ios_account_0421.csv";

    public static void main(String[] args) throws IOException, SQLException {

        //String path = "D:\\t\\test\\ios_account_0418_all.xlsx";
        //String path = "D:\\t\\test\\ios_account_0418.xlsx";
        //File excelFile = new File("D:\\pbt2_compensate.xlsx");
        File excelFile = new File(path_read);

        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(new FileInputStream(excelFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ////1.读取Excel文档对象
        //XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("D:\\pbt2_compensate.xlsx"));
        //2.获取要解析的表格（第一个表格）
        XSSFSheet sheet = wb.getSheetAt(0);
        //获得最后一行的行号
        int lastRowNum = sheet.getLastRowNum();

        String [] keys = new String[40];
        List<Map> datas = new ArrayList();
        for (int i = 0; i <= lastRowNum; i++) {//遍历每一行
            //3.获得要解析的行
            XSSFRow row = sheet.getRow(i);

            if (i == 0) {
                int index=0;
                Object cell = row.getCell(index);
                while (cell != null) {
                    keys[index] = cell.toString();
                    index++;
                    cell = row.getCell(index);
                }
            } else {
                Map data = new HashMap();
                int index=0;
                Object cell = row.getCell(index);
                while (index < keys.length) {
                    data.put(keys[index],cell);
                    index++;
                    cell = row.getCell(index);
                }
                datas.add(data);
            }
        }
        System.out.println("查询内容： " + keys);
        System.out.println("查询结果： " + datas.size());

        doSomething(datas);
    }

    private static void doSomething(List<Map> datas) throws SQLException {

        //createNewExcel(datas);
        writeFile(datas);
        //insertToDB(datas);
    }

    private static void createNewExcel(List<Map> datas) {

        System.out.println("开始计算数据");
        List<Map> newDatas = new ArrayList<>();
        Map<String, Integer> utdidMap = new HashMap<>();
        Map<String, Integer> accountMap = new HashMap<>();
        for (Map data : datas) {
            Map map = new HashMap();
            String utdid = valueFromMap(data, "utdid", "");
            System.out.println(utdid);
            addToMap(utdidMap, utdid);
            String idfa = valueFromMap(data, "idfa", "");
            Double roleIdDouble = Double.parseDouble(valueFromMap(data, "roleId", "0"));
            Long roleId = roleIdDouble.longValue();
            System.out.println(roleIdDouble + " === " + roleId);
            String roleName = valueFromMap(data, "roleName", "");
            String accountId = valueFromMap(data, "account_id", "");
            addToMap(accountMap, accountId);

            String serverId = valueFromMap(data, "server_id", "");
            String level = valueFromMap(data, "level", "");
            String rk = valueFromMap(data, "rk", "未打过排位");
            String os = valueFromMap(data, "os", "");
            String pay = valueFromMap(data, "pay", "0");
            String inform = valueFromMap(data, "inform", "");
            Double kills = Double.parseDouble(valueFromMap(data, "kills", "0"));
            String mvpNum = valueFromMap(data, "mvpNum", "0");
            Double battle = Double.parseDouble(valueFromMap(data, "battle", "0"));
            Double num1 = Double.parseDouble(valueFromMap(data, "num1", "0"));
            Double num2 = Double.parseDouble(valueFromMap(data, "num2", "0"));
            Double num3 = Double.parseDouble(valueFromMap(data, "num3", "0"));
            Double num4 = Double.parseDouble(valueFromMap(data, "num4", "0"));
            Double num5 = Double.parseDouble(valueFromMap(data, "num5", "0"));
            Double num6 = Double.parseDouble(valueFromMap(data, "num6", "0"));
            Double num7 = Double.parseDouble(valueFromMap(data, "num7", "0"));
            Double num8 = Double.parseDouble(valueFromMap(data, "num8", "0"));
            Double num9 = Double.parseDouble(valueFromMap(data, "num9", "0"));
            Double num10 = Double.parseDouble(valueFromMap(data, "num10", "0"));
            Double num11 = Double.parseDouble(valueFromMap(data, "num11", "0"));
            Double num12 = Double.parseDouble(valueFromMap(data, "num12", "0"));
            Double num13 = Double.parseDouble(valueFromMap(data, "num13", "0"));
            Double num14 = Double.parseDouble(valueFromMap(data, "num14", "0"));

            double avgKill = battle == 0 ? 0 : (kills*100 / battle) / 100;
            double avgSec3 = battle == 0 ? 0 : (num3*100 / battle) / 100;
            double avgSec9 = battle == 0 ? 0 : (num9*100 / battle) / 100;
            double avgSec10 = battle == 0 ? 0 : (num10*100 / battle) / 100;
            double avgSec11 = battle == 0 ? 0 : (num11*100 / battle) / 100;
            double avgSec14 = battle == 0 ? 0 : (num14*100 / battle) / 100;

            double danVal = num1*50 + num2*50 + num4*50 + num5*50 + num6*50 + num7*50 + num8*50 + num12 + num13*50
                + avgSec11 + avgSec10*25 + avgSec3*100 + avgSec9*200 + avgSec14*10;

            map.put("utdid", utdid);
            map.put("idfa", idfa);
            map.put("roleId", roleId);
            map.put("roleName", roleName);
            map.put("accountId", accountId);
            map.put("serverId", serverId);
            map.put("level", level);
            map.put("rk", rk);
            map.put("os", os);
            map.put("pay", pay);
            map.put("inform", inform);
            map.put("kills", kills);
            map.put("mvpNum", mvpNum);
            map.put("battle", battle);
            map.put("num1", num1);
            map.put("num2", num2);
            map.put("num3", num3);
            map.put("num4", num4);
            map.put("num5", num5);
            map.put("num6", num6);
            map.put("num7", num7);
            map.put("num8", num8);
            map.put("num9", num9);
            map.put("num10", num10);
            map.put("num11", num11);
            map.put("num12", num12);
            map.put("num13", num13);
            map.put("num14", num14);
            map.put("avgKill", avgKill);
            map.put("avgSec3", avgSec3);
            map.put("avgSec9", avgSec9);
            map.put("avgSec10", avgSec10);
            map.put("avgSec11", avgSec11);
            map.put("avgSec14", avgSec14);
            map.put("danVal", danVal);
            newDatas.add(map);
        }
        System.out.println("数据拉取完成，计算风险值");
        genDanVal(newDatas,utdidMap, accountMap);
        System.out.println("计算风险值完成, 开始生产CSV");
        writeCsv(newDatas);
        System.out.println("CSV已生成");
    }

    private static void writeCsv(List<Map> datas) {
        //String tmpPath = "D:\\t\\test\\ios_sec_data_0418.csv";
        Set<String> keys = datas.get(0).keySet();
        //String [] keyStrs = new String[keys.size()];
        String [] keyStrs = new String[37];
        keyStrs[0] = "utdid";
        keyStrs[1] = "idfa";
        keyStrs[2] = "accountId";
        keyStrs[3] = "serverId";
        keyStrs[4] = "roleId";
        keyStrs[5] = "roleName";
        keyStrs[6] = "level";
        keyStrs[7] = "rk";
        keyStrs[8] = "os";
        keyStrs[9] = "pay";
        keyStrs[10] = "inform";
        keyStrs[11] = "kills";
        keyStrs[12] = "mvpNum";
        keyStrs[13] = "num1";
        keyStrs[14] = "num2";
        keyStrs[15] = "num3";
        keyStrs[16] = "num4";
        keyStrs[17] = "num5";
        keyStrs[18] = "num6";
        keyStrs[19] = "num7";
        keyStrs[20] = "num8";
        keyStrs[21] = "num9";
        keyStrs[22] = "num10";
        keyStrs[23] = "num11";
        keyStrs[24] = "num12";
        keyStrs[25] = "num13";
        keyStrs[26] = "num14";
        keyStrs[27] = "avgKill";
        keyStrs[28] = "avgSec11";
        keyStrs[29] = "avgSec10";
        keyStrs[30] = "avgSec3";
        keyStrs[31] = "avgSec9";
        keyStrs[32] = "avgSec14";
        keyStrs[33] = "danVal";
        keyStrs[34] = "utdidNum";
        keyStrs[35] = "accountNum";
        keyStrs[36] = "battle";
        String csvHead = "";
        for (String key : keyStrs) {
            csvHead += key + ",";
        }
        csvHead = csvHead.substring(0, csvHead.length()-1);
        FileUtils.writeStr(csvHead, path_write);
        for (Map data : datas) {
            String csvContent = "";
            for (int i=0; i< keyStrs.length; i++) {
                csvContent += data.get(keyStrs[i]) + ",";
            }
            csvContent = csvContent.substring(0, csvContent.length()-1);
            FileUtils.writeStr(csvContent, path_write);
        }
        
    }

    private static void genDanVal(List<Map> datas, Map<String, Integer> utdidMap, Map<String, Integer> accountMap) {
        for (Map data : datas) {
            String utdid = (String)data.get("utdid");
            Integer utdidNum = utdidMap.get(utdid);
            String accountId = (String)data.get("accountId");
            Integer accountNum = accountMap.get(accountId);

            data.put("utdidNum", utdidNum);
            data.put("accountNum", accountNum);

            double danVal = (double)data.get("danVal");
            danVal = danVal * (0.9+utdidNum*0.1) + utdidNum*10-10;
            data.put("danVal", danVal);
        }
    }

    private static String valueFromMap(Map map, String key, String extra) {
        Object cell = map.get(key);

        if (cell==null) {

            //System.out.printf("NULL KEY : " + key );
            return extra;
        }

        String rs = ((XSSFCell)map.get(key)).toString();
        rs = rs.equalsIgnoreCase("\\N") ? extra : rs;
        return rs;
    }

    private static void addToMap(Map<String, Integer> map, String str) {
        Integer num = map.get(str);
        if (num == null) {
            num = 0;
        }
        num++;
        map.put(str, num);
    }

    private static void writeFile(List<Map> datas) {

        String rs = "insert into tmp_sec_data values ";
        int index = 0;
        for (Map map: datas) {
            index++;
            System.out.println("hhh");
            rs += "('" + map.get("utdid") + "',";
            rs += "'" + map.get("idfa") + "',";
            rs += "'" + map.get("account_id") + "',";
            rs += "'" + map.get("server_id") + "'," + index+ ", '20200417'),";
        }
        rs = rs.substring(0, rs.length()-1) + ";";
        System.out.println(rs);
    }

    private static void insertToDB(List<Map> datas) throws SQLException {
        String sql = "";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            long startms = System.currentTimeMillis();
            System.out.println("数据库连接url：" + url);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            System.out.println("连接成功");
            conn.setAutoCommit(false);

            sql = "insert into tb_compensate (`account_id`, content, version) values ";
            int index = 0;
            for (Map data : datas) {

                sql += String.format("('%s','%s','pbt1')", data.get("account"), JSON.toJSONString(data.get("content")));
                index++;
                if (index % 100 == 0) {
                    System.out.println("执行进度 " + index + " / " + datas.size());
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                    sql = "insert into tb_compensate (`account_id`, content, version) values ";
                } else {
                    sql += ",";
                }

            }
            pst = conn.prepareStatement(sql.substring(0, sql.length()-1));
            pst.execute();

            System.out.println("执行进度 " + index + " / " + datas.size());
            conn.commit();

            long endms = System.currentTimeMillis();
            System.out.println("执行完成数据库关闭！" + (endms - startms) + " ms");

            pst.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int doubleToInt(double num) {
        return ((Double) num).intValue();
    }
}
