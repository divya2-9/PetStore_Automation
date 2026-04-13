package clients;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * UserClient
 * Handles all API calls for the /user endpoints.
 */
public class UserClient extends BaseTest {

    private static final Logger log = LogManager.getLogger(UserClient.class);

    /**
     * POST /user — Create a new user
     * @param username  username
     * @param email     email (can be invalid for negative testing)
     * @param password  password
     */
    public Response createUser(String username, String email, String password) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", 0);
        body.put("username", username);
        body.put("firstName", "Test");
        body.put("lastName", "User");
        body.put("email", email);
        body.put("password", password);
        body.put("phone", "1234567890");
        body.put("userStatus", 1);

        log.info("Creating user username='{}' email='{}'", username, email);

        return given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post("/user")
                .then()
                .extract().response();
    }

    /**
     * GET /user/{username} — Fetch a user by username
     */
    public Response getUserByUsername(String username) {
        log.info("Fetching user with username='{}'", username);

        return given()
                .spec(requestSpec)
                .pathParam("username", username)
                .when()
                .get("/user/{username}")
                .then()
                .extract().response();
    }

    /**
     * GET /user/login — Login with credentials
     */
    public Response loginUser(String username, String password) {
        log.info("Logging in with username='{}'", username);

        return given()
                .spec(requestSpec)
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get("/user/login")
                .then()
                .extract().response();
    }
}