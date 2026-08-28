package api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class HealthCheckSteps {
    private boolean frameworkInitialized = false;

    @Given("the API automation framework is initialized")
    public void theAPIAutomationFrameworkIsInitialized() {
        // Write code here that turns the phrase above into concrete actions
        frameworkInitialized = true;
    }

    @Then("the framework should be ready to run tests")
    public void theFrameworkShouldBeReadyToRunTests() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(frameworkInitialized);
    }
}
