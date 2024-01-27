package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.users.UserPage;

public class SidePanel {

    private final WebDriver driver;

    @FindBy(id = "menu-users")
    private WebElement sideMenuUsersButton;

    public SidePanel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UserPage goToUserPage() {
        sideMenuUsersButton.click();

        return new UserPage(driver);
    }
}
