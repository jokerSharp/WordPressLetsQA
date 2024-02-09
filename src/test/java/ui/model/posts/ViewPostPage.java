package ui.model.posts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class ViewPostPage extends BasePage {

    @FindBy(className = "wp-block-post-date")
    private WebElement postDate;

    protected ViewPostPage(WebDriver driver) {
        super(driver);
        waitHeaderPanel();
    }

    private void waitHeaderPanel() {
        getWait2().until(driver -> postDate.isDisplayed());
    }
}
