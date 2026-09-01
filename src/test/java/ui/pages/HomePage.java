package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final String baseUrl;

    private final By logo = By.cssSelector("[data-testid='logo-link']" );
    private final By loginLink = By.cssSelector("[data-testid='nav-login-link']");
    private final By loggedInUserName = By.cssSelector("[data-testid='logged-in-username']");

    public HomePage(WebDriver driver, String baseUrl){
        super(driver);
        this.baseUrl = baseUrl;
    }

    public void open(){
        driver.get(baseUrl);
    }

    public boolean isHomePageLoaded(){
        return isDisplayed(logo);
    }

    public void navigateToLoginPage(){
        click(loginLink);
    }

    public boolean isLoggedIn(){
        return isDisplayed(loggedInUserName);
    }
}
