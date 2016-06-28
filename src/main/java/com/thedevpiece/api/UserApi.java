package com.thedevpiece.api;

import org.bson.types.ObjectId;
import spark.Request;
import spark.Route;

import java.util.function.Function;

import static com.thedevpiece.converters.JsonConverter.asDocument;
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
        post("/api/users",
                route(((Function<Request, String>) Request::body)
                        .andThen(asDocument)
                        .andThen(doc -> doc.append("_id", ObjectId.get()))
                        .andThen(doc -> save().apply(users, doc))
                        .andThen(doc -> new ResponseObject().withStatus(201).withLocation("/api/users/" + doc.get("_id").toString()))));

        get("/api/users/:id",
                route(((Function<Request, String>) (req) -> req.params(":id"))
                        .andThen(id -> findById().apply(users, id))
                        .andThen(doc -> new ResponseObject().withStatus(200).withBody(doc.toJson()))));
    }

    public static Route route(final Function<Request, ResponseObject> function) {
        return (request, response) -> {
            final ResponseObject responseObject = function.apply(request);
            response.header("Location", responseObject.getLocation());
            response.status(responseObject.getStatus());
            return responseObject.getBody() == null ? "" : responseObject.getBody();
        };
    }
}
