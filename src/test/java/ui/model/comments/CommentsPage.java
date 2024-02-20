package ui.model.comments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.CommentTest;
import ui.model.base.BasePage;

public class CommentsPage extends BasePage {

    @FindBy(id = "cb-select-all-1")
    private WebElement selectAllCheckbox;

    @FindBy(id = "cb-select-1")
    private WebElement wordPressCommenterCheckbox;

    public CommentsPage(WebDriver driver) {
        super(driver);
    }

    public CommentsPage clickSelectAllCheckbox() {
        selectAllCheckbox.click();

        return this;
    }

    public CommentsPage clickWordPressCommenterCheckbox() {
        wordPressCommenterCheckbox.click();

        return this;
    }



}
