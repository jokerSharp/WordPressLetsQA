package ui.model.nonAdminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class NAPagePage extends BasePage {

    @FindBy(className = "wp-block-post-title")
    private WebElement postTitle;

    public NAPagePage(WebDriver driver) {
        super(driver);
    }

    public String postTitleGetText() {

        return  postTitle.getText();
    }
}
