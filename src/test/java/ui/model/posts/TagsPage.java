package ui.model.posts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

import java.util.List;

public class TagsPage extends BasePage {
    @FindBy(id = "tag-name")
    private WebElement nameInput;

    @FindBy(id = "submit")
    private WebElement addNewTagButton;

    @FindBy(xpath = "//tbody[@id='the-list']/tr/td[@data-colname='Name']/strong")
    private List<WebElement> tagsName;

    public TagsPage inputTagName(String name) {
        nameInput.sendKeys(name);

        return new TagsPage(getDriver());
    }
    public TagsPage clickAddNewTag() {
        addNewTagButton.click();

        return new TagsPage(getDriver());
    }

    public List<String> getTagsList() {
        new Actions(getDriver()).pause(800).perform();

        return tagsName.stream()
                .map(WebElement::getText)
                .toList();
    }

    public TagsPage(WebDriver driver) {
        super(driver);
    }
}
