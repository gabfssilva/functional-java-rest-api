package com.thedevpiece.api;

import com.jayway.restassured.RestAssured;
import com.thedevpiece.Application;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.IStopable;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static com.thedevpiece.Application.run;
import static org.junit.Assert.*;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class UserApiTest {
    private static IStopable mongod = null;

    @BeforeClass
    public static void beforeAll() throws IOException {
        final MongodStarter starter = MongodStarter.getDefaultInstance();

        final IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.V3_3)
                .net(new Net(27017, Network.localhostIsIPv6()))
                .build();

        final MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
        mongod = mongodExecutable.start();
        run();
    }

    @AfterClass
    public static void afterAll() {
        Application.stop();
        mongod.stop();
    }

    @Test
    public void assert_user_creation_is_working() {
        RestAssured
                .given()
                .body("{\n" +
                        "   \"username\":\"gabfssilva\",\n" +
                        "   \"email\":\"gabfssilva@gmail.com\",\n" +
                        "   \"occupation\":\"Software Engineer\",\n" +
                        "   \"age\":23\n" +
                        "}")
                .and()
                .contentType("application/json")
                .when()
                .post("http://localhost:9090/api/users")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void assert_user_search_is_working() {
        String location = RestAssured.given()
                .body("{\n" +
                        "   \"username\":\"gabfssilva\",\n" +
                        "   \"email\":\"gabfssilva@gmail.com\",\n" +
                        "   \"occupation\":\"Software Engineer\",\n" +
                        "   \"age\":23\n" +
                        "}")
                .and()
                .contentType("application/json")
                .when()
                .post("http://localhost:9090/api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .extract()
                .header("Location");

        RestAssured
                .when()
                .get("http://localhost:9090" + location)
                .then()
                .assertThat()
                .statusCode(200);
    }
}