package ui.model.nonAdminPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.base.BasePage;

public class NAHomePage extends BasePage {

    @FindBy(css = "h2 > a[href*='/?p=1']")
    private WebElement firstPostName;

    public NAHomePage(WebDriver driver) {
        super(driver);
    }

    public NAPostPage clickFirstPostLink() {
        firstPostName.click();

        return new NAPostPage(getDriver());
    }
}
