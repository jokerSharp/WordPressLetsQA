package ui.model.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class DeleteUsersPage extends BasePage {

    @FindBy(id = "submit")
    private WebElement confirmDeletionButton;

    public DeleteUsersPage(WebDriver driver) {
        super(driver);
    }

    public UserPage clickConfirmDeletionButton() {
        confirmDeletionButton.click();

        return new UserPage(getDriver());
    }
}
