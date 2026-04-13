package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features  = "src/test/resources/features",
        glue      = "stepDefinitions",
        plugin    = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/surefire-reports/TEST-cucumber.xml"
        },
        monochrome = true
)
public class TestRunner {
    // Empty — JUnit + Cucumber handle execution via annotations above
}