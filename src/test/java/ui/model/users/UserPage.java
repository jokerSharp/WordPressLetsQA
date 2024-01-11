package ui.model.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

import java.util.List;

public class UserPage extends BasePage {

    @FindBy(xpath = "//strong/a[contains(@href,'http://localhost:8000/wp-admin/user-edit')]")
    private WebElement newCreatedUserUsername;

    @FindBy(xpath = "//tbody/tr/td/strong")
    private List<WebElement> allUsersList;

    @FindBy(css = ".submitdelete")
    private WebElement deleteUserButton;

    @FindBy(xpath = "//a[@href='profile.php']")
    private WebElement profileButton;

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public String getUsernameValue() {
        return newCreatedUserUsername.getText();
    }

    public int getUsersAmount() {
        return allUsersList.size();
    }

    public UserPage hoverOnCreatedUser() {
        new Actions(getDriver())
                .moveToElement(newCreatedUserUsername)
                .perform();

        return this;
    }

    public DeleteUsersPage clickDeleteUserButton() {
        deleteUserButton.click();

        return new DeleteUsersPage(getDriver());
    }

    public UserProfilePage goToUserProfilePage() {
        profileButton.click();

        return new UserProfilePage(getDriver());
    }
}
