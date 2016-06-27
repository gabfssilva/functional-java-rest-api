package com.thedevpiece.api;

import com.thedevpiece.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;
import spark.Request;

import java.util.function.Function;

import static com.thedevpiece.converters.Converters.convertDocumentToUser;
import static com.thedevpiece.converters.Converters.convertUserToDocument;
import static com.thedevpiece.converters.JsonConverter.convertToObject;
import static com.thedevpiece.converters.JsonConverter.jsonTransformer;
import static com.thedevpiece.db.CrudFunctions.findById;
import static com.thedevpiece.db.CrudFunctions.save;
import static com.thedevpiece.db.Mongo.users;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class UserApi {
    public static void registerRoutes() {
        post("/api/users", (request, response) -> {
            final Document document = convertToObject(User.class)
                    .andThen(u -> u.withId(ObjectId.get().toHexString()))
                    .andThen(convertUserToDocument())
                    .andThen((doc) -> save().apply(users, doc))
                    .apply(request.body());

            response.header("Location", "/api/users/" + document.get("_id").toString());
            response.status(201);
            return null;
        }, jsonTransformer);

        get("/api/users/:id", (request, response) -> {
            return ((Function<Request, String>) (req) -> req.params(":id"))
                    .andThen((id) -> findById().apply(users, id))
                    .andThen(convertDocumentToUser())
                    .apply(request);
        }, jsonTransformer);
    }
}
