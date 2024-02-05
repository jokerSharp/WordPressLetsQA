package ui.model.posts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;
import ui.model.base.Header;
import ui.model.base.SidePanel;

import java.util.List;

public class AllPostsPage extends BasePage {

    private Header header;

    private SidePanel sidePanel;

    @FindBy(css = "#the-list > tr > td > strong")
    private List<WebElement> postsList;

    @FindBy(id = "cb-select-all-1")
    private WebElement allPostsCheckbox;

    @FindBy(id = "cb-select-1")
    private WebElement helloWorldPostCheckbox;

    @FindBy(id = "bulk-action-selector-top")
    private WebElement bulkActionSelector;

    @FindBy(css = "option[value=\"trash\"]")
    private WebElement moveToTrashOption;

    @FindBy(id = "doaction")
    private WebElement applyButton;

    @FindBy(xpath = "//a[@href='edit.php?post_status=trash&post_type=post']")
    private WebElement trashLink;

    public AllPostsPage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
        sidePanel = new SidePanel(driver);
    }

    public AllPostsPage hoverOnPostTitle(String titleName) {
        new Actions(getDriver())
                .moveToElement(getDriver()
                        .findElement(By.xpath(String.format("//a[contains(text(), '%s')]", titleName))))
                .perform();

        return this;
    }

    public AllPostsPage clickTrashButton(String titleName) {
        getDriver()
                .findElement(By.xpath(String.format("//span[@class='trash']/a[contains(@aria-label, '%s')]", titleName)))
                .click();

        return new AllPostsPage(getDriver());
    }

    public int getPostsListSize() {
        return postsList.size();
    }

    public Header getHeader() {
        return header;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public AllPostsPage clickSelectAllPostsCheckbox() {
        allPostsCheckbox.click();
        return this;
    }

    public AllPostsPage clickHelloWorldPostCheckbox() {
        helloWorldPostCheckbox.click();
        return this;
    }

    public AllPostsPage clickBulkActionSelector() {
        bulkActionSelector.click();
        return this;
    }

    public AllPostsPage clickMoveToTrashOption() {
        moveToTrashOption.click();
        return this;
    }

    public AllPostsPage clickApplyButton() {
        applyButton.click();
        return this;
    }

    public TrashPostsPage clickTrashLink() {
        trashLink.click();
        return new TrashPostsPage(getDriver());
    }
}
