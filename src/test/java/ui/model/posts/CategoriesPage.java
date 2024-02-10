package ui.model.posts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

        return new CategoriesPage(getDriver());
    }

    public CategoriesPage clickDeleteCategory(String name) {
        new Actions(getDriver())
                .moveToElement(getDriver()
                        .findElement(By.xpath("//a[text()='" + name + "']")))
                .moveToElement(getDriver()
                        .findElement(By.xpath("//a[@aria-label='Delete “" + name+ "”']")))
                .click()
                .perform();

        getWait2().until(ExpectedConditions.alertIsPresent()).accept();

        return new CategoriesPage(getDriver());
    }

    public List<String> getCategoriesList() {
        new Actions(getDriver()).pause(800).perform();

        return categoryName.stream()
                .map(WebElement::getText)
                .toList();
    }

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }
}
