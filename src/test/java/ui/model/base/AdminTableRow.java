package ui.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdminTableRow {
    private WebDriver driver;
    private WebElement root;
    @FindBy(css = ".row-actions a, .row-actions button")
    private List<WebElement> actions;
    @FindBy(css = "td")
    private List<WebElement> data;

    public AdminTableRow(WebDriver driver, WebElement root) {
        PageFactory.initElements(root, this);
        this.driver = driver;
        this.root = root;
    }

    public String getField(int index) {
        return data.get(index).getText();
    }

    public void doAction(String action) {
        hover();
        for (WebElement actionElement : actions) {
            if (actionElement.getText().equals(action)) {
                actionElement.click();
                return;
            }
        }
        throw new RuntimeException("Action not found");
    }

    public void hover() {
        new Actions(driver)
                .moveToElement(root)
                .perform();
    }
}
