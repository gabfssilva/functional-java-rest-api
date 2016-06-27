package com.thedevpiece.converters;

import com.thedevpiece.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.function.Function;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class Converters {
    public static Function<User, Document> convertUserToDocument() {
        return (user) -> {
            final Document basicDBObject = new Document();

            basicDBObject.put("_id", new ObjectId(user.getId()));
            basicDBObject.put("username", user.getUsername());
            basicDBObject.put("email", user.getEmail());
            basicDBObject.put("occupation", user.getOccupation());
            basicDBObject.put("age", user.getAge());

            return basicDBObject;
        };
    }

    public static Function<Document, User> convertDocumentToUser() {
        return (db) ->
                new User(
                        db.get("_id").toString(),
                        db.getString("username"),
                        db.getString("email"),
                        db.getString("occupation"),
                        db.getInteger("age")
                );
    }
}
