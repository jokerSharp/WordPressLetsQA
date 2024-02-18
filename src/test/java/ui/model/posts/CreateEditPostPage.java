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
    private WebElement viewPost;

    @FindBy (xpath = "//div[@class = 'components-form-token-field']//input")
    private WebElement tagsInputField;

    @FindBy (xpath = "//div[@class = 'components-panel']//button[contains(text(), 'Tags')]")
    private WebElement tagsRightPanelButton;

    @FindBy(xpath = "//div[@class = 'components-snackbar__content']")
    private WebElement successMessage;

    @FindBy (xpath = "//a[@href = 'edit.php?post_type=post']")
    private WebElement wpLogo;

    @FindBy (xpath = "//button[@aria-label = 'Close']")
    private WebElement welcomeWindowCloseButton;

    @FindBy (css = ".components-modal__content")
    private WebElement welcomeWindow;

    @FindBy (css = ".components-form-token-field__suggestions-list > li")
    private WebElement tagsDropDownList;

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public CreateEditPostPage inputTagsName(String tag) {
        if (tagsRightPanelButton.getAttribute("aria-expanded").equals("false")) {
            tagsRightPanelButton.click();
        }
        tagsInputField.click();
        tagsInputField.sendKeys(tag);

        return this;
    }

    public CreateEditPostPage clickTagNameFromDropdown() {
        tagsDropDownList.click();

        return this;
    }

    public String getSuccessMessageText() {
        getWait5().until(ExpectedConditions.visibilityOf(successMessage));

        return successMessage.getText();
    }

    public AllPostsPage clickWpLogo() {
        wpLogo.click();

        return new AllPostsPage(getDriver());
    }

    public CreateEditPostPage closeWelcomeWindowIfAppears() {
        if (welcomeWindow.isDisplayed()) {
            welcomeWindowCloseButton.click();
        }

        return this;
    }
}
