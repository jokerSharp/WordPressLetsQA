package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.posts.AllPostsPage;
import ui.model.posts.CategoriesPage;
import ui.model.posts.ViewPostPage;
import ui.model.users.UserPage;

public class SidePanel {

    private final WebDriver driver;

    @FindBy(id = "menu-users")
    private WebElement sideMenuUsersButton;

    @FindBy(xpath = "//div[contains(text(), 'Posts')]")
    private WebElement sideMenuPostsButton;

    @FindBy(xpath = "//a[text()='Categories']")
    private WebElement sideMenuCategoriesButton;

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

}
