package ui.model.nonAdminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class NAPostPage extends BasePage {


    @FindBy(id = "comment")
    private WebElement commentInputField;

    @FindBy(id = "submit")
    private WebElement postCommentButton;

    @FindBy(xpath = "//div[@class='wp-block-comment-content']/p")
    private List<WebElement> commentsList;

    protected NAPostPage(WebDriver driver) {
        super(driver);
    }

    public NAPostPage typeNewComment(String comment) {
        commentInputField.sendKeys(comment);

        return this;
    }

    public NAPostPage clickPostCommentButton() {
        postCommentButton.click();

        return new NAPostPage(getDriver());
    }

    public List<String> getCommentsText() {
        List<String> commentsTextList = new ArrayList<>();

        for (WebElement element : commentsList) {
            commentsTextList.add(element.getText());
        }

        return commentsTextList;
    }
}
