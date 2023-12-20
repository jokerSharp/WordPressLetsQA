package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    private final WebDriver driver;
    protected WebDriver getDriver() {
        return driver;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
