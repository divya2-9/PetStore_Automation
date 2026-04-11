package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetSteps {
    Response response;
    long petId;

    @Given("I have pet details with name {string} and status {string}")
    public void setPetDetails(String name, String status) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @When("I perform a POST request to {string}")
    public void createPet(String endpoint) {
        String body = "{\"name\": \"Buddy\", \"status\": \"available\"}";
        response = given().header("Content-Type", "application/json")
                .body(body).post(endpoint);
    }

    @Then("the status code should be {int}")
    public void verifyStatusCode(int code) {
        response.then().statusCode(code);
    }

    @And("I extract the pet ID from the response")
    public void extractId() {
        petId = response.jsonPath().getLong("id");
    }

    @When("I perform a GET request for that pet ID")
    public void getPet() {
        response = get("/pet/" + petId);
    }

    @Then("the name should be {string} and status {string}")
    public void verifyPetDetails(String name, String status) {
        response.then().body("name", equalTo(name)).body("status", equalTo(status));
    }

    @When("I perform a DELETE request for that pet ID")
    public void deletePet() {
        response = delete("/pet/" + petId);
    }

    @And("a GET request for that pet ID should return {int}")
    public void verifyDeleted(int code) {
        get("/pet/" + petId).then().statusCode(code);
    }
}