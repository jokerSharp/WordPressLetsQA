package ui.model;

import org.openqa.selenium.WebDriver;
import ui.model.base.BasePage;

public class DashboardPage extends BasePage {

    public String getTitle() {
        return getDriver().getTitle();
    }
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
}
