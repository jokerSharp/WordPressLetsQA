package ui.model.installation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.LoginPage;
import ui.model.base.BasePage;
import ui.runner.LoggerUtils;

public class SuccessfulInstallationPage extends BasePage {

    @FindBy(xpath = "//a[text() ='Log In']")
    private WebElement loginButton;

    public SuccessfulInstallationPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage proceedToLogin() {
       try{ loginButton.click();}
       catch (NoSuchElementException e){
           LoggerUtils.logError("NoSuchElementException - SuccessfulInstallationPage");
       }
        return new LoginPage(getDriver());
    }
}
