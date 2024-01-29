package ui.model.posts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class NewPostPage extends BasePage {

    @FindBy(css = ".wp-block-post-title")
    private WebElement postTitle;

    @FindBy(css = ".editor-post-publish-panel__toggle")
    private WebElement publishButton;

    @FindBy(css = ".editor-post-publish-button")
    private WebElement finalPublishButton;

    @FindBy(xpath = "//a[text()=\"View Post\"]")
    WebElement viewPost;

    public NewPostPage(WebDriver driver) {
        super(driver);
    }

    public NewPostPage typeTitle(String title) {
        getDriver().switchTo().frame("editor-canvas");
        postTitle.sendKeys(title);
        getDriver().switchTo().defaultContent();

        return this;
    }

    public NewPostPage clickPreliminaryPublishButton() {
        publishButton.click();

        return this;
    }

    public NewPostPage clickFinalPublishButton() {
        finalPublishButton.click();

        return this;
    }

    public PostPage clickViewPost() {
        viewPost.click();

        return new PostPage(getDriver());
    }

}
