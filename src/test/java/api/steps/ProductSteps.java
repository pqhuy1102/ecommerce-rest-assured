package api.steps;

import api.clients.ProductClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;


public class ProductSteps {
    private final ProductClient productClient;
    private Response response;

    public ProductSteps(ProductClient productClient){
        this.productClient = productClient;
    }

    @When("the client requests all products")
    public void theClientRequestsAllProducts() {
        response = productClient.getProductsList();
    }

    @Then("the product request should be successful")
    public void theProductRequestShouldBeSuccessful() {
        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200));
    }

    @And("the product list should not be empty")
    public void theProductListShouldNotBeEmpty() {
        response.then()
                .body("products", not(empty()));
    }
}
