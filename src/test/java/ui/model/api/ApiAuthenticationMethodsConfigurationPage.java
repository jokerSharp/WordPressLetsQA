package ui.model.api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class ApiAuthenticationMethodsConfigurationPage extends BasePage {

    @FindBy(xpath = "//div/p[(text()='JWT AUTHENTICATION')]")
    private WebElement jwtAuthentication;

    @FindBy(css = "form>div>button:nth-child(1)")
    private WebElement nextButton;

    @FindBy(xpath = "//button[@onclick='moJWTAuthenticationMethodFinish()']")
    private WebElement finishButton;

    @FindBy(css = ".mo_api_auth_admin_custom_notice_success")
    private WebElement noticeField;

    public ApiAuthenticationMethodsConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public ApiAuthenticationMethodsConfigurationPage clickJwtAuthentication() {
        new Actions(getDriver())
                .pause(500)
                .moveToElement(jwtAuthentication)
                .click()
                .perform();

        return this;
    }

    public ApiAuthenticationMethodsConfigurationPage clickNext() {
        nextButton.click();

        return this;
    }

    public ApiAuthenticationMethodsConfigurationPage clickFinish() {
        finishButton.click();

        return this;
    }

    public String getNoticeMessage() {
        new Actions(getDriver())
                .pause(500)
                .perform();

        return noticeField.getText();
    }
 }
