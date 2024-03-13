package ui.model.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.base.BasePage;

public class ToolsPage extends BasePage {

    @FindBy(css = ".install-now[data-name=\"LiveJournal\"]")
    private WebElement liveJournalInstallNowButton;

    @FindBy(css = "#install-success > p")
    private WebElement importConfirmationField;

    public ToolsPage(WebDriver driver) {
        super(driver);
    }

    public ToolsPage clickliveJournalInstallNowButton() {
        liveJournalInstallNowButton.click();

        return new ToolsPage(getDriver());
    }

    public String getImportConfirmationText() {

        return getWait10().until(ExpectedConditions.visibilityOf(importConfirmationField)).getText().substring(0, 32);
    }
}
