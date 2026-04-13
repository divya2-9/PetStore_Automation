package clients;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BaseTest
 * Holds the base URL and shared RequestSpecification.
 * All Client classes extend this — so base URL is defined ONCE here.
 */
public class BaseTest {

    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    protected static RequestSpecification requestSpec;

    static {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        log.info("BaseTest initialized. Base URL: {}", BASE_URL);
    }
}

