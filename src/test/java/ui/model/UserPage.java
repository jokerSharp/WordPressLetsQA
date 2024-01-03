package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class UserPage extends BasePage {

    @FindBy(xpath = "//strong/a[contains(@href,'http://localhost:8000/wp-admin/user-edit')]")
    private WebElement newAddedUserUsername;

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public String getUsernameValue() {
        return newAddedUserUsername.getText();
    }
}
