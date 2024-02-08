package ui.model.posts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ui.model.base.AllPostsBasePage;

public class AllPostsPage extends AllPostsBasePage {

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

    public AllPostsPage clickTrashButtonUnderPostTitle(String titleName) {
        getDriver()
                .findElement(By.xpath(String.format("//span[@class='trash']/a[contains(@aria-label, '%s')]", titleName)))
                .click();

        return new AllPostsPage(getDriver());
    }
}
