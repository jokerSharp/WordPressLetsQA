package ui.model.plugins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.base.BasePage;

public class PluginsPage extends BasePage {

    @FindBy(id = "message")
    private WebElement noticeMessage;

    public PluginsPage(WebDriver driver) {
        super(driver);
    }

    public String getNoticeMessage() {
        getWait2().until(ExpectedConditions.visibilityOf(noticeMessage));

        return noticeMessage.getText().substring(0, 17);
    }
}
