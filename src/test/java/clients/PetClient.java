package clients;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class PetClient extends BaseTest {

    private static final Logger log = LogManager.getLogger(PetClient.class);
    private static final Random random = new Random();

    // Generates a unique 6-digit ID to avoid Long.MAX_VALUE overflow
    private long uniqueId() {
        return 100000 + random.nextInt(900000);
    }

    public Response createPet(String name, String status) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", uniqueId());
        body.put("name", name);
        body.put("status", status);
        log.info("Creating pet with name='{}' status='{}'", name, status);
        return given().spec(requestSpec).body(body).when().post("/pet").then().extract().response();
    }

    public Response createPetWithCategory(String name, String status, String categoryName) {
        Map<String, Object> category = new HashMap<>();
        category.put("id", uniqueId());
        category.put("name", categoryName);

        Map<String, Object> body = new HashMap<>();
        body.put("id", uniqueId());
        body.put("name", name);
        body.put("status", status);
        body.put("category", category);
        log.info("Creating pet name='{}' status='{}' category='{}'", name, status, categoryName);
        return given().spec(requestSpec).body(body).when().post("/pet").then().extract().response();
    }

    public Response getPetById(long petId) {
        log.info("Fetching pet with id={}", petId);
        return given().spec(requestSpec).pathParam("petId", petId)
                .when().get("/pet/{petId}").then().extract().response();
    }

    public Response updatePetStatus(long petId, String name, String newStatus) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", petId);
        body.put("name", name);
        body.put("status", newStatus);
        log.info("Updating pet id={} to status='{}'", petId, newStatus);
        return given().spec(requestSpec).body(body).when().put("/pet").then().extract().response();
    }

    public Response deletePet(long petId) {
        log.info("Deleting pet with id={}", petId);
        return given().spec(requestSpec).pathParam("petId", petId)
                .when().delete("/pet/{petId}").then().extract().response();
    }

    public Response findPetsByStatus(String status) {
        log.info("Finding pets with status='{}'", status);
        return given().spec(requestSpec).queryParam("status", status)
                .when().get("/pet/findByStatus").then().extract().response();
    }
}