package ui.model.api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testcontainers.shaded.org.apache.commons.io.build.AbstractOrigin;
import ui.model.base.BasePage;

public class ApiAuthenticationMethodsConfigurationPage extends BasePage {

    @FindBy(xpath = "//div/p[(text()='JWT AUTHENTICATION')]")
    private WebElement jwtAuthentication;

    @FindBy(css = "form>div>button:nth-child(1)")
    private WebElement nextButton;

    @FindBy(xpath = "//button[(text()='Finish')]")
    private WebElement finishButton;

    @FindBy(css = ".mo_api_auth_admin_custom_notice_success")
    private WebElement noticeField;

    public ApiAuthenticationMethodsConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public ApiAuthenticationMethodsConfigurationPage clickJwtAuthentication() {
        jwtAuthentication.click();

        return new ApiAuthenticationMethodsConfigurationPage(getDriver());
    }

    public ApiAuthenticationMethodsConfigurationPage clickNext() {
        nextButton.click();

        return new ApiAuthenticationMethodsConfigurationPage(getDriver());
    }

    public ApiAuthenticationMethodsConfigurationPage clickFinish() {
        finishButton.click();

        return new ApiAuthenticationMethodsConfigurationPage(getDriver());
    }

    public String getNoticeMessage() {

        return noticeField.getText();
    }
 }
