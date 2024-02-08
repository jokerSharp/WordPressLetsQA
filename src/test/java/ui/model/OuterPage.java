package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import ui.model.base.BasePage;

import java.util.ArrayList;

public class OuterPage extends BasePage {

    public OuterPage(WebDriver driver) {
        super(driver);
    }

    public OuterPage goToOuterPageTab() {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));

        return new OuterPage(getDriver());
    }

    public DashboardPage closeOuterPageTabAndReturnToDashboardPage() {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());

        getDriver().close();
        getDriver().switchTo().window(tabs.get(0));

        return new DashboardPage(getDriver());
    }
}
