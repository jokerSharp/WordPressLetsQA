package ui.model.posts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.DashboardPage;
import ui.model.base.BasePage;

public class ViewPostPage extends BasePage {

    @FindBy(className = "wp-block-post-date")
    private WebElement postDate;

    @FindBy(id = "comment")
    private WebElement commentInputField;

    @FindBy(id = "submit")
    private WebElement postCommentButton;

    @FindBy(css = "#wp-admin-bar-site-name > a[href*='/wp-admin']")
    private WebElement adminLogo;

    @FindBy(css = ".wp-block-post-title.has-x-large-font-size")
    private WebElement postTitle;

    public ViewPostPage(WebDriver driver) {
        super(driver);
        waitHeaderPanel();
    }

    private void waitHeaderPanel() {
        getWait2().until(driver -> postDate.isDisplayed());
    }

    public ViewPostPage typeComment(String comment) {
        commentInputField.sendKeys(comment);

        return this;
    }

    public ViewPostPage clickPostCommentButton() {
        postCommentButton.click();

        return new ViewPostPage(getDriver());
    }

    public DashboardPage goToDashboardPage() {
        adminLogo.click();

        return new DashboardPage(getDriver());
    }

    public String getPostTitleText() {
        return postTitle.getText();
    }
}
