package ui.model.nonAdminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

import java.util.List;

public class NAMainPage extends BasePage {

    @FindBy (className = "wp-block-pages-list__item__link")
    private List<WebElement> pagesList;

    public NAMainPage(WebDriver driver) {
        super(driver);
    }

    public static NAMainPage open(WebDriver driver, String baseUrl) {
        driver.get(baseUrl);
        return new NAMainPage(driver);
    }

    public NAPagePage clickPage(String pageTitle) {
        for (WebElement webElement : pagesList) {
            if (webElement.getText().equals(pageTitle)) {
                webElement.click();
                return new NAPagePage(getDriver());
            }
        }
        throw new RuntimeException("No such element" + pageTitle);
    }
}
