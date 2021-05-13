package com.alibaba.game.test.db;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class TestMongo {

    public static void main(String[] args) {

        try {
            //mongodb://30.97.198.4:27017/
            String sURI = String.format("mongodb://%s:%d/%s","30.97.198.4", 27017, "test");
            MongoClientURI uri = new MongoClientURI(sURI);
            MongoClient mongoClient = new MongoClient(uri);
            //MongoClient mongoClient = new MongoClient("30.97.198.4", 27017);
            DB db = mongoClient.getDB("test");
            DBCollection coll = db.getCollection("pTest_2019913145515");
            //BasicDBObject doc = new BasicDBObject("name", "mongo").append("info", new BasicDBObject("ver", "2.0")).append("i", 100);
            //coll.insert(doc);

            long sstart = System.currentTimeMillis();
            List list = query(coll);
            System.out.println("query cost " +(System.currentTimeMillis() - sstart));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static List query(DBCollection coll) {

        BasicDBObject query = new BasicDBObject();
        BasicDBObject show = new BasicDBObject("_id", 0);
        //BasicDBObject show = new BasicDBObject("_id", 0).append("name", 1).append("info.ver", 1);

        DBCursor cursor = coll.find(query, show);

        List result = new ArrayList();
        try {
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                BasicDBObject rs0 = (BasicDBObject)((BasicDBObject)obj).get("response_0");
                result.add(obj);
                System.out.println(obj);
            }
        } finally {
            cursor.close();
        }

        return result;
    }
}
