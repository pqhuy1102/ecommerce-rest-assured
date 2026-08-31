package api.clients;

import api.models.request.AccountRequestDTO;
import api.models.request.CredentialsRequestDTO;
import api.specifications.RequestSpecFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountClient {
    private static final String CREATE_ACCOUNT_PATH = "/api/createAccount";
    private static final String LOGIN_ACCOUNT_PATH = "/api/verifyLogin";
    private static final String GET_ACCOUNT_DETAILS_PATH = "/api/getUserDetailByEmail";
    private static final String UPDATE_ACCOUNT_PATH = "/api/updateAccount";
    private static final String DELETE_ACCOUNT_PATH = "/api/deleteAccount";


    public Response createNewAccount(AccountRequestDTO accountPayload){
        return given()
                .spec(RequestSpecFactory.defaultSpec())
                .contentType(ContentType.JSON)
                .body(accountPayload)
                .when()
                .post(CREATE_ACCOUNT_PATH);
    }

    public Response loginAccount(CredentialsRequestDTO credentialsPayload){
         return given()
                 .spec(RequestSpecFactory.defaultSpec())
                 .contentType(ContentType.JSON)
                 .body(credentialsPayload)
                 .when()
                 .post(LOGIN_ACCOUNT_PATH);
    }

    public Response getAccountDetails(String email){
        return given()
                .spec(RequestSpecFactory.defaultSpec())
                .queryParam("email", email)
                .when()
                .get(GET_ACCOUNT_DETAILS_PATH);
    }

    public Response updateInformation(AccountRequestDTO updatePayload){
        return given()
                .spec(RequestSpecFactory.defaultSpec())
                .contentType(ContentType.JSON)
                .body(updatePayload)
                .when()
                .put(UPDATE_ACCOUNT_PATH);
    }

    public Response deleteAccount(String email){
        return given()
                .spec(RequestSpecFactory.defaultSpec())
                .contentType(ContentType.JSON)
                .queryParam("email", email)
                .when()
                .delete(DELETE_ACCOUNT_PATH);
    }
}
