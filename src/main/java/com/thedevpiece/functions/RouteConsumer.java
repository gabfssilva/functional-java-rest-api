package com.thedevpiece.functions;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.function.BiConsumer;

import static com.thedevpiece.utils.ExceptionUtils.doThrow;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
@FunctionalInterface
public interface RouteConsumer extends Route, BiConsumer<Request, Response> {
    default void accept(Request request, Response response) {
        try {
            handle(request, response);
        } catch (Exception e) {
            doThrow(e);
        }
    }
}
