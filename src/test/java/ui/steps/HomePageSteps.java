package ui.steps;

import api.config.ConfigManager;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.context.WebDriverContext;
import ui.pages.HomePage;

import static org.testng.Assert.assertTrue;

public class HomePageSteps {
    private HomePage homePage;
    private final WebDriverContext webDriverContext;

    public HomePageSteps(WebDriverContext context){
        this.webDriverContext = context;
    }

    @Given("the client opens ecommerce homepage")
    public void openHomePage(){
        String baseURL = ConfigManager.get("base.url");
        homePage = new HomePage(webDriverContext.getDriver(), baseURL);
        homePage.open();
    }

    @Then("the homepage should be displayed")
    public void theHomepageShouldBeDisplayed() {
        assertTrue(homePage.isHomePageLoaded());
    }
}
