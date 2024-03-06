package ui.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class AddPluginsPage extends BasePage {

    @FindBy(id = "search-plugins")
    private WebElement searchPluginsTextArea;

    @FindBy(css = ".install-now.button[data-slug='wp-api-swaggerui']")
    private WebElement installWpApiSwaggerUiPlugin;

    @FindBy(css = ".button.activate-now[data-slug='wp-api-swaggerui']")
    private WebElement activateWpApiSwaggerUiPlugin;

    public AddPluginsPage(WebDriver driver) {
        super(driver);
    }

    public AddPluginsPage enterTextToSearchPluginsTextArea(String textForSearch) {
        new Actions(getDriver())
                .pause(500)
                .moveToElement(getDriver().findElement(By.id("search-plugins")))
                .click()
                .pause(500)
                .sendKeys(textForSearch)
                .perform();

        return new AddPluginsPage(getDriver());
    }

    public AddPluginsPage clickInstallWpApiSwaggerUiPlugin() {
        installWpApiSwaggerUiPlugin.click();

        return new AddPluginsPage(getDriver());
    }

    public PluginsPage clickActivateWpApiSwaggerUiPlugin() {
        new Actions(getDriver())
                .pause(2000)
                .moveToElement(activateWpApiSwaggerUiPlugin)
                .click()
                .perform();

        return new PluginsPage(getDriver());
    }
}
