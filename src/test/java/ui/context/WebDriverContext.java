package ui.context;

import org.openqa.selenium.WebDriver;
import ui.driver.WebDriverFactory;

public class WebDriverContext {
    private WebDriver driver;

    public void start(boolean headless){
        if(driver != null){
            throw new IllegalStateException("WebDriver has been started!");
        }

        driver = WebDriverFactory.createChromeDriver(headless);
    }

    public WebDriver getDriver(){
        if(driver == null){
            throw new IllegalStateException("WebDriver has not been started!");
        }

        return driver;
    }

    public boolean isDriverStarted(){
        return driver != null;
    }

    public void quit(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

}
