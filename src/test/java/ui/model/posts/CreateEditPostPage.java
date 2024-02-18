package ui.model.posts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.base.BasePage;

public class CreateEditPostPage extends BasePage {

    @FindBy(css = ".wp-block-post-title")
    private WebElement postTitle;

    @FindBy(css = ".editor-post-publish-panel__toggle")
    private WebElement publishButton;

    @FindBy(css = ".editor-post-publish-button")
    private WebElement finalPublishOrUpdateButton;

    @FindBy(xpath = "//a[text() = 'View Post']")
    WebElement viewPost;

    @FindBy (xpath = "//div[@class = 'components-form-token-field']//input")
    WebElement tagsInputField;

    @FindBy (xpath = "//div[@class = 'components-panel']//button[contains(text(), 'Tags')]")
    WebElement tagsRightPanelButton;

    @FindBy(xpath = "//div[@class = 'components-snackbar__content']")
    WebElement successMessage;

    public CreateEditPostPage(WebDriver driver) {
        super(driver);
    }

    public CreateEditPostPage typeTitle(String title) {
        getDriver().switchTo().frame("editor-canvas");
        postTitle.sendKeys(title);
        getDriver().switchTo().defaultContent();

        return this;
    }

    public CreateEditPostPage clickPreliminaryPublishButton() {
        publishButton.click();

        return this;
    }

    public CreateEditPostPage clickFinalPublishOrUpdateButton() {
        getWait2().until(driver -> ExpectedConditions.elementToBeClickable(finalPublishOrUpdateButton));
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        finalPublishOrUpdateButton.click();

        return this;
    }

    public ViewPostPage clickViewPost() {
        viewPost.click();

        return new ViewPostPage(getDriver());
    }

    public CreateEditPostPage scrollToTagsSection() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", tagsRightPanelButton);

        return this;
    }

    public CreateEditPostPage inputTagAndPushEnter(String tag) {
        if (tagsRightPanelButton.getAttribute("aria-expanded").equals("false")) {
            tagsRightPanelButton.click();
        }
        tagsInputField.click();
        tagsInputField.sendKeys(tag);
        tagsInputField.sendKeys(Keys.ENTER);

        return this;
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }
}
