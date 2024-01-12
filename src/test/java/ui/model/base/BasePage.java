package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.*;
import ui.model.posts.NewPostPage;
import ui.model.users.NewUserPage;
import ui.model.users.UserPage;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage extends BaseModel{

    protected BasePage(WebDriver driver) {
        super(driver);
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
