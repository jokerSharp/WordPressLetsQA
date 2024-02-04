package ui.model.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class UserPage extends BasePage {

    @FindBy(xpath = "//td[contains(@class,'username')]")
    private List<WebElement> allUsersList;

    @FindBy(css = ".submitdelete")
    private WebElement deleteUserButton;

    @FindBy(xpath = "//a[@href='profile.php']")
    private WebElement profileButton;

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getUsernames() {
        return allUsersList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public int getUsersAmount() {
        return allUsersList.size();
    }

    public UserPage hoverOnUser(String username) {
        WebElement userElement = allUsersList.stream().filter(element -> element.getText().equals(username)).findFirst().orElse(null);
        new Actions(getDriver())
                .moveToElement(userElement)
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
