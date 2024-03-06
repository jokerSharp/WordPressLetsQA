package ui.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.PermalinkSettingsPage;
import ui.model.comments.CommentsPage;
import ui.model.posts.AllPostsPage;
import ui.model.posts.CategoriesPage;
import ui.model.posts.TagsPage;
import ui.model.users.UserPage;

public class SidePanel {

    private final WebDriver driver;

    @FindBy(id = "menu-users")
    private WebElement sideMenuUsersButton;

    @FindBy(xpath = "//div[contains(text(), 'Posts')]")
    private WebElement sideMenuPostsButton;

    @FindBy(id = "menu-comments")
    private WebElement sideMenuCommentsButton;

    @FindBy(xpath = "//a[text()='Categories']")
    private WebElement sideMenuCategoriesButton;

    @FindBy(xpath = "//a[text()='Tags']")
    private WebElement sideMenuTagsButton;

    @FindBy(xpath = "//div[text()='Settings']")
    private WebElement sideMenuSettingsButton;

    @FindBy(xpath = "//a[@href='options-permalink.php']")
    private WebElement sideMenuSettingsPermalinksChoice;

    public SidePanel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UserPage goToUserPage() {
        sideMenuUsersButton.click();

        return new UserPage(driver);
    }

    public AllPostsPage clickSideMenuPostsButton() {
        sideMenuPostsButton.click();

        return new AllPostsPage(driver);
    }

    public CategoriesPage clickSideMenuCategoriesButton() {
        sideMenuCategoriesButton.click();

        return new CategoriesPage(driver);
    }

    public TagsPage clickSideMenuTagsButton() {
        sideMenuTagsButton.click();

        return new TagsPage(driver);
    }

    public CommentsPage clickSideMenuCommentsButton() {
        sideMenuCommentsButton.click();

        return new CommentsPage(driver);
    }

    public SidePanel clickSideMenuSettingsButton() {
        sideMenuSettingsButton.click();

        return this;
    }

    public PermalinkSettingsPage clickSideMenuSettingsPermalinksChoice() {
        sideMenuSettingsPermalinksChoice.click();

        return new PermalinkSettingsPage(driver);
    }

//    public PermalinkSettingsPage clickSideMenuSettingsPermalinks() {
//        new Actions(driver).moveToElement(driver.findElement(By.xpath(sideMenuSettingsButton)))
//                .moveToElement(driver.findElement(By.xpath(sideMenuSettingsPermalinksChoice)))
//                .click()
//                .perform();
//
//        return new PermalinkSettingsPage(driver);
//    }

}
