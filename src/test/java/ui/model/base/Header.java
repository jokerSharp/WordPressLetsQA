package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.DashboardPage;
import ui.model.LoginPage;
import ui.model.posts.NewPostPage;
import ui.model.users.NewUserPage;

import java.util.ArrayList;
import java.util.List;

public class Header {

    private final WebDriver driver;

    @FindBy(xpath = "//a[@href='http://localhost:8000/wp-admin/profile.php']")
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

    @FindBy(xpath = "//a[@href='http://localhost:8000/wp-admin/user-new.php']")
    private WebElement newUserButton;

    public Header (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DashboardPage hoverOnUserProfileButton() {
        new Actions(driver)
                .moveToElement(userProfileButton)
                .perform();

        return new DashboardPage(driver);
    }

    public LoginPage clickLogOutButton() {
        logOutButton.click();
        return new LoginPage(driver);
    }

    public DashboardPage hoverOnWpLogo() {
        new Actions(driver)
                .moveToElement(wpLogo)
                .perform();

        return new DashboardPage(driver);
    }

    public String getWpLogoIconColor() {
        return wpLogoIcon.getCssValue("color");
    }

    public List<String> getWpLogoMenuItemsList() {
        String res = wpLogoMenuItemsList.getText();

        return List.of(res.split("\\n"));
    }

    public DashboardPage hoverOnFeedbackLogoMenuItem() {
        new Actions(driver)
                .moveToElement(feedbackLogoMenuItem)
                .perform();

        return new DashboardPage(driver);
    }

    public String getColorOfFeedbackLogoMenuItem() {
        return feedbackLogoMenuItem.getCssValue("color");
    }

    public DashboardPage hoverOnNewContentButton() {
        new Actions(driver)
                .moveToElement(newContentButton)
                .perform();

        return new DashboardPage(driver);
    }
    public NewPostPage clickNewPostButton() {
        newPostButton.click();
        return new NewPostPage(driver);
    }
    public List<String> getWpLogoMenuItemsList2() {
        List<String> menuItemsList = new ArrayList<>();

        for (WebElement item : wpLogoMenuItemsList2) {
            menuItemsList.add(item.getText());
        }
        return menuItemsList;
    }

    public DashboardPage hoverOnNewButton() {
        new Actions(driver)
                .moveToElement(newContentButton)
                .perform();

        return new DashboardPage(driver);
    }

    public NewUserPage clickNewUserButton() {
        newUserButton.click();

        return new NewUserPage(driver);
    }
}