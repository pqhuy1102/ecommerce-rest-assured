package ui.pages;

import api.config.ConfigManager;
import api.models.request.AccountRequestDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final String loginUrl;
    private final By emailTxtBox = By.id("login-email");
    private final By passwordTxtBox = By.id("login-password");
    private final By loginBtn = By.cssSelector("[data-testid='submit-login-btn']");

    public LoginPage(WebDriver driver){
        super(driver);
        loginUrl = ConfigManager.get("base.url") + "/login";
    }

    public void open(){
        driver.get(loginUrl);
    }

    public void login(AccountRequestDTO accountRequest){
        type(emailTxtBox, accountRequest.email());
        type(passwordTxtBox, accountRequest.password());
        click(loginBtn);
    }
}
