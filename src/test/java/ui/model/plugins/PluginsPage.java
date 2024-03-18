package ui.model.plugins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.api.ApiAuthenticationMethodsConfigurationPage;
import ui.model.base.BasePage;

public class PluginsPage extends BasePage {

    @FindBy(id = "message")
    private WebElement noticeMessage;

    @FindBy(xpath = "//a[(text() = 'Configure')]")
    private WebElement configureRestApiAuthentication;

    public PluginsPage(WebDriver driver) {
        super(driver);
    }

    public String getNoticeMessage() {
        getWait2().until(ExpectedConditions.visibilityOf(noticeMessage));

        return noticeMessage.getText().substring(0, 17);
    }

    public ApiAuthenticationMethodsConfigurationPage clickConfigureRestApiAuthentication() {
        configureRestApiAuthentication.click();

        return new ApiAuthenticationMethodsConfigurationPage(getDriver());
    }
}
