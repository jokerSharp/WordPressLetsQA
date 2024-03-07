package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class SwaggerSettingPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(), 'Docs URL')]")
    private WebElement apiDocsUrl;

    public SwaggerSettingPage(WebDriver driver) {
        super(driver);
    }

    public SwaggerPage clickApiDocsUrl() {
        apiDocsUrl.click();

        return new SwaggerPage(getDriver());
    }
}
