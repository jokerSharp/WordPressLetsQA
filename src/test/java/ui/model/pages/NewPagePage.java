package ui.model.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.base.BasePage;
import ui.model.base.EditPostWelcomeGuideDialog;

import java.util.List;

public class NewPagePage extends BasePage {

    private EditPostWelcomeGuideDialog editPostWelcomeGuideDialog;

    @FindBy(className = "block-editor-block-patterns-list__item")
    private List <WebElement> patternsList;

    @FindBy(className = "editor-post-title")
    private WebElement title;

    @FindBy(css = ".editor-post-publish-panel__toggle")
    private WebElement publishButton;

    @FindBy(css = ".editor-post-publish-button")
    private WebElement finalPublishButton;

   // @FindBy(className = "components-modal__header-heading")
    //private WebElement choosePatternTitle;

    public NewPagePage(WebDriver driver, boolean skipWelcomeDialog) {
        super(driver);
        editPostWelcomeGuideDialog = EditPostWelcomeGuideDialog.wait(driver);
        if (editPostWelcomeGuideDialog != null && skipWelcomeDialog) {
            editPostWelcomeGuideDialog.clickCloseButton();
        }
    }

   // private void waitChoosePatternTitle() {
   //     getWait2().until(driver -> choosePatternTitle.isDisplayed());
    //}

    public NewPagePage clickPattern() {
        patternsList.get(0).click();

        return this;
    }

    public NewPagePage typeTitle(String pageTitle) {
        getDriver().switchTo().frame("editor-canvas");
        title.sendKeys(pageTitle);
        getDriver().switchTo().defaultContent();

        return this;
    }

    public NewPagePage clickPublishButton() {
        publishButton.click();

        return this;
    }

    public NewPagePage clickFinalPublishButton() {
        getWait2().until(driver -> ExpectedConditions.elementToBeClickable(finalPublishButton));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finalPublishButton.click();

        return this;
    }
}
