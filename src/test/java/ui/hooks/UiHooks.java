package ui.hooks;

import api.config.ConfigManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ui.context.WebDriverContext;

public class UiHooks {
    private final WebDriverContext webDriverContext;

    public UiHooks(WebDriverContext webDriverContext){
        this.webDriverContext = webDriverContext;
    }

    @Before("@ui")
    public void startBrowser(){
        webDriverContext.start(ConfigManager.isUiHeadless("ui.headless"));
    }

    @After(value = "@ui", order = 100)
    public void captureFailureAndQuit(Scenario scenario){
        if(!webDriverContext.isDriverStarted()) return;

        try {
            if(scenario.isFailed()){
                attachScreenshot(scenario);
                attachCurrentUrl(scenario);
            }
        } finally {
            webDriverContext.quit();
        }
    }

    private void attachScreenshot(Scenario scenario){
        byte[] screenshot = ((TakesScreenshot) webDriverContext.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Failure screenshot");
    }

    private void attachCurrentUrl(Scenario scenario){
        String currentUrl = webDriverContext.getDriver().getCurrentUrl();
        scenario.attach(currentUrl, "text/plain", "Current URL");
    }
}
