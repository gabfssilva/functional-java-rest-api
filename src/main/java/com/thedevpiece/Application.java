package com.thedevpiece;

import com.thedevpiece.api.UserApi;
import spark.Spark;

import static com.thedevpiece.utils.Props.prop;
import static java.lang.Integer.parseInt;
import static spark.Spark.port;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class Application {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        port(parseInt(prop("port", "9090")));
        UserApi.registerRoutes();
    }

    public static void stop() {
        Spark.stop();
    }
}
