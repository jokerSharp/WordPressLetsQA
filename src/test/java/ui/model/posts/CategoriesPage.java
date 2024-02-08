package ui.model.posts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

import java.util.List;

public class CategoriesPage extends BasePage {
    @FindBy(id = "tag-name")
    private WebElement nameInput;
    @FindBy(id = "submit")
    private WebElement addNewCategoryButton;
    @FindBy(xpath = "//tbody[@id='the-list']/tr/td[@data-colname='Name']/strong")
    private List <WebElement> categoryName;

    public CategoriesPage inputCategoryName(String name) {
        nameInput.sendKeys(name);

        return new CategoriesPage(getDriver());
    }
    public CategoriesPage clickAddNewCategory() {
        addNewCategoryButton.click();
        new Actions(getDriver()).pause(800).perform();

        return new CategoriesPage(getDriver());
    }
    public List<String> getCategoriesList() {
        return categoryName.stream().map(WebElement::getText).toList();
    }

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }
}
