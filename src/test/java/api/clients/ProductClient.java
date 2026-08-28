package api.clients;

import api.specifications.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductClient {
    private static final String PRODUCTS_LIST_PATH = "/api/productsList";

    public Response getProductsList() {
        return given()
                .spec(RequestSpecFactory.defaultSpec())
                .when()
                .get(PRODUCTS_LIST_PATH);
    }
}
