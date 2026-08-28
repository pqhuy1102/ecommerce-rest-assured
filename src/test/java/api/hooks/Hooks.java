package api.hooks;

import api.context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;

public class Hooks {
    private final ScenarioContext scenarioContext;

    public Hooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @BeforeAll
    public static void configureRestAssured(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(
                LogDetail.ALL
        );
    }

    @After
    public void attachFailedResponse(Scenario scenario){
        if(scenario.isFailed() && scenarioContext.hasResponse()){
            scenario.attach(
                    scenarioContext.getLatestResponse().asPrettyString(),
                    "application/json",
                    "Latest API Response"
            );
        }
    }
}
