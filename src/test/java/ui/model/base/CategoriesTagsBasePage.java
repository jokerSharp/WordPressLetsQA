package ui.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public abstract class CategoriesTagsBasePage <PostSupportPage extends CategoriesTagsBasePage<?>> extends BasePage {
    @FindBy(id = "tag-name")
    private WebElement nameInput;

    @FindBy(id = "submit")
    private WebElement addNewElementButton;

    @FindBy(xpath = "//tbody[@id='the-list']/tr/td[@data-colname='Name']/strong")
    private List<WebElement> elementName;

    @FindBy(id = "tag-search-input")
    private WebElement searchInput;

    @FindBy(id = "search-submit")
    private WebElement searchButton;
    @FindBy(xpath = "//div[@class='tablenav top']/div[@class='tablenav-pages one-page']/span[@class='displaying-num']")
    private WebElement topItemCounter;

    protected abstract PostSupportPage createPage();

    public PostSupportPage inputElementName(String name) {
        nameInput.sendKeys(name);

        return createPage();
    }
    public PostSupportPage clickAddNewElement() {
        addNewElementButton.click();

        return createPage();
    }

    public PostSupportPage clickDeleteElement(String name) {
        new Actions(getDriver())
                .moveToElement(getDriver()
                    .findElement(By.xpath("//a[text()='" + name + "']")))
                .moveToElement(getDriver()
                    .findElement(By.xpath("//a[@aria-label='Delete “" + name+ "”']")))
                .click()
                .perform();

        getWait2().until(ExpectedConditions.alertIsPresent()).accept();

        return createPage();
    }

    public List<String> getElementsList() {
        new Actions(getDriver())
                .pause(800)
                .perform();

        return elementName.stream()
                             .map(WebElement::getText)
                             .toList();
    }

    public PostSupportPage inputSearchValue(String value) {
        searchInput.sendKeys(value);

        return createPage();
    }

    public PostSupportPage clickSearchButton() {
        searchButton.click();

        return createPage();
    }

    public String getItemCounter() {
        return topItemCounter.getText();
    }

    public CategoriesTagsBasePage(WebDriver driver) {
        super(driver);
    }
}
