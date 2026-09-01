package ui.steps;

import api.config.ConfigManager;
import api.context.AccountContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.context.WebDriverContext;
import ui.pages.HomePage;
import ui.pages.LoginPage;

import static org.testng.Assert.assertTrue;

public class LoginPageSteps {
    private final AccountContext accountContext;
    private final WebDriverContext webDriverContext;

    public LoginPageSteps(AccountContext accountContext, WebDriverContext webDriverContext){
        this.accountContext = accountContext;
        this.webDriverContext = webDriverContext;
    }

    @When("the client login with created-account")
    public void theClientLoginWithCreatedAccount() {
        LoginPage loginPage = new LoginPage(webDriverContext.getDriver());
        loginPage.open();
        loginPage.login(accountContext.getAccount());
    }

    @Then("the client should login successfully on store UI")
    public void theClientShouldLoginSuccessfullyOnStoreUI() {
        HomePage homePage = new HomePage(webDriverContext.getDriver(), ConfigManager.get("base.url"));
        assertTrue(homePage.isLoggedIn());
    }
}
