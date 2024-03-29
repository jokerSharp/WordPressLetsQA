package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.LoginPage;
import ui.model.nonAdminPages.NAHomePage;
import ui.model.posts.CreateEditPostPage;
import ui.model.users.NewUserPage;

import java.util.ArrayList;
import java.util.List;

public class Header {

    private final WebDriver driver;

    @FindBy(id = "wp-admin-bar-my-account")
    private WebElement userProfileButton;

    @FindBy(xpath = "//a[text()='Log Out']")
    private WebElement logOutButton;

    @FindBy(id = "wp-admin-bar-wp-logo")
    private WebElement wpLogo;

    @FindBy(xpath = "//li[@id= 'wp-admin-bar-wp-logo']/a/span")
    private WebElement wpLogoIcon;

    @FindBy(xpath = "//li[@id='wp-admin-bar-wp-logo']/div")
    private WebElement wpLogoMenuItemsList;

    @FindBy(xpath = "//li[@id='wp-admin-bar-wp-logo']/div/ul/li")
    private List<WebElement> wpLogoMenuItemsList2;

    @FindBy(xpath = "//a[@href='https://wordpress.org/support/forum/requests-and-feedback']")
    private WebElement feedbackLogoMenuItem;

    @FindBy(id = "wp-admin-bar-new-content")
    private WebElement newContentButton;

    @FindBy(id = "wp-admin-bar-new-post")
    private WebElement newPostButton;

    @FindBy(id = "wp-admin-bar-new-user")
    private WebElement newUserButton;

    @FindBy(id = "wp-admin-bar-site-name")
    private WebElement letsQALogo;

    public Header (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public Header hoverOnUserProfileButton() {
        new Actions(getDriver())
                .moveToElement(userProfileButton)
                .perform();

        return this;
    }

    public LoginPage clickLogOutButton() {
        logOutButton.click();
        return new LoginPage(getDriver());
    }

    public Header hoverOnWpLogo() {
        new Actions(getDriver())
                .moveToElement(wpLogo)
                .perform();

        return this;
    }

    public String getWpLogoIconColor() {
        return wpLogoIcon.getCssValue("color");
    }

    public List<String> getWpLogoMenuItemsList() {
        String res = wpLogoMenuItemsList.getText();

        return List.of(res.split("\\n"));
    }

    public Header hoverOnFeedbackLogoMenuItem() {
        new Actions(getDriver())
                .moveToElement(feedbackLogoMenuItem)
                .perform();

        return this;
    }

    public String getColorOfFeedbackLogoMenuItem() {
        return feedbackLogoMenuItem.getCssValue("color");
    }

    public Header hoverOnNewContentButton() {
        new Actions(getDriver())
                .moveToElement(newContentButton)
                .perform();

        return this;
    }
    public CreateEditPostPage clickNewPostButton() {
        newPostButton.click();

        return new CreateEditPostPage(getDriver());
    }
    public List<String> getWpLogoMenuItemsList2() {
        List<String> menuItemsList = new ArrayList<>();

        for (WebElement item : wpLogoMenuItemsList2) {
            menuItemsList.add(item.getText());
        }
        return menuItemsList;
    }

    public Header hoverOnNewButton() {
        new Actions(getDriver())
                .moveToElement(newContentButton)
                .perform();

        return this;
    }

    public NewUserPage clickNewUserButton() {
        newUserButton.click();

        return new NewUserPage(getDriver());
    }

    public NAHomePage goToNAHomePage() {
        letsQALogo.click();

        return new NAHomePage(getDriver());
    }
}
