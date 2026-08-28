package api.steps;

import api.clients.ProductClient;
import api.context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static api.specifications.ResponseSpecFactory.okJsonResponse;

import static org.hamcrest.Matchers.*;


public class ProductSteps {
    private final ProductClient productClient;
    private final ScenarioContext scenarioContext;
    private Response response;

    public ProductSteps(ProductClient productClient, ScenarioContext scenarioContext ){
        this.productClient = productClient;
        this.scenarioContext = scenarioContext;
    }

    @When("the client requests all products")
    public void theClientRequestsAllProducts() {
        response = productClient.getProductsList();
    }

    @Then("the product request should be successful")
    public void theProductRequestShouldBeSuccessful() {
        response.then()
                .spec(okJsonResponse())
                .body("responseCode", equalTo(200));
    }

    @And("the product list should not be empty")
    public void theProductListShouldNotBeEmpty() {
        response.then()
                .body("products", not(empty()));
    }
}
