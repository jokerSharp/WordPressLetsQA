package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.AddPluginsPage;
import ui.model.PermalinkSettingsPage;
import ui.model.SwaggerSettingPage;
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

    @FindBy(xpath = "//div[text()='Plugins ']")
    private WebElement sideMenuPluginsButton;

    @FindBy(xpath = "//div[text()='Settings']")
    private WebElement sideMenuSettingsButton;

    @FindBy(xpath = "//a[@href='options-permalink.php']")
    private WebElement sideMenuSettingsPermalinksChoice;

    @FindBy(xpath = "//a[@href='plugin-install.php']")
    private WebElement addNewPluginButton;

    @FindBy(xpath = "//a[@href='options-general.php?page=swagger-ui']")
    private WebElement sideMenuSettingsSwaggerButton;

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

    public SidePanel hoverSideMenuSettingsButton() {
        new Actions(driver).moveToElement(sideMenuSettingsButton)
                        .perform();

        return this;
    }

    public PermalinkSettingsPage clickSideMenuSettingsPermalinksChoice() {
        sideMenuSettingsPermalinksChoice.click();

        return new PermalinkSettingsPage(driver);
    }

    public SidePanel hoverSideMenuPluginsButton() {
        new Actions(driver).moveToElement(sideMenuPluginsButton)
                        .perform();

        return this;
    }

    public AddPluginsPage clickAddNewPluginButton() {
        addNewPluginButton.click();

        return new AddPluginsPage(driver);
    }

    public SwaggerSettingPage clickSideMenuSettingsSwaggerButton() {
        sideMenuSettingsSwaggerButton.click();

        return new SwaggerSettingPage(driver);
    }

}
