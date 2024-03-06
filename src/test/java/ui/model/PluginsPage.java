package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;
import ui.runner.BaseTest;

public class PluginsPage extends BasePage {

    @FindBy(xpath = "//*[@id='wpbody-content']/div[3]/a")
    private WebElement addNewPluginButton;

    @FindBy(id = "message")
    private WebElement noticeMessage;

    public PluginsPage(WebDriver driver) {
        super(driver);
    }

    public AddPluginsPage clickAddNewPluginButton() {
        addNewPluginButton.click();

        return new AddPluginsPage(getDriver());
    }

    public String getNoticeMessage() {

        return noticeMessage.getText();
    }
}
