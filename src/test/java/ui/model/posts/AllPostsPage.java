package ui.model.posts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class AllPostsPage extends BasePage {

    @FindBy(css = "#the-list > tr > td > strong")
    private List<WebElement> postsList;

    public AllPostsPage(WebDriver driver) {
        super(driver);
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
}
