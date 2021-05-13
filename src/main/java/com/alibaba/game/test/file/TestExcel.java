package com.alibaba.game.test.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class TestExcel{

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://rm-j6c9w51yq4ly3a370ao.mysql.rds.aliyuncs.com:3306/cd_game?user=ccd_game&password=6DduRkJ3fbV8W3ah&allowMultiQueries=true";
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

            String startStr = "2019-07-26 20:00:00";
            String endStr = "2019-07-27 20:00:00";
            sql = "SELECT uid as playId, role_name, context, gmt_create as time from tb_user_feedback "
                + "WHERE gmt_create < '" + endStr + "' and gmt_create > '" + startStr + "'"
                + "ORDER BY id desc;";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            List<Map<String, Object>> list = new ArrayList<>();
            while (rs.next()) {
                poshInfo(list,rs);
            }
            String fileName = "D:\\用户反馈_" + endStr.substring(0,10).replaceAll("-", "") + ".xls";
            createExcel(fileName, "用户反馈",list);
            //createExcel(path + "百度文库.xls", "百度文库",exchangeCodes2);
            //createExcel(path + "迅雷.xls", "迅雷",exchangeCodes3);

            long endms = System.currentTimeMillis();
            System.out.println("执行完成数据库关闭！" + (endms - startms) + " ms");

            pst.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void poshInfo(List list,ResultSet rs) throws SQLException {

        Map<String, Object> map = new HashMap<>();
        map.put("playId", rs.getInt(1));
        map.put("roleName", rs.getString(2));
        map.put("context", rs.getString(3));
        map.put("time", rs.getString(4));
        list.add(map);

    }

    /**
     * 创建新excel.
     * @param fileDir  excel的路径
     * @param sheetName 要创建的表格索引
     * @param exchangeCodes
     */
    public static void createExcel(String fileDir,String sheetName, List<Map<String, Object>> exchangeCodes){
        //创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        wb.createSheet(sheetName);
        //新建文件
        FileOutputStream out = null;
        try {
            //title CellStyle


            CellStyle csTitle = titleStyle(wb);
            CellStyle normal = contentStyle(wb);
            //添加表头
            Sheet sheet = wb.getSheet(sheetName);
            sheet.setDefaultColumnWidth(20);
            Row row = sheet.createRow(0);    //创建第一行
            row.setHeightInPoints((short)20);
            createCell(row, 1, "playId", csTitle);
            createCell(row, 2, "roleName", csTitle);
            createCell(row, 3, "context", csTitle);
            createCell(row, 0, "createTime", csTitle);
            int index = 0;
            for(Map<String,Object> info : exchangeCodes) {
                index++;
                row = sheet.createRow(index);
                createCell(row, 1, info.get("playId").toString(),normal);
                createCell(row, 2, info.get("roleName").toString(), normal);
                createCell(row, 3, info.get("context").toString(), normal);
                createCell(row, 0, info.get("time").toString(), normal);
                //cell = row.createCell(0);
                //cell.setCellValue("" + info.get("code"));
            }
            out = new FileOutputStream(fileDir);
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static CellStyle titleStyle(HSSFWorkbook wb) {
        CellStyle csTitle = wb.createCellStyle();
        Font fTitle = wb.createFont();
        fTitle.setFontHeightInPoints((short)15);
        fTitle.setColor((short)Font.COLOR_NORMAL);
        csTitle.setFont(fTitle);
        csTitle.setBorderBottom(BorderStyle.THIN);
        csTitle.setBorderLeft(BorderStyle.THIN);
        csTitle.setBorderTop(BorderStyle.THIN);
        return csTitle;
    }

    private static CellStyle contentStyle(HSSFWorkbook wb) {
        CellStyle csTitle = wb.createCellStyle();
        Font fTitle = wb.createFont();
        fTitle.setColor((short)Font.COLOR_NORMAL);
        csTitle.setFont(fTitle);
        csTitle.setBorderLeft(BorderStyle.THIN);
        return csTitle;
    }

    public static void createCell(Row row, int index, String str, CellStyle csTitle){
        Cell cell = row.createCell(index);
        cell.setCellValue(str);
        if (csTitle != null) {
            cell.setCellStyle(csTitle);
        }
    }



}