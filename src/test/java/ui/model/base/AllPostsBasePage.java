package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.posts.CreateEditPostPage;
import ui.model.posts.TrashPostsPage;

import java.util.ArrayList;
import java.util.List;

public abstract class AllPostsBasePage<Self extends AllPostsBasePage<?>> extends BasePage{
    private Header header;

    private SidePanel sidePanel;

    @FindBy(css = "#the-list > tr > td > strong")
    private List<WebElement> postsList;

    @FindBy(css = "#the-list > tr > td > strong > a")
    private List<WebElement> postsClicableTitlesList;

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

    @FindBy (xpath = "//tbody[@id = 'the-list']//td[@class = 'tags column-tags']/a")
    private List<WebElement> tagsList;

    protected AllPostsBasePage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
        sidePanel = new SidePanel(driver);
    }

    public int getPostsListSize() {
        return postsList.size();
    }

    public CreateEditPostPage clickAnyPostTitle() {
        if (postsClicableTitlesList != null) {
            int max = postsClicableTitlesList.size();
            int min = 0;
            int randomPost = (int) (Math.random()*(max-min)+min);
            postsClicableTitlesList.get(randomPost).click();
        } else {
            System.out.println("No posts found!");
        }
        return new CreateEditPostPage(getDriver());
    }

    public Header getHeader() {
        return header;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public Self clickSelectAllPostsCheckbox() {
        allPostsCheckbox.click();
        return (Self) this;
    }

    public Self clickHelloWorldPostCheckbox() {
        helloWorldPostCheckbox.click();
        return (Self) this;
    }

    public Self clickBulkActionSelector() {
        bulkActionSelector.click();
        return (Self) this;
    }

    public Self clickMoveToTrashOption() {
        moveToTrashOption.click();
        return (Self) this;
    }

    public Self clickApplyButton() {
        applyButton.click();
        return (Self) this;
    }

    public TrashPostsPage clickTrashLink() {
        trashLink.click();
        return new TrashPostsPage(getDriver());
    }

    public List<String> getTagsList() {
        List<String> tags = new ArrayList<>();

        for (WebElement e : tagsList) {
            tags.add(e.getText());
        }
        return tags;
    }
}
