package ui.model.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class NewUserPage extends BasePage {

    @FindBy(id = "user_login")
    private WebElement usernameInputField;

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "first_name")
    private WebElement firstNameInputField;

    @FindBy(id = "last_name")
    private WebElement lastNameInputField;

    @FindBy(id = "url")
    private WebElement websiteInputField;

    @FindBy(id = "createusersub")
    private WebElement addNewUserButton;

    public NewUserPage(WebDriver driver) {
        super(driver);
    }

    public NewUserPage typeUsername(String username) {
        usernameInputField.click();
        usernameInputField.sendKeys(username);

        return this;
    }

    public NewUserPage typeEmail(String email) {
        emailInputField.click();
        emailInputField.sendKeys(email);

        return this;
    }

    public NewUserPage typeFirstName(String firstName) {
        firstNameInputField.click();
        firstNameInputField.sendKeys(firstName);

        return this;
    }

    public NewUserPage typeLastName(String lastName) {
        lastNameInputField.click();
        lastNameInputField.sendKeys(lastName);

        return this;
    }

    public NewUserPage typeWebsite(String website) {
        websiteInputField.click();
        websiteInputField.sendKeys(website);

        return this;
    }

    public UserPage clickAddNewUserButton() {
        addNewUserButton.click();

        return new UserPage(getDriver());
    }
}
