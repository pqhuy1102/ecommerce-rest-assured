package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String value){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(value);
    }

    protected String getText(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        locator
                )
        ).getText();
    }

    protected boolean isDisplayed(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        locator
                )
        ).isDisplayed();
    }

    protected void scrollToElement(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void selectOptionDropdown(By locator, String value){
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        Select select = new Select(element);
        select.selectByValue(value);
    }
}
