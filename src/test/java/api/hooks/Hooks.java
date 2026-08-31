package api.hooks;

import api.clients.AccountClient;
import api.context.AccountContext;
import api.context.ScenarioContext;
import api.models.request.AccountRequestDTO;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;

public class Hooks {
    private final ScenarioContext scenarioContext;
    private final AccountContext accountContext;
    private final AccountClient accountClient;

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
