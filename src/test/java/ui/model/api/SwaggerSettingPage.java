package ui.model.api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class SwaggerSettingPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(), 'Docs URL')]")
    private WebElement apiDocsUrl;

    @FindBy(css = "select[name=swagger_api_basepath]>[selected=selected]")
    private WebElement basePath;

    public SwaggerSettingPage(WebDriver driver) {
        super(driver);
    }

    public SwaggerPage clickApiDocsUrl() {
        apiDocsUrl.click();

        return new SwaggerPage(getDriver());
    }

    public String getBasePath() {
        return basePath.getAttribute("value");
    }
}
