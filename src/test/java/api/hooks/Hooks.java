package api.hooks;

import api.clients.AccountClient;
import api.context.AccountContext;
import api.context.ScenarioContext;
import api.models.request.AccountRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;

public class Hooks {
    private final ScenarioContext scenarioContext;
    private final AccountContext accountContext;
    private final AccountClient accountClient;
    private static boolean isRestAssuredConfigured = false;

    public Hooks(ScenarioContext scenarioContext, AccountContext accountContext, AccountClient accountClient) {
        this.accountContext = accountContext;
        this.accountClient = accountClient;
        this.scenarioContext = scenarioContext;
    }

    @BeforeAll
    public static void configureRestAssured(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(
                LogDetail.ALL
        );
    }

    @Before
    public void setupRestAssured(){
        if(!isRestAssuredConfigured){
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

            RestAssured.config = RestAssuredConfig.config()
                    .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                            .jackson2ObjectMapperFactory((cls, charset) -> mapper));
        }
    }

    @After(order = 100)
    public void attachFailedResponse(Scenario scenario){
        if(scenario.isFailed() && scenarioContext.hasResponse()){
            scenario.attach(
                    scenarioContext.getLatestResponse().asPrettyString(),
                    "application/json",
                    "Latest API Response"
            );
        }
    }

    @After(value = "@account", order = 0)
    public void cleanUpAccount(Scenario scenario){
        if(!accountContext.isCreated() || accountContext.isDeleted()) return;

        try {
            AccountRequestDTO accountRequest = accountContext.getAccount();

            Response cleanUpAccountResponse = accountClient.deleteAccount(accountRequest.email());

            scenario.log("Account cleanup status: " + cleanUpAccountResponse.statusCode());
        } catch (Exception exception){
            scenario.log("Failed to cleanup account: " + exception.getMessage());
        }
    }
}
