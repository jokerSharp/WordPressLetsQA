package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "login-message")
    private WebElement loginMessage;

    @FindBy(id = "user_login")
    private WebElement usernameInput;

    @FindBy(id = "user_pass")
    private WebElement passwordInput;

    @FindBy(id = "wp-submit")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    static public LoginPage open(WebDriver driver, String baseUrl) {
        driver.get(baseUrl + "wp-admin/");
        return new LoginPage(driver);
    }

    public DashboardPage login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
        return new DashboardPage(getDriver());
    }

    public String getLoginMessageText() {
        return loginMessage.getText();
    }
}
