package ui.model.nonAdminPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.base.BasePage;

public class NAHomePage extends BasePage {

    @FindBy(css = "h2 > a[href*=\"/1/\"]")
    private WebElement firstPostName;

    public NAHomePage(WebDriver driver) {
        super(driver);
    }

    public NAPostPage scrollAndClickFirstPostLink() {

//        getWait5().until(ExpectedConditions.visibilityOf(firstPostName));

//        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        js.executeScript("arguments[0].scrollIntoView();", firstPostName);

        new Actions(getDriver())
                .scrollToElement(firstPostName)
                .perform();

        firstPostName.click();

        return new NAPostPage(getDriver());
    }
}
