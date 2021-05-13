package com.alibaba.game.test.db;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class TestMongoData {

    public static void main(String[] args) {

        try {
            //mongodb://root:RtMN4AsPyerX@dds-j6c83a229a28a6041373-pub.mongodb.rds.aliyuncs.com:3717,dds-j6c83a229a28a6042389-pub.mongodb.rds.aliyuncs.com:3717/?replicaSet=mgset-14139713&authSource=admin
            //String sURI = String.format("mongodb://%s:%d/%s","30.97.198.4", 27017, "test");
            String sURI = "mongodb://root:RtMN4AsPyerX@dds-j6c83a229a28a6041373-pub.mongodb.rds.aliyuncs.com:3717,dds-j6c83a229a28a6042389-pub.mongodb.rds.aliyuncs.com:3717/?replicaSet=mgset-14139713&authSource=admin";
            MongoClientURI uri = new MongoClientURI(sURI);
            MongoClient mongoClient = new MongoClient(uri);
            //MongoClient mongoClient = new MongoClient("30.97.198.4", 27017);
            DB db = mongoClient.getDB("game");
            DBCollection coll = db.getCollection("role");
            //BasicDBObject doc = new BasicDBObject("name", "mongo").append("info", new BasicDBObject("ver", "2.0")).append("i", 100);
            //coll.insert(doc);

            long sstart = System.currentTimeMillis();
            Map map = showStructure(coll);
            System.out.println("query cost " +(System.currentTimeMillis() - sstart) + " , result map is ::: " + map);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static Map showStructure(DBCollection coll) {

        //BasicDBObject query = new BasicDBObject();
        BasicDBObject query = new BasicDBObject("account", "5ce2ce479d0be9b6608c4888");
        //BasicDBObject show = new BasicDBObject("_id", 0).append("name", 1).append("info.ver", 1);

        DBCursor cursor = coll.find(query);

        Map result = null;
        try {
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                if (obj instanceof Map) {
                    Map info = (Map)obj;
                    result = showStructure(info);
                    //break;
                }
            }
        } finally {
            cursor.close();
        }

        return result;
    }

    private static Map showStructure(Map info) {
        Map result = new HashMap();

        Set<String> keySet = info.keySet();
        List structures = new ArrayList();
        for (String key : keySet) {
            Map structureInfo = new HashMap();
            structureInfo.put("key", key);
            Object val = info.get(key);

            if (val instanceof Map) {
                if (!isExtraMap((Map) val, structureInfo)) {
                    structureInfo.put("type", "map");
                    structureInfo.put("val", showStructure((Map)val));
                };
            } else if (val instanceof List) {
                structureInfo.put("type", "list");
            } else if (val instanceof String) {
                structureInfo.put("type", "str");
            } else if (val instanceof Long || val instanceof Integer) {
                structureInfo.put("type", "num");
            } else {
                structureInfo.put("type", "obj");
            }
            structures.add(structureInfo);
        }

        result.put("structure", structures);
        return result;
    }

    private static boolean isExtraMap(Map info, Map structureInfo) {
        boolean result = false;
        Set<String> keyset = info.keySet();
        for (String key : keyset) {
            if (isNum(key)) {
                structureInfo.put("type", "extramap");
                structureInfo.put("val", mapStructure(key, info.get(key)));
                result = true;
                break;
            }
        }
        return result;
    }

    private static Map mapStructure(String key, Object obj) {

        Map result = new HashMap();
        if (obj instanceof Map) {
            Map info = (Map)obj;

            Set<String> keyset = info.keySet();
            int index = 0, order = 1;
            boolean isMatch = false;

            Map detail = new HashMap();
            for (String k : keyset) {
                Object val = info.get(k);
                String str = val.toString();
                if (key.equals(str)) {
                    isMatch = true;
                    result.put("order" + order, k);
                    break;
                }
            }
            if (isMatch) {
                result.put("key", "extra");
            }
            result.put("val", showStructure(info));

        } else {
            result.put("key", "normal");
            result.put("type", "base");
        }
        return result;
    }

    private static boolean isNum(String key) {
        boolean result = false;
        try {
            Integer.parseInt(key);
            result = true;
        } catch (Exception e) {
            System.out.println("tran error");
        }
        return result;
    }
}
