package com.thedevpiece.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.thedevpiece.utils.Props.prop;
import static java.lang.Integer.parseInt;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class Mongo {
    public static final MongoClient mongoClient = mongoClient();
    public static final MongoDatabase appDataBase = db("app");
    public static final MongoCollection<Document> users = collection(appDataBase, "users");

    public static MongoClient mongoClient() {
        return new MongoClient(prop("mongo.db.hostname", "localhost"), parseInt(prop("mongo.db.port", "27017")));
    }

    public static MongoDatabase db(String db) {
        return mongoClient.getDatabase(db);
    }

    public static MongoCollection<Document> collection(MongoDatabase db, String collection) {
        return db.getCollection(collection);
    }
}
