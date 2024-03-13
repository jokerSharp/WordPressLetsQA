package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.plugins.AddPluginsPage;
import ui.model.settings.PermalinkSettingsPage;
import ui.model.api.SwaggerSettingPage;
import ui.model.appearance.AppearancePage;
import ui.model.comments.CommentsPage;
import ui.model.pages.NewPagePage;
import ui.model.posts.AllPostsPage;
import ui.model.posts.CategoriesPage;
import ui.model.posts.TagsPage;
import ui.model.tools.ToolsPage;
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

    @FindBy(id = "menu-pages")
    private WebElement sideMenuPageButton;

    @FindBy(xpath =  "//a[contains(text(), 'Add New Page')]")
    private WebElement addNewPageButton;

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

    @FindBy(id = "menu-appearance")
    private WebElement sideMenuAppearanceButton;

    @FindBy(id = "collapse-button")
    private WebElement collapseMenuButton;

    @FindBy(id = "adminmenuwrap")
    private WebElement theWholeSidePanel;

    @FindBy(xpath = "//div[contains(text(), 'Tools')]")
    private WebElement sideMenuToolsButton;

    @FindBy(xpath = "//a[@href='import.php']")
    private WebElement subMenuToolsImportButton;

    public SidePanel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public UserPage goToUserPage() {
        sideMenuUsersButton.click();

        return new UserPage(getDriver());
    }

    public AllPostsPage clickSideMenuPostsButton() {
        sideMenuPostsButton.click();

        return new AllPostsPage(getDriver());
    }

    public CategoriesPage clickSideMenuCategoriesButton() {
        sideMenuCategoriesButton.click();

        return new CategoriesPage(getDriver());
    }

    public TagsPage clickSideMenuTagsButton() {
        sideMenuTagsButton.click();

        return new TagsPage(getDriver());
    }

    public CommentsPage clickSideMenuCommentsButton() {
        sideMenuCommentsButton.click();

        return new CommentsPage(getDriver());
    }

    public SidePanel hoverOnSideMenuPageButton() {
        new Actions(getDriver())
                .moveToElement(sideMenuPageButton)
                .perform();

        return this;
    }

    public NewPagePage clickAddNewPageButton() {
        return clickAddNewPageButton(true);
    }

    public NewPagePage clickAddNewPageButton(boolean skipWelcomeDialog) {
        addNewPageButton.click();

        return new NewPagePage(getDriver(), skipWelcomeDialog);
    }

    public SidePanel hoverSideMenuSettingsButton() {
        new Actions(getDriver()).moveToElement(sideMenuSettingsButton)
                        .perform();

        return this;
    }

    public PermalinkSettingsPage clickSideMenuSettingsPermalinksChoice() {
        sideMenuSettingsPermalinksChoice.click();

        return new PermalinkSettingsPage(getDriver());
    }

    public SidePanel hoverSideMenuPluginsButton() {
        new Actions(getDriver())
                .moveToElement(sideMenuPluginsButton)
                .perform();

        return this;
    }

    public AddPluginsPage clickAddNewPluginButton() {
        addNewPluginButton.click();

        return new AddPluginsPage(getDriver());
    }

    public SwaggerSettingPage clickSideMenuSettingsSwaggerButton() {
        sideMenuSettingsSwaggerButton.click();

        return new SwaggerSettingPage(getDriver());
    }

    public AppearancePage clickSideMenuAppearanceButton() {
        sideMenuAppearanceButton.click();

        return new AppearancePage(getDriver());
    }

    public SidePanel clickSideMenuCollapseMenuButton() {
        collapseMenuButton.click();

        return this;
    }

    public String getTheWholeSidePanelWidth() {
        return theWholeSidePanel.getCssValue("width");
    }

    public SidePanel hoverOnSideMenuToolsButton() {
        new Actions(getDriver())
                .moveToElement(sideMenuToolsButton)
                .perform();

        return this;
    }

    public ToolsPage clickSubMenuToolsImportButton() {
        subMenuToolsImportButton.click();

        return new ToolsPage(driver);
    }
}
