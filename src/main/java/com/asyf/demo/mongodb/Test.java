package com.asyf.demo.mongodb;

import com.asyf.model.User;
import com.asyf.util.SerializeUtil;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BSON;
import org.bson.Document;
import org.bson.types.Binary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully");

            //mongoDatabase.createCollection("test");
            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            User user = new User();
            user.setId("10001");
            user.setName("zhanbu");
            byte[] bytes = SerializeUtil.serialize(user);
            Document document = new Document("title", "serialize").append("user", bytes);
            //删除符合条件的第一个文档
            collection.deleteOne(Filters.eq("title", "serialize"));
            collection.insertOne(document);
            //  BasicDBObject dbo = new BasicDBObject();
            //dbo.put("user",bytes);
            //条件查询
            FindIterable<Document> findIterable = collection.find(new Document("title", new Document("$eq", "serialize")));
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                Document d = mongoCursor.next();
                Object obj = d.get("user");
                Binary binary = (Binary) obj;
                byte[] data = binary.getData();
                User u2 = (User) SerializeUtil.unserialize(data);
                System.err.println(user.equals(u2));
                System.err.println(user.hashCode() + "---" + user);
                System.err.println(u2.hashCode() + "---" + u2);
                System.out.println(d);
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }



    public static void insert() {

        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        System.out.println("Connect to database successfully");
        Document document = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("by", "Fly");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        //collection.insertMany(documents);
        System.out.println("文档插入成功");
        //mongoDatabase.createCollection("test");
        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }
}
