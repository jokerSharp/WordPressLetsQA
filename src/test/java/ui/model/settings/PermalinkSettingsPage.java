package ui.model.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.base.BasePage;

public class PermalinkSettingsPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), '%year%')] ")
    private WebElement customStructureYear;

    @FindBy(css = "#submit")
    private WebElement submit;

    @FindBy(xpath = "//*[@id='setting-error-settings_updated']/p/strong")
    private WebElement settingsUpdated;

    public PermalinkSettingsPage(WebDriver driver) {
        super(driver);
    }

    public PermalinkSettingsPage setCustomStructureYear() {
        customStructureYear.click();

        return this;
    }

    public PermalinkSettingsPage submit() {
        submit.click();

        return this;
    }

    public String getTextSettingsUpdated() {
        getWait2().until(ExpectedConditions.visibilityOf(settingsUpdated));

        return settingsUpdated.getText();
    }

}
