package ui.model.comments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;
import ui.model.posts.ViewPostPage;

import java.util.ArrayList;
import java.util.List;

public class CommentsPage extends BasePage {

    @FindBy(id = "cb-select-all-1")
    private WebElement selectAllCheckbox;

    @FindBy(id = "cb-select-1")
    private WebElement wordPressCommenterCheckbox;

    @FindBy(id = "bulk-action-selector-top")
    private WebElement bulkActionSelector;

    @FindBy(css = "option[value=\"trash\"]")
    private WebElement moveToTrashOption;

    @FindBy(id = "doaction")
    private WebElement applyButton;

    @FindBy(css = "#the-comment-list > tr > td  > strong")
    private List<WebElement> commentsAuthorsList;

    @FindBy(css = ".comment-author + p")
    private List<WebElement> commentsTextList;

    @FindBy(xpath = "//div/a[contains(text(),'View Post')]")
    private List<WebElement> viewPostLinksList;

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

    public CommentsPage clickBulkActionSelector() {
        bulkActionSelector.click();

        return this;
    }

    public CommentsPage ClickMoveToTrashOption() {
        moveToTrashOption.click();

        return this;
    }

    public CommentsPage clickApplyButton() {
        applyButton.click();
        return this;
    }

    public List<String> getCommentsAuthorsList() {
        List<String> comments = new ArrayList<>();

        for (WebElement e : commentsAuthorsList) {
            comments.add(e.getText());
        }

        return comments;
    }

    public List<String> getCommentsTextList() {
        List<String> comments = new ArrayList<>();

        for (WebElement e : commentsTextList) {
            comments.add(e.getText());
        }

        return comments;
    }

    public int getCommentsListSize() {
        return commentsAuthorsList.size();
    }

    public List<WebElement> getViewPostLinksList() {
        return viewPostLinksList;
    }

    public ViewPostPage clickTopViewPostLink() {
        getViewPostLinksList().get(0).click();

        return new ViewPostPage(getDriver());
    }
}
