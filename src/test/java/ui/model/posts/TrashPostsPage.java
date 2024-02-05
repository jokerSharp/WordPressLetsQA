package ui.model.posts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

import java.util.List;

public class TrashPostsPage extends BasePage {

    @FindBy(id = "cb-select-all-1")
    private WebElement allPostsCheckbox;

    @FindBy(id = "delete_all")
    private WebElement emptyTrashButton;

    @FindBy(css = "#the-list > tr > td > strong")
    private List<WebElement> postsList;

    @FindBy(xpath = "//p[contains(text(), 'permanently deleted.')]")
    private WebElement permanentlyDeletedMessage;

    public TrashPostsPage(WebDriver driver) {
        super(driver);
    }

    public TrashPostsPage clickSelectAllPostsCheckbox() {
        allPostsCheckbox.click();
        return this;
    }

    public TrashPostsPage clickEmptyTrashButton() {
        emptyTrashButton.click();
        return this;
    }

    public int getPostsListSize() {
        return postsList.size();
    }

    public boolean permanentlyDeletedMessageIsVisible() {
        return permanentlyDeletedMessage.isDisplayed();
    }
}
