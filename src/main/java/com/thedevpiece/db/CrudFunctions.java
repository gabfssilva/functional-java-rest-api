package com.thedevpiece.db;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.function.BiFunction;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class CrudFunctions {
    public static BiFunction<MongoCollection<Document>, Document, Document> save = (collection, doc) -> {
        collection.insertOne(doc);
        return doc;
    };

    public static BiFunction<MongoCollection<Document>, String, Document> findById = (collection, id) -> collection.find(new Document("_id", new ObjectId(id))).first();
}
