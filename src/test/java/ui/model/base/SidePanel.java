package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.comments.CommentsPage;
import ui.model.pages.NewPagePage;
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

    @FindBy(id = "menu-pages")
    private WebElement sideMenuPageButton;

    @FindBy(xpath =  "//a[contains(text(), 'Add New Page')]")
    private WebElement addNewPageButton;

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

    public SidePanel hoverOnSideMenuPageButton() {
        new Actions(driver)
                .moveToElement(sideMenuPageButton)
                .perform();

        return this;
    }

    public NewPagePage clickAddNewPageButton() {
        return clickAddNewPageButton(true);
    }

    public NewPagePage clickAddNewPageButton(boolean skipWelcomeDialog) {
        addNewPageButton.click();

        return new NewPagePage(driver, skipWelcomeDialog);
    }

}
