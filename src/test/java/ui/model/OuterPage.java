package ui.model;

import org.openqa.selenium.WebDriver;
import ui.model.base.BasePage;

import java.util.ArrayList;

public class OuterPage extends BasePage {
    public OuterPage(WebDriver driver) {
        super(driver);
    }
    public String getOuterPageUrl() {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        return getDriver().switchTo().window(tabs.get(1)).getCurrentUrl();
    }
}
