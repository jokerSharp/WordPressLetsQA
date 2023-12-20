package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "login-message")
    private WebElement loginMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getLoginMessageText() {
        return loginMessage.getText();
    }
}
