package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.*;
import ui.model.posts.NewPostPage;
import ui.model.users.NewUserPage;
import ui.model.users.UserPage;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage extends BaseModel{

    @FindBy(xpath = "//a[@href='http://localhost:8000/wp-admin/profile.php']")
    private WebElement userProfileButton;

    @FindBy(xpath = "//a[text()='Log Out']")
    private WebElement logOutButton;

    @FindBy(xpath = "//div[@id = 'wpadminbar']")
    private WebElement header;

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

    @FindBy(xpath = "//span[@class='ab-label']")
    private WebElement newButton;

    @FindBy(xpath = "//a[@href='http://localhost:8000/wp-admin/user-new.php']")
    private WebElement newUserButton;

    @FindBy(id = "menu-users")
    private WebElement sideMenuUsersButton;

    protected BasePage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage hoverOnUserProfileButton() {
        new Actions(getDriver())
                .moveToElement(userProfileButton)
                .perform();

        return new DashboardPage(getDriver());
    }

    public LoginPage clickLogOutButton() {
        logOutButton.click();
        return new LoginPage(getDriver());
    }

    public DashboardPage hoverOnWpLogo() {
        new Actions(getDriver())
                .moveToElement(wpLogo)
                .perform();

        return new DashboardPage(getDriver());
    }

    public DashboardPage hoverOnFeedbackLogoMenuItem() {
        new Actions(getDriver())
                .moveToElement(feedbackLogoMenuItem)
                .perform();

        return new DashboardPage(getDriver());
    }

    public List<String> getWpLogoMenuItemsList() {
        String res = wpLogoMenuItemsList.getText();
        List<String> resl = List.of(res.split("\\n"));

        return resl;
    }

    public List<String> getWpLogoMenuItemsList2() {
        List<String> menuItemsList = new ArrayList<>();

        for (WebElement item : wpLogoMenuItemsList2) {
            menuItemsList.add(item.getText());
        }
        return menuItemsList;
    }

    public String getColorOfFeedbackLogoMenuItem() {
        return feedbackLogoMenuItem.getCssValue("color");
    }

    public String getWpLogoIconColor() {
        return wpLogoIcon.getCssValue("color");
    }

    public DashboardPage hoverOnNewContentButton() {
        new Actions(getDriver())
                .moveToElement(newContentButton)
                .perform();

        return new DashboardPage(getDriver());
    }
    public NewPostPage clickNewPostButton() {
        newPostButton.click();
        return new NewPostPage(getDriver());
    }

    public DashboardPage hoverOnNewButton() {
        new Actions(getDriver())
                .moveToElement(newButton)
                .perform();

        return new DashboardPage(getDriver());
    }

    public NewUserPage clickNewUserButton() {
        newUserButton.click();

        return new NewUserPage(getDriver());
    }

    public UserPage goToUserPage() {
        sideMenuUsersButton.click();

        return new UserPage(getDriver());
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
