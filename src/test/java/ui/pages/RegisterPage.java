package ui.pages;

import api.config.ConfigManager;
import api.models.request.AccountRequestDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    private final String registerUrl;

    // Account Information
    private final By mrRadioBtn = By.cssSelector("[data-testid='title-mr-radio']");
    private final By mrsRadioBtn = By.cssSelector("[data-testid='title-mrs-radio']");
    private final By missRadioBtn = By.cssSelector("[data-testid='title-miss-radio']");
    private final By fullNameTxtBox = By.id("signup-name");
    private final By emailTxtBox = By.id("signup-email");
    private final By passwordTxtBox = By.id("signup-password");
    private final By birthYearDropdown = By.id("birth-year");
    private final By birthMonthDropdown = By.id("birth-month");
    private final By birthDayDropdown = By.id("birth-day");

    // Address Information
    private final By firstNameTxtBox = By.id("first-name");
    private final By lastNameTxtBox = By.id("last-name");
    private final By companyTxtBox = By.id("company");
    private final By address1TxtBox = By.id("address1");
    private final By address2TxtBox = By.id("address2");
    private final By stateTxtBox = By.id("state");
    private final By cityTxtBox = By.id("city");
    private final By zipCodeTxtBox = By.id("zipcode");
    private final By mobileNumberTxtBox = By.id("mobile-number");
    private final By countryDropdown = By.id("country");

    private final By submitRegisterBtn = By.cssSelector("[data-testid='submit-register-btn']");

    public RegisterPage(WebDriver webDriver){
        super(webDriver);
        registerUrl = ConfigManager.get("base.url") + "/signup";
    }

    public void open(){
        driver.get(registerUrl);
    }

    public void registerAccount(AccountRequestDTO accountRequest){
        switch (accountRequest.title()){
            case "Mr":
                click(mrRadioBtn);
                break;
            case "Mrs":
                click(mrsRadioBtn);
                break;
            case "Miss":
                click(missRadioBtn);
                break;
            default:
                System.out.println("Null or Empty title");
                break;
        }

        // Fill account info
        type(fullNameTxtBox, accountRequest.name());
        type(emailTxtBox, accountRequest.email());
        type(passwordTxtBox, accountRequest.password());
        selectOptionDropdown(birthDayDropdown, accountRequest.birthDate());
        selectOptionDropdown(birthMonthDropdown, accountRequest.birthMonth());
        selectOptionDropdown(birthYearDropdown, accountRequest.birthYear());

        //Fill address info
        type(firstNameTxtBox, accountRequest.firstname());
        type(lastNameTxtBox, accountRequest.lastname());
        type(companyTxtBox, accountRequest.company());
        type(address1TxtBox, accountRequest.address1());
        type(address2TxtBox, accountRequest.address2());
        type(stateTxtBox, accountRequest.state());
        type(cityTxtBox, accountRequest.city());
        type(zipCodeTxtBox, accountRequest.zipcode());
        type(mobileNumberTxtBox, accountRequest.mobileNumber());
        selectOptionDropdown(countryDropdown, accountRequest.country());

        click(submitRegisterBtn);
    }
}
