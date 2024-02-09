package ui.model.installation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class AdminRegistrationPage extends BasePage {
    @FindBy(id = "weblog_title")
    WebElement weblogTitleInput;

    @FindBy(id = "user_login")
    WebElement userLoginInput;

    @FindBy(id = "pass1")
    WebElement passwordInput;

    @FindBy(className = "pw-checkbox")
    WebElement weakPasswordCheckBox;

    @FindBy(id = "admin_email")
    WebElement adminEmailInput;

    @FindBy(id = "submit")
    WebElement submitButton;

    public AdminRegistrationPage(WebDriver driver) {
        super(driver);
        waitForGeneratedPassword();
    }

    public String getPassword() {
        return passwordInput.getAttribute("value");
    }

    private void waitForGeneratedPassword() {
        getWait5().until(driver -> !getPassword().isEmpty());
    }

    public AdminRegistrationPage inputWeblogTitle(String title) {
        weblogTitleInput.sendKeys(title);
        return this;
    }

    public AdminRegistrationPage inputUsername(String userName) {
        userLoginInput.sendKeys(userName);
        return this;
    }

    public AdminRegistrationPage clearPassword() {
        passwordInput.clear();
        return this;
    }

    public AdminRegistrationPage inputPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public AdminRegistrationPage agreeWithWeakPassword() {
        weakPasswordCheckBox.click();
        return this;
    }

    public AdminRegistrationPage inputAdminEmail(String email) {
        adminEmailInput.sendKeys(email);
        return this;
    }

    public SuccessfulInstallationPage submit() {
        submitButton.click();
        return new SuccessfulInstallationPage(getDriver());
    }
}
