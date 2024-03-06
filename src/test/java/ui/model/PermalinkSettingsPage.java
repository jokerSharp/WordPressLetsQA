package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;
import ui.model.base.Header;
import ui.model.base.SidePanel;

public class PermalinkSettingsPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), '%year%')] ")
    private WebElement customStructureYear;

    @FindBy(css = "#submit")
    private WebElement submit;

    @FindBy(css = "#setting-error-settings_updated")
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

        return new PermalinkSettingsPage(getDriver());
    }

    public String getTextSettingsUpdated() {

        return settingsUpdated.getText();
    }

}
