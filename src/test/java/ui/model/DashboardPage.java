package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class DashboardPage extends BasePage {

    @FindBy(id = "contextual-help-link")
    private WebElement helpButton;

    @FindBy(id = "tab-link-overview")
    private WebElement tabOverview;

    @FindBy(id = "show-settings-link")
    private WebElement screenOptionsButton;

    @FindBy(css = ".metabox-prefs legend")
    private WebElement screenOptionsTitle;

    @FindBy(xpath = "//div[contains(text(), 'Dashboard')]")
    private WebElement dashboardSideMenuButtonText;

    @FindBy(xpath = "//div[contains(text(), 'Dashboard')]//parent::a")
    private WebElement getDashboardSideMenuButtonFull;

    @FindBy(xpath = "//label[@for='wp_welcome_panel-hide']")
    private WebElement checkboxWelcomePanelHide;

    @FindBy(css = "div[class='welcome-panel-header'] h2")
    private WebElement welcomePanelTitle;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public DashboardPage helpButtonClick(){
        helpButton.click();
        return new DashboardPage(getDriver());
    }

    public boolean tabOverviewIsDisplayed(){
        return tabOverview.isDisplayed();
    }

    public DashboardPage screenOptionsButtonClick(){
        screenOptionsButton.click();
        return  new DashboardPage(getDriver());
    }

    public String getScreenOptionsTitle(){
       return screenOptionsTitle.getText();
    }

    public DashboardPage clickDashboardSideMenuButton() {
        dashboardSideMenuButtonText.click();
        return new DashboardPage(getDriver());
    }

    public String getDashboardSideMenyButtonColor() {
        return getDashboardSideMenuButtonFull.getCssValue("background");
    }

    public DashboardPage clickCheckboxWelcomePanelHide(){
        checkboxWelcomePanelHide.click();

        return new DashboardPage(getDriver());
    }

    public boolean verifyWelcomePanelIsOpened(){
       return welcomePanelTitle.isDisplayed();
    }



}
