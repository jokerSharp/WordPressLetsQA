package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class DashboardPage extends BasePage {

    @FindBy(id = "contextual-help-link")
    private WebElement helpButton;

    @FindBy(id = "tab-link-overview")
    private WebElement tabOverview;

    @FindBy(xpath = "//div[contains(text(), 'Dashboard')]")
    private WebElement dashboardSideMenuButtonText;

    @FindBy(xpath = "//div[contains(text(), 'Dashboard')]//parent::a")
    private WebElement getDashboardSideMenuButtonFull;

    public String getTitle() {
        return getDriver().getTitle();
    }

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage helpButtonClick(){
        helpButton.click();
        return new DashboardPage(getDriver());
    }

    public boolean tabOverviewIsDisplayed(){
        return tabOverview.isDisplayed();
    }

    public DashboardPage clickDashboardSideMenuButton() {
        dashboardSideMenuButtonText.click();
        return new DashboardPage(getDriver());
    }

    public String getDashboardSideMenyButtonColor() {
        return getDashboardSideMenuButtonFull.getCssValue("background");
    }

}
