package clients;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

/**
 * StoreClient
 * Handles all API calls for the /store endpoints.
 */
public class StoreClient extends BaseTest {

    private static final Logger log = LogManager.getLogger(StoreClient.class);

    /**
     * GET /store/inventory — Returns a map of status counts
     * e.g. { "available": 120, "sold": 45, "pending": 10 }
     */
    public Response getInventory() {
        log.info("Fetching store inventory");

        return given()
                .spec(requestSpec)
                .when()
                .get("/store/inventory")
                .then()
                .extract().response();
    }
}
