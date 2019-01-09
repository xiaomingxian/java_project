package test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class MongoDBTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        //或者采用连接字符串
        // MongoClientURI mongoClientURI = new MongoClientURI("mongodb://root@localhost:27017");
        // MongoClient client = new MongoClient(mongoClientURI);
        MongoDatabase database = client.getDatabase("test");
        //连接collection
        MongoCollection<Document> collection1 = database.getCollection("collection1");
        //查询第一个文档
        Document first = collection1.find().first();
        String s = first.toJson();
        System.out.println(s);


    }
}



