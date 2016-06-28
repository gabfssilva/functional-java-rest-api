package com.thedevpiece.api;

import org.bson.Document;
import org.bson.types.ObjectId;
import spark.Request;
import spark.Route;

import java.util.function.Function;

import static com.thedevpiece.api.ResponseObject.response;
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
                        .andThen(Document::parse)
                        .andThen(doc -> doc.append("_id", ObjectId.get()))
                        .andThen(doc -> save.apply(users, doc))
                        .andThen(doc -> response().withLocation("/api/users/" + doc.get("_id").toString()))
                        .andThen(resp -> resp.withStatus(201))));

        get("/api/users/:id",
                route(((Function<Request, String>) (req) -> req.params(":id"))
                        .andThen(id -> findById.apply(users, id))
                        .andThen(doc -> response().withBody(doc.toJson()))
                        .andThen(resp -> resp.withStatus(200))));
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
