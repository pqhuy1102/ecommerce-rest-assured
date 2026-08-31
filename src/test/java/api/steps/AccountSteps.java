package api.steps;

import api.clients.AccountClient;
import api.context.AccountContext;
import api.context.ScenarioContext;
import api.data.AccountDataFactory;
import api.models.request.AccountRequestDTO;
import api.models.request.CredentialsRequestDTO;
import api.models.response.LoginResponseDTO;
import api.models.response.MessageResponseDTO;
import api.models.response.UserDetailsResponseDTO;
import api.models.response.UserResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.util.Locale;

import static api.data.AccountDataFactory.createUniqueAccount;
import static org.testng.Assert.*;

public class AccountSteps {
    private final AccountContext accountContext;
    private final ScenarioContext scenarioContext;
    private final AccountClient accountClient;
    private Response response;
    private AccountRequestDTO requestUpdateAccount;

    @BeforeClass
    public void setup() {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                        .jackson2ObjectMapperFactory((cls, charset) -> mapper));
    }

    public AccountSteps(AccountContext accountContext, AccountClient client, ScenarioContext scenarioContext){
        this.accountClient = client;
        this.accountContext = accountContext;
        this.scenarioContext = scenarioContext;
    }

    @Given("a unique account test data is prepared")
    public void aUniqueAccountTestDataIsPrepared() {
        accountContext.setAccount(createUniqueAccount());
    }


    @When("the client creates the account")
    public void theClientCreatesTheAccount() {
        response = accountClient.createNewAccount(accountContext.getAccount());
        scenarioContext.setLatestResponse(response);
    }

    @Then("the account should be created successfully")
    public void theAccountShouldBeCreatedSuccessfully() {
        scenarioContext.getLatestResponse()
                .then()
                .statusCode(201);

        MessageResponseDTO responseBody = scenarioContext.getLatestResponse().as(MessageResponseDTO.class);
        assertEquals(responseBody.responseCode(), 201);
        assertTrue(responseBody.message().toLowerCase(Locale.ROOT).contains("user created"));
        accountContext.markAsCreated();
    }

    @When("the client login using created account credentials")
    public void theClientLoginUsingCreatedAccountCredentials() {
        response = accountClient.loginAccount(getCurrentCredentials());
        scenarioContext.setLatestResponse(response);
    }

    @Then("the client should login successfully")
    public void theClientShouldLoginSuccessfully() {
        scenarioContext.getLatestResponse()
                .then()
                .statusCode(200);

        LoginResponseDTO responseBody = scenarioContext.getLatestResponse().as(LoginResponseDTO.class);
        assertEquals(responseBody.responseCode(), 200);
        assertTrue(responseBody.message().toLowerCase(Locale.ROOT).contains("user exists"));
    }

    @When("the client requests account details")
    public void theClientRequestsAccountDetails() {
        AccountRequestDTO account = accountContext.getAccount();
        response = accountClient.getAccountDetails(account.email());
        scenarioContext.setLatestResponse(response);
    }

    @Then("the account details should be correct")
    public void theAccountDetailsShouldBeCorrect() {
        scenarioContext.getLatestResponse()
                .then()
                .statusCode(200);

        UserDetailsResponseDTO responseBody = scenarioContext.getLatestResponse().as(UserDetailsResponseDTO.class);
        UserResponseDTO actualUser = responseBody.user();

        assertAccountDetails(actualUser, accountContext.getAccount());
    }

    @When("the client updates account information")
    public void theClientUpdatesAccountInformation() {
        requestUpdateAccount = AccountDataFactory.createUpdatedAccount(accountContext.getAccount());
        accountContext.setPendingUpdateAccount(requestUpdateAccount);
        response = accountClient.updateInformation(requestUpdateAccount);
        scenarioContext.setLatestResponse(response);
    }

    @Then("the account should be updated successfully")
    public void theAccountShouldBeUpdatedSuccessfully() {
        scenarioContext.getLatestResponse()
                .then()
                .statusCode(200);

        MessageResponseDTO responseBody = scenarioContext.getLatestResponse().as(MessageResponseDTO.class);
        assertEquals(responseBody.responseCode(), 200);
        assertTrue(responseBody.message().toLowerCase(Locale.ROOT).contains("user updated"));
        accountContext.applyPendingToCurrentAccount();
    }

    @Then("the account details should contain updated information")
    public void theAccountDetailsShouldContainUpdatedInformation() {
        UserDetailsResponseDTO responseBody = scenarioContext.getLatestResponse().as(UserDetailsResponseDTO.class);
        assertAccountDetails(responseBody.user(), requestUpdateAccount);
    }

    @When("the client deletes account")
    public void theClientDeletesAccount() {
        AccountRequestDTO accountRequest = accountContext.getAccount();
        response = accountClient.deleteAccount(accountRequest.email());
        scenarioContext.setLatestResponse(response);
    }

    @Then("the account should be deleted successfully")
    public void theAccountShouldBeDeletedSuccessfully() {
        scenarioContext.getLatestResponse()
                .then()
                .statusCode(200);

        MessageResponseDTO responseBody = scenarioContext.getLatestResponse().as(MessageResponseDTO.class);
        assertEquals(responseBody.responseCode(), 200);
        assertTrue(responseBody.message().toLowerCase(Locale.ROOT).contains("account deleted"));
        accountContext.markAsDeleted();
    }

    @When("the client login using deleted account credentials")
    public void theClientLoginUsingDeletedAccountCredentials() {
       response = accountClient.loginAccount(getCurrentCredentials());
       scenarioContext.setLatestResponse(response);
    }

    @Then("the login request should be rejected")
    public void theLoginRequestShouldBeRejected() {
        scenarioContext.getLatestResponse()
                .then()
                .statusCode(404);

        MessageResponseDTO responseBody = scenarioContext.getLatestResponse().as(MessageResponseDTO.class);
        assertEquals(responseBody.responseCode(), 404);
        assertTrue(responseBody.message().toLowerCase(Locale.ROOT).contains("user not found"));
    }

    // helpers
    private CredentialsRequestDTO getCurrentCredentials(){
        AccountRequestDTO account = accountContext.getAccount();
        return new CredentialsRequestDTO(account.email(), account.password());
    }

    private void assertAccountDetails(UserResponseDTO actualAccount, AccountRequestDTO expectedAccount){
        assertNotNull(actualAccount);
        assertEquals(actualAccount.email(), expectedAccount.email());
        assertEquals(actualAccount.name(), expectedAccount.name());
        assertEquals(actualAccount.country(), expectedAccount.country());
    }
}
