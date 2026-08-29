package api.clients;

import api.models.response.ProductListResponseDTO;
import api.specifications.RequestSpecFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.models.request.SearchProductRequestDTO;
import static io.restassured.RestAssured.given;

public class ProductClient {
    private static final String PRODUCTS_LIST_PATH = "/api/productsList";
    private static final String SEARCH_PRODUCTS_PATH = "/api/searchProduct";

    public Response getProductsList() {
        return given()
                .spec(RequestSpecFactory.defaultSpec())
                .when()
                .get(PRODUCTS_LIST_PATH);
    }

    private Response getSearchProductResponse(SearchProductRequestDTO searchProductRequest){
        return given()
                .spec(RequestSpecFactory.defaultSpec())
                .contentType(ContentType.JSON)
                .body(searchProductRequest)
                .when()
                .post(SEARCH_PRODUCTS_PATH);
    }

    public Response searchProducts(String keyword){
        SearchProductRequestDTO searchProductRequest = new SearchProductRequestDTO(keyword);
        return getSearchProductResponse(searchProductRequest);
    }

    public Response searchProductsWithoutKeyword(){
        SearchProductRequestDTO searchProductRequest = new SearchProductRequestDTO(null);
        return getSearchProductResponse(searchProductRequest);
    }

    public Response searchProductsWithEmptyKeyword(){
        SearchProductRequestDTO searchProductRequest = new SearchProductRequestDTO("");
        return getSearchProductResponse(searchProductRequest);
    }
}
