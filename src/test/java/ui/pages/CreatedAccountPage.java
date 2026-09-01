package ui.pages;


import api.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatedAccountPage extends BasePage{
    private final By accountCreatedHeader = By.cssSelector("[data-testid='account-created-message']");


    public CreatedAccountPage(WebDriver driver){
        super(driver);
    }

    public boolean isCreatedAccountPageLoaded(){
        return isDisplayed(accountCreatedHeader);
    }
}
