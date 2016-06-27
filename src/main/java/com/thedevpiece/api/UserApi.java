package com.thedevpiece.api;

import com.thedevpiece.model.User;
import spark.Request;

import java.util.function.Function;

import static com.thedevpiece.converters.Converters.convertDocumentToUser;
import static com.thedevpiece.converters.Converters.convertUserToDocument;
import static com.thedevpiece.converters.JsonConverter.convertToObject;
import static com.thedevpiece.db.CrudFunctions.findById;
import static com.thedevpiece.db.CrudFunctions.save;
import static com.thedevpiece.db.Mongo.users;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class UserApi {
    public static void routes() {
        post("/api/users", (request, response) ->
                convertToObject(User.class)
                        .andThen(convertUserToDocument())
                        .andThen((doc) -> save().apply(users, doc))
                        .andThen(convertDocumentToUser())
                        .apply(request.body()));

        get("/api/users/:id", (request, response) -> {
            Function<Request, String> extractId = (req) -> req.params("id");

            return extractId
                    .andThen((id) -> findById().apply(users, id))
                    .andThen(convertDocumentToUser())
                    .apply(request);
        });
    }
}
