package com.alibaba.game.test.db;

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
import java.util.Properties;

public class TestMysql {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException
    {

        //cn-video
        String url = "jdbc:mysql://rm-uf6xyyjyh45fgxi9p1o.mysql.rds.aliyuncs.com:3306/cd_video?user=ccd_game&password=egTK8ev8RcEx&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL&transformedBitIsBoolean=true";
        String sql = "SELECT * from tb_user limit 1";
        int index = 5;

        //video
        String url1 = "jdbc:mysql://rm-j6c5j14ejuv0h3j995o.mysql.rds.aliyuncs.com:3306/cd_video?user=ccd_game&password=0ASGJNgM2AB6&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL&transformedBitIsBoolean=true";
        String sql1 = "SELECT * from tb_video_info ORDER BY id desc limit 1";
        int index1 = 20;

        //dev
        String url2 = "jdbc:mysql://rm-uf68827a4sr4j2ck97o.mysql.rds.aliyuncs.com:3306/ice?user=ccd_game&password=cOlmXwu0uS2c&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL&transformedBitIsBoolean=true";
        String sql2 = "SELECT * FROM tb_test WHERE `key` = 'test13' LIMIT 1";
        int index2 = 7;

        //10184
        String url3 = "jdbc:mysql://rm-j6cl626wri02icwk2mo.mysql.rds.aliyuncs.com:3306/cd_game?user=ccd_game&password=wK48ENhGSgXg&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL&transformedBitIsBoolean=true";
        String sql3 = "SELECT * from tb_account_info ORDER BY id desc LIMIT 1";
        int index3 = 8;

        //cn-wings
        String url4 = "jdbc:mysql://rm-uf69jg4gqv758s058xo.mysql.rds.aliyuncs.com:3306/cd_octopod?user=ccd_game&password=BGjkBrQ1Id7r&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL&transformedBitIsBoolean=true";
        String sql4 = "SELECT * from tb_server ORDER BY id desc LIMIT 1";
        int index4 = 11;
        String goodsId="",goodsName="";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            long startms = System.currentTimeMillis();


            //System.out.println("数据库连接url："+url);
            Class.forName("com.mysql.jdbc.Driver");
            //Connection conn1 = DriverManager.getConnection(url1);
            //PreparedStatement pst1 = conn.prepareStatement(sql1);
            //rs = pst1.executeQuery();
            //while(rs.next()) {
            //    Object o = rs.getObject(index1);
            //    System.out.println("--------" + o);
            //}

            //cn-video
            execute(url,sql,index);
            //video
            execute(url1,sql1,index1);
            //dev
            execute(url2,sql2,index2);
            //10184
            execute(url3,sql3,index3);
            //cn-wings
            execute(url4,sql4,index4);

            long endms = System.currentTimeMillis();
            System.out.println("执行完成数据库关闭！ 耗时：" + (endms - startms) + " ms");

            pst.close();
            conn.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int execute(String url, String sql, int index) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pst1 = conn.prepareStatement(sql);
        ResultSet rs = pst1.executeQuery();
        while(rs.next()) {
            Object o = rs.getObject(index);
            System.out.println("--------" + o);
        }
        return 0;
    }
}
