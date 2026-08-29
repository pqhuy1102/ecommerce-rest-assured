package api.steps;

import api.clients.ProductClient;
import api.context.ScenarioContext;
import api.models.response.ApiErrorResponseDTO;
import api.models.response.ProductListResponseDTO;
import api.models.response.ProductResponseDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Locale;

import static api.specifications.ResponseSpecFactory.okJsonResponse;
import static org.testng.Assert.*;


public class ProductSteps {
    private final ProductClient productClient;
    private final ScenarioContext scenarioContext;
    private Response response;
    private String searchKeyword;

    public ProductSteps(ProductClient productClient, ScenarioContext scenarioContext ){
        this.productClient = productClient;
        this.scenarioContext = scenarioContext;
    }

    @When("the client requests all products")
    public void theClientRequestsAllProducts() {
        response = productClient.getProductsList();
        scenarioContext.setLatestResponse(response);
    }

    @Then("the product request should be successful")
    public void theProductRequestShouldBeSuccessful() {
        scenarioContext.getLatestResponse().then()
                .spec(okJsonResponse());
        ProductListResponseDTO productListResponseBody = scenarioContext.getLatestResponse().as(ProductListResponseDTO.class);

        assertEquals(productListResponseBody.responseCode(), 200);
    }

    @And("the product list should not be empty")
    public void theProductListShouldNotBeEmpty() {
        ProductListResponseDTO productListResponseBody = scenarioContext.getLatestResponse().as(ProductListResponseDTO.class);

        assertFalse(productListResponseBody.products().isEmpty());
    }

    @When("the client searches for products by using keyword {string}")
    public void theClientSearchesForProductsByUsingKeyword(String keyword) {
        searchKeyword = keyword;
        response = productClient.searchProducts(keyword);
        scenarioContext.setLatestResponse(response);
    }

    @Then("the product search request should be successful")
    public void theProductSearchRequestShouldBeSuccessful() {
       scenarioContext.getLatestResponse().then()
               .spec(okJsonResponse());

       ProductListResponseDTO productListResponse = scenarioContext.getLatestResponse().as(ProductListResponseDTO.class);
       assertEquals(productListResponse.responseCode(), 200);
    }

    @And("the returned products should match the search keyword")
    public void theReturnedProductsShouldMatchTheSearchKeyword() {
        ProductListResponseDTO bodyResponse = scenarioContext.getLatestResponse().as(ProductListResponseDTO.class);

        assertFalse(bodyResponse.products().isEmpty());

        String normalizeKeyword = searchKeyword.toLowerCase(Locale.ROOT);

        boolean allProductsMatch = bodyResponse.products()
                .stream()
                .map(ProductResponseDTO::name)
                .map(name -> name.toLowerCase(Locale.ROOT))
                .allMatch(name -> name.contains(normalizeKeyword));

        assertTrue(allProductsMatch);
    }

    @When("the client searches for products without a keyword")
    public void theClientSearchesForProductsWithoutAKeyword() {
        response = productClient.searchProductsWithoutKeyword();
        scenarioContext.setLatestResponse(response);
    }

    @Then("the product search request should be rejected")
    public void theProductSearchRequestShouldBeRejected() {
       scenarioContext.getLatestResponse()
               .then()
               .statusCode(400);

        ApiErrorResponseDTO errorBodyResponse = scenarioContext.getLatestResponse().as(ApiErrorResponseDTO.class);
        assertEquals(errorBodyResponse.responseCode(), 400, "Business errors");
        assertTrue(errorBodyResponse.message().contains("search_product parameter is missing"));
    }

    @When("the client searches for products with empty keyword")
    public void theClientSearchesForProductsWithEmptyKeyword() {
        response = productClient.searchProductsWithEmptyKeyword();
        scenarioContext.setLatestResponse(response);
    }

    @Then("the product search request should be error")
    public void theProductSearchRequestShouldBeError() {
        scenarioContext.getLatestResponse()
                .then()
                .statusCode(400);

        ApiErrorResponseDTO errorBodyResponse = scenarioContext.getLatestResponse().as(ApiErrorResponseDTO.class);
        assertEquals(errorBodyResponse.responseCode(), 400, "Business errors");
        assertTrue(errorBodyResponse.message().contains("search_product parameter is missing"));
    }
}
