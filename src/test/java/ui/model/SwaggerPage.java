package ui.model;

import org.openqa.selenium.WebDriver;
import ui.model.base.BasePage;
import ui.runner.LoggerUtils;

public class SwaggerPage extends BasePage {

    public SwaggerPage(WebDriver driver) {
        super(driver);
    }

    public String getUrlSwaggerApidocs() {
        LoggerUtils.logInfo("url = " + getCurrentUrl());

        return getCurrentUrl().substring(51, 67);
    }
}
