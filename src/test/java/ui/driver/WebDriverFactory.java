package ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class WebDriverFactory {
    private WebDriverFactory(){}

    public static WebDriver createChromeDriver(boolean headless){
        ChromeOptions options = new ChromeOptions();

        if(headless){
            options.addArguments("--headless=new");
        }

        options.addArguments(
            "--window-size=1920,1080", "disable-notifications"
        );
        return new ChromeDriver(options);
    }
}
