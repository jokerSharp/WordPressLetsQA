package ui.model.base;

import org.openqa.selenium.WebDriver;

public abstract class BasePage extends BaseModel{

    protected BasePage(WebDriver driver) {
        super(driver);
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public String getTitle() {
        return getDriver().getTitle();
    }
}
