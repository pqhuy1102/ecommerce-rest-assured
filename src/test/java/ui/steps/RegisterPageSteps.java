package ui.steps;

import api.context.AccountContext;
import api.context.ScenarioContext;
import api.data.AccountDataFactory;
import api.models.request.AccountRequestDTO;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ui.context.WebDriverContext;
import ui.pages.CreatedAccountPage;
import ui.pages.RegisterPage;

import static org.testng.Assert.assertTrue;

public class RegisterPageSteps {
    private final AccountContext accountContext;
    private final WebDriverContext webDriverContext;

    public RegisterPageSteps(AccountContext accountContext, WebDriverContext webDriverContext){
        this.accountContext = accountContext;
        this.webDriverContext = webDriverContext;
    }

    @When("the client registers with prepared account")
    public void theClientRegistersWithPreparedAccount() {
        AccountRequestDTO account = accountContext.getAccount();
        RegisterPage registerPage = new RegisterPage(webDriverContext.getDriver());
        registerPage.open();
        registerPage.registerAccount(account);
    }

    @Then("account-created confirmation should be displayed")
    public void accountCreatedConfirmationShouldBeDisplayed() {
        CreatedAccountPage createdAccountPage = new CreatedAccountPage(webDriverContext.getDriver());
        boolean isCreatedAccountPageLoaded = createdAccountPage.isCreatedAccountPageLoaded();
        if(isCreatedAccountPageLoaded){
            accountContext.markAsCreated();
        }
        assertTrue(isCreatedAccountPageLoaded);
    }
}
