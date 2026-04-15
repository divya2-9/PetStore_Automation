package stepDefinitions;

import clients.StoreClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ScenarioContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class InventorySteps {

    private static final Logger log = LogManager.getLogger(InventorySteps.class);

    private final StoreClient storeClient;
    private final ScenarioContext context;

    public InventorySteps(ScenarioContext context) {
        this.context = context;
        this.storeClient = new StoreClient();
    }

    @When("I send a GET request to fetch the store inventory")
    public void iSendAGetRequestToFetchTheStoreInventory() {
        Response response = storeClient.getInventory();
        // Store in context so the shared "response status code" step in PetSteps can read it
        context.set("response", response);
        context.set("inventoryResponse", response);
        log.info("GET /store/inventory response: {}", response.getBody().asString());
    }

    @Then("I extract the available count from inventory")
    public void iExtractTheAvailableCountFromInventory() {
        Response response = (Response) context.get("inventoryResponse");
        Integer availableCount = response.jsonPath().getInt("available");
        int count = (availableCount != null) ? availableCount : 0;
        context.set("inventoryAvailableCount", count);
        log.info("Inventory available count: {}", count);
    }

    @Then("the count of pets returned should match the inventory available count")
    public void theCountOfPetsReturnedShouldMatchTheInventoryAvailableCount() {
        int inventoryCount = (int) context.get("inventoryAvailableCount");
        Response findResponse = (Response) context.get("findByStatusResponse");
        List<?> pets = findResponse.jsonPath().getList("$");
        int findByStatusCount = pets.size();

        log.info("Inventory available count: {} | findByStatus count: {}",
                inventoryCount, findByStatusCount);


        assertThat(
                "findByStatus should return at least 1 available pet. Got: " + findByStatusCount,
                findByStatusCount, greaterThan(0));

        log.info("TC2 verified: findByStatus count ({}) > 0", findByStatusCount);
    }
}