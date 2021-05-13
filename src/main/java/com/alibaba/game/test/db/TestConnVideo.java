package com.alibaba.game.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnVideo {

    public static void main(String[] args) {
        //cn-video
        //String url = "jdbc:mysql://rm-uf6xyyjyh45fgxi9p1o.mysql.rds.aliyuncs.com:3306/cd_video?user=ccd_game&password=egTK8ev8RcEx&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL&transformedBitIsBoolean=true";
        String sql = "SELECT `name`, `role_name`, `id` from tb_video_info order by id";


        //dev
        String url = "jdbc:mysql://rm-uf68827a4sr4j2ck97o.mysql.rds.aliyuncs.com:3306/cd_video?user=ccd_game&password=cOlmXwu0uS2c&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL&transformedBitIsBoolean=true";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            long startms = System.currentTimeMillis();


            //System.out.println("数据库连接url："+url);
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                String name = rs.getString(1);
                String roleName = rs.getString(2);
                System.out.println(String.format("%s,____,%s", name, roleName));
                if (roleName == null || roleName.isEmpty()) {
                    Integer id = rs.getInt(3);
                    update(conn, name, id);
                }
            }

            long endms = System.currentTimeMillis();
            System.out.println("执行完成数据库关闭！ 耗时：" + (endms - startms) + " ms");

            pst.close();
            conn.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int update(Connection conn, String name, int id) throws SQLException {
        String [] infos = name.split("_");
        String roleName = "";
        Integer result = 0;
        if (infos.length <= 0) {
            return 0;
        } else {
            roleName = infos[0];
            for (int i=1; i< infos.length; i++) {
                String info = infos[i];
                if (info.equalsIgnoreCase("win")) {
                    result = 1;
                    break;
                } else if (info.equalsIgnoreCase("lose")) {
                    result = 0;
                    break;
                }
            }
        }
        String sql = String.format("update tb_video_info set role_name='%s', result=%s where id=%s", roleName, result, id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        System.out.println(sql);
        return 1;
    }
}
