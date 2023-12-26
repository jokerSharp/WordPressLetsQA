package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.DashboardPage;
import ui.model.LoginPage;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {

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

    private final WebDriver driver;

    protected WebDriver getDriver() {
        return driver;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public String getWpLogoIconColor() {
        return wpLogoIcon.getCssValue("color");
    }
}
