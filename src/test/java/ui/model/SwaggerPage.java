package ui.model;

import org.openqa.selenium.WebDriver;
import ui.model.base.BasePage;

public class SwaggerPage extends BasePage {

    public SwaggerPage(WebDriver driver) {
        super(driver);
    }

    public String getUrlSwaggerApidocs() {

        return getCurrentUrl().substring(51, 67);
    }
}
