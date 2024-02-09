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

    public <T extends BasePage> T returnToPreviousPage(T page) {
        getDriver().navigate().back();

        return page;
    }
}
