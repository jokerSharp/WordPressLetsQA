package ui.model.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.model.pages.NewPagePage;

import java.time.Duration;

public class EditPostWelcomeGuideDialog {

    private final WebDriver driver;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButton;

    @FindBy(className = "edit-post-welcome-guide__heading")
    private WebElement welcomeGuideHeading;

    public EditPostWelcomeGuideDialog (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> welcomeGuideHeading.isDisplayed());
    }

    public static EditPostWelcomeGuideDialog wait(WebDriver driver) {
        try {
            return new EditPostWelcomeGuideDialog(driver);
        } catch(NoSuchElementException e) {
            return null;
        }
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public WebElement getWelcomeGuideHeading() {
        return welcomeGuideHeading;
    }
}
