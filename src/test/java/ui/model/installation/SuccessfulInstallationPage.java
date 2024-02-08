package ui.model.installation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.LoginPage;
import ui.model.base.BasePage;

public class SuccessfulInstallationPage extends BasePage {

    @FindBy(xpath = "//a[text() ='Log In']")
    private WebElement loginButton;

    public SuccessfulInstallationPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage proceedToLogin() {
       try{ loginButton.click();}
       catch (NoSuchElementException e){
           System.out.println();
       }
        return new LoginPage(getDriver());
    }
}
