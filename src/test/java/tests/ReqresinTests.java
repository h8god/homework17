package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class ReqresinTests {

    @Test
    void createUser() {
        given()
                .body("{\"name\": \"test\", \"job\": \"test\" }")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);

    }

    @Test
    void updateUser() {
        given()
                .body("{\"name\": \"test\", \"job\": \"AQA\" }")
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200);
    }

    @Test
    void listUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[0].email", is("michael.lawson@reqres.in"));
    }

    @Test
    void missingPasswordLogin() {
        given()
                .body("{\"email\": \"peter@klaven\" }")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void registerUser() {
        given()
                .body("{     \"email\": \"eve.holt@reqres.in\",     \"password\": \"pistol\" }")
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is(notNullValue()));

    }

}
