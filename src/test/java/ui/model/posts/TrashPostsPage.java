package ui.model.posts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.AllPostsBasePage;

import java.util.List;

public class TrashPostsPage extends AllPostsBasePage {

    @FindBy(id = "delete_all")
    private WebElement emptyTrashButton;

    @FindBy(xpath = "//p[contains(text(), 'permanently deleted.')]")
    private WebElement permanentlyDeletedMessage;

    public TrashPostsPage(WebDriver driver) {
        super(driver);
    }


    public TrashPostsPage clickEmptyTrashButton() {
        emptyTrashButton.click();
        return this;
    }

    public boolean permanentlyDeletedMessageIsVisible() {
        return permanentlyDeletedMessage.isDisplayed();
    }
}
