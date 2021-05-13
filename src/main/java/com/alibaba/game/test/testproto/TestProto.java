package com.alibaba.game.test.testproto;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.game.test.testproto.BattleLogInfoDTO.BattleLogAttrChangeInfo;
import com.alibaba.game.test.testproto.BattleLogInfoDTO.BattleLogBaseInfo;
import com.alibaba.game.test.testproto.BattleLogInfoDTO.BattleLogInfoList;

public class TestProto {

    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream("D:\\t\\test\\testdd.bytes");
            byte[] byteArray = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte buff[] = new byte[1024];
            int read = 0;
            while( ( read = is.read(buff) ) > 0 ) {
                baos.write(buff, 0, read);
            }
            byteArray = baos.toByteArray();
            BattleLogInfoList infoList = BattleLogInfoList.parseFrom(byteArray);
            BattleLogBaseInfo dto = infoList.getInfosList().get(0);

            BattleLogAttrChangeInfo attrChangeInfo = dto.getObject().unpack(BattleLogAttrChangeInfo.class);
            String json = JSON.toJSONString(attrChangeInfo);
            Map<String, Object> map = createInfo(attrChangeInfo);
            System.out.println("...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> createInfo(BattleLogAttrChangeInfo info) {
        Map map = new HashMap();
        Class c1 = info.getClass();
        Class c2 = c1.getSuperclass();
        Field [] f1 = c1.getDeclaredFields();
        Field [] f2 = c2.getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        for (Field f : f1) {
            boolean exist = false;
            for (Field ff : f2) {
                if (f.getName().equals(ff.getName())) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                fields.add(f);
            }
        }
        return map;
    }
}
