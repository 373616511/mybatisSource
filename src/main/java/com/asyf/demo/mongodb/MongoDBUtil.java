package com.asyf.demo.mongodb;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MongoDB工具类 Mongo实例代表了一个数据库连接池，即使在多线程的环境中，一个Mongo实例对我们来说已经足够了<br>
 * 注意Mongo已经实现了连接池，并且是线程安全的。 <br>
 * 设计为单例模式， 因 MongoDB的Java驱动是线程安全的，对于一般的应用，只要一个Mongo实例即可，<br>
 * Mongo有个内置的连接池（默认为10个） 对于有大量写和读的环境中，为了确保在一个Session中使用同一个DB时，<br>
 * DB和DBCollection是绝对线程安全的<br>
 */
public enum MongoDBUtil {

    /**
     * 定义一个枚举的元素，它代表此类的一个实例，是单例的
     */
    instance;

    /**
     * MongoClient的实例代表数据库连接池，是线程安全的，可以被多线程共享，客户端在多线程条件下仅维持一个实例即可
     * Mongo是非线程安全的，目前mongodb API中已经建议用MongoClient替代Mongo
     */
    private MongoClient mongoClient;

    static {
        System.out.println("===============MongoDBUtil初始化开始========================");
        //初始化
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        // options.autoConnectRetry(true);// 自动重连true
        // options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
        builder.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
        builder.connectTimeout(15000);// 连接超时，推荐>3000毫秒
        builder.maxWaitTime(5000); //
        builder.socketTimeout(0);// 套接字超时时间，0无限制
        builder.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
        builder.writeConcern(WriteConcern.SAFE);//
        MongoClientOptions options = builder.build();
        //获取属性文件
        CompositeConfiguration config = new CompositeConfiguration();
        try {
            config.addConfiguration(new PropertiesConfiguration("mongodb.properties"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        // 从配置文件中获取属性值
        String host = config.getString("host");
        int port = config.getInt("port");
        //指定服务器地址和端口号码
        ServerAddress serverAddress = new ServerAddress(host, port);
        //创建实例
        instance.mongoClient = new MongoClient(serverAddress, options);

        // or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
        // List<ServerAddress> listHost = Arrays.asList(new ServerAddress("localhost", 27017),new ServerAddress("localhost", 27018));
        // instance.mongoClient = new MongoClient(listHost);

        // 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
        // boolean auth = db.authenticate(myUserName, myPassword);
        System.out.println("===============MongoDBUtil初始化完成========================");
    }

    // ------------------------------------共用方法---------------------------------------------------

    /**
     * 获取DB实例 - 指定DB
     *
     * @param dbName
     * @return
     */
    public MongoDatabase getDB(String dbName) {
        if (dbName != null && !"".equals(dbName)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            return database;
        }
        return null;
    }

    /**
     * 获取collection对象 - 指定Collection
     *
     * @param collName
     * @return
     */
    public MongoCollection<Document> getCollection(String dbName, String collName) {
        if (null == collName || "".equals(collName)) {
            return null;
        }
        if (null == dbName || "".equals(dbName)) {
            return null;
        }
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
        return collection;
    }

    /**
     * 查询DB下的所有表名
     */
    public List<String> getAllCollections(String dbName) {
        MongoIterable<String> colls = getDB(dbName).listCollectionNames();
        List<String> _list = new ArrayList<String>();
        for (String s : colls) {
            _list.add(s);
        }
        return _list;
    }

    /**
     * 获取所有数据库名称列表
     *
     * @return
     */
    public MongoIterable<String> getAllDBNames() {
        MongoIterable<String> s = mongoClient.listDatabaseNames();
        return s;
    }

    /**
     * 删除一个数据库
     */
    public void dropDB(String dbName) {
        getDB(dbName).drop();
    }

    /**
     * 查找对象 - 根据主键_id
     *
     * @param id
     * @return
     */
    public Document findById(MongoCollection<Document> coll, String id) {
        /*ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }*/
        Document myDoc = coll.find(Filters.eq("id", id)).first();
        return myDoc;
    }

    /**
     * 统计数
     */
    public int getCount(MongoCollection<Document> coll) {
        int count = (int) coll.count();
        return count;
    }

    /**
     * 条件查询
     */
    public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        return coll.find(filter).iterator();
    }

    /**
     * 分页查询
     */
    public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo, int pageSize) {
        Bson orderBy = new BasicDBObject("_id", 1);
        return coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
    }

    /**
     * 覆盖插入
     *
     * @param coll
     * @param document
     */
    public void insertOne(MongoCollection<Document> coll, Document document) {
        String id = null;
        try {
            id = (String) document.get("id");
            Document byId = findById(coll, id);
            if (byId != null) {
                deleteById(coll, id);
            }
            coll.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过ID删除
     *
     * @param coll
     * @param id
     * @return
     */
    public int deleteById(MongoCollection<Document> coll, String id) {
        int count = 0;/*;
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }*/
        Bson filter = Filters.eq("id", id);
        DeleteResult deleteResult = coll.deleteOne(filter);
        count = (int) deleteResult.getDeletedCount();
        return count;
    }

    /**
     * FIXME
     *
     * @param coll
     * @param id
     * @param newdoc
     * @return
     */
    public Document updateById(MongoCollection<Document> coll, String id, Document newdoc) {
       /* ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }*/
        Bson filter = Filters.eq("id", id);
        // coll.replaceOne(filter, newdoc); // 完全替代
        coll.updateOne(filter, new Document("$set", newdoc));
        return newdoc;
    }

    public void dropCollection(String dbName, String collName) {
        getDB(dbName).getCollection(collName).drop();
    }

    /**
     * 关闭Mongodb
     */
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

    /**
     * 测试入口
     *
     * @param args
     */
    public static void main(String[] args) {

        String dbName = "test";
        String collName = "test";
        MongoCollection<Document> coll = MongoDBUtil.instance.getCollection(dbName, collName);

        // 插入多条
        for (int i = 1; i <= 4; i++) {
            Document doc = new Document();
            doc.append("id", "test_id2");
            doc.put("name", "zhoulf222");
            doc.put("school", "NEFU" + i);
            Document interests = new Document();
            interests.put("game", "game" + i);
            interests.put("ball", "ball" + i);
            doc.put("interests", interests);
            System.out.println(doc.get("id"));
            //MongoDBUtil.instance.insertOne(coll, doc);
        }
        //根据id删除
        //MongoDBUtil.instance.deleteById(coll, "test_id");

        // // 根据ID查询
        String id = "test_id";
        Document doc = MongoDBUtil.instance.findById(coll, id);
        System.out.println("查询结果：" + doc);

        // 查询多个
        // MongoCursor<Document> cursor1 = coll.find(Filters.eq("name", "zhoulf")).iterator();
        // while (cursor1.hasNext()) {
        // org.bson.Document _doc = (Document) cursor1.next();
        // System.out.println(_doc.toString());
        // }
        // cursor1.close();

        // 查询多个
        // MongoCursor<Person> cursor2 = coll.find(Person.class).iterator();

        // 删除数据库
        // MongoDBUtil2.instance.dropDB("testdb");

        // 删除表
        // MongoDBUtil2.instance.dropCollection(dbName, collName);

        // 修改数据
        //String id = "556949504711371c60601b5a";
        Document newdoc = new Document();
        newdoc.put("name", "时候");
        MongoDBUtil.instance.updateById(coll, id, newdoc);

        // 统计表
        // System.out.println(MongoDBUtil.instance.getCount(coll));

        // 条件查询
        Bson filter = Filters.eq("id", "test_id");
        Bson filter2 = Filters.eq("name", "zhoulf222");
        //or查询
        Bson b = Filters.or(Arrays.asList(filter, filter2));
        MongoCursor<Document> documentMongoCursor = MongoDBUtil.instance.find(coll, b);

        while (documentMongoCursor.hasNext()) {
            Document document = documentMongoCursor.next();
            System.out.println("结果是：" + document);
        }

    }

}
