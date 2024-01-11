package ui.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.model.base.BasePage;
import ui.runner.BaseTest;

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

    @FindBy(xpath = "//label[@for='dashboard_site_health-hide']")
    private WebElement checkboxSiteHealthStatus;

    @FindBy(xpath = "//h2[normalize-space()='Site Health Status']")
   private  WebElement siteHealthStatusPanel;

    @FindBy(xpath = "//label[@for='dashboard_right_now-hide']")
    private WebElement checkboxAtAGlance;

    @FindBy(xpath = "//h2[normalize-space()='At a Glance']")
    private  WebElement AtAGlancePanel;

    @FindBy(xpath = "//label[@for='dashboard_activity-hide']")
    private WebElement checkboxActivity;

    @FindBy(xpath = "//h2[normalize-space()='Activity']")
    private  WebElement ActivityPanel;

    @FindBy(xpath = "//label[@for='dashboard_quick_press-hide']")
    private WebElement checkboxQuickDraft;

    @FindBy(xpath = "//h2/span[normalize-space()='Quick Draft']")
    private  WebElement QuickDraftPanel;

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
        getWait2().until(ExpectedConditions.textToBePresentInElement(tabOverview, "Overview"));
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

    public DashboardPage clickCheckboxSiteHealthStatus(){
        checkboxSiteHealthStatus.click();

        return new DashboardPage(getDriver());
    }

    public boolean checkSiteHealthStatusPanelIsVisible(){
        return siteHealthStatusPanel.isDisplayed();
    }

    public DashboardPage clickCheckboxAtAGlance(){
        checkboxAtAGlance.click();

        return new DashboardPage(getDriver());
    }

    public boolean checkAtAGlancePanelPanelIsVisible(){
        return AtAGlancePanel.isDisplayed();
    }

    public DashboardPage clickCheckboxActivity(){
        checkboxActivity.click();

        return new DashboardPage(getDriver());
    }

    public boolean checkActivityPanelIsVisible(){
        return ActivityPanel.isDisplayed();
    }

    public DashboardPage clickCheckboxQuickDraft(){
        checkboxQuickDraft.click();

        return new DashboardPage(getDriver());
    }

    public boolean checkQuickDraftPanelIsVisible(){
        return QuickDraftPanel.isDisplayed();
    }

}
