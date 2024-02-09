package ui.model;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.base.BasePage;
import ui.model.base.Header;
import ui.model.base.SidePanel;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {

    private Header header;

    private SidePanel sidePanel;

    @FindBy(id = "contextual-help-link")
    private WebElement helpButton;

    @FindBy(id = "tab-link-overview")
    private WebElement tabOverview;

    @FindBy(id = "show-settings-link")
    private WebElement screenOptionsButton;

    @FindBy(css = ".metabox-prefs legend")
    private WebElement screenOptionsTitle;

    @FindBy(xpath = "//div[contains(text(), 'Dashboard')]//parent::a")
    private WebElement getDashboardSideMenuButtonFull;

    @FindBy(xpath = "//label[@for='wp_welcome_panel-hide']")
    private WebElement welcomePanelHideCheckbox;

    @FindBy(css = "div[class='welcome-panel-header'] h2")
    private WebElement welcomePanelTitle;

    @FindBy(xpath = "//label[@for='dashboard_site_health-hide']")
    private WebElement siteHealthStatusCheckbox;

    @FindBy(xpath = "//h2[normalize-space()='Site Health Status']")
    private  WebElement siteHealthStatusPanel;

    @FindBy(xpath = "//label[@for='dashboard_right_now-hide']")
    private WebElement atAGlanceCheckbox;

    @FindBy(xpath = "//h2[normalize-space()='At a Glance']")
    private  WebElement atAGlancePanel;

    @FindBy(xpath = "//label[@for='dashboard_activity-hide']")
    private WebElement activityCheckbox;

    @FindBy(xpath = "//h2[normalize-space()='Activity']")
    private  WebElement activityPanel;

    @FindBy(xpath = "//label[@for='dashboard_quick_press-hide']")
    private WebElement quickDraftCheckbox;

    @FindBy(xpath = "//h2/span[normalize-space()='Quick Draft']")
    private  WebElement quickDraftPanel;

    @FindBy(id = "community-events")
    private WebElement eventsAndNewsFrame;

    @FindBy(xpath = "//a[@href = 'https://make.wordpress.org/community/meetups-landing-page']")
    private WebElement meetupsLink;

    @FindBy(xpath = "//a[@href = 'https://central.wordcamp.org/schedule/']")
    private WebElement wordCampsLink;

    @FindBy(xpath = "//a[@href = 'https://wordpress.org/news/']")
    private WebElement newsLink;

    @FindBy(css = ".community-events-footer > a")
    private List<WebElement> outerPagesLinks;

    @FindBy(css = ".contextual-help-sidebar > p > a")
    private List<WebElement> forMoreInformationOuterPagesLinksInHelpPanel;

    @FindBy(xpath = "//div[@class = 'contextual-help-sidebar']//a[contains(text(), 'Documentation')]")
    private WebElement documentationOnDashboardLink;

    @FindBy(xpath = "//div[@class = 'contextual-help-sidebar']//a[contains(text(), 'Support')]")
    private WebElement supportForumsLink;

    @FindBy(xpath = "//div[@class = 'contextual-help-sidebar']//a[contains(text(), 'Version')]")
    private WebElement versionLink;

    @FindBy(id = "footer-upgrade")
    private WebElement footerVersion;

    public DashboardPage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
        sidePanel = new SidePanel(driver);
    }

    public Header getHeader() {
        return header;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
     }

    public DashboardPage clickHelpButton(){
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

    public String getDashboardSideMenyButtonColor() {
        return getDashboardSideMenuButtonFull.getCssValue("background");
    }

    public DashboardPage clickCheckboxWelcomePanelHide(){
        welcomePanelHideCheckbox.click();

        return new DashboardPage(getDriver());
    }

    public boolean verifyWelcomePanelIsOpened(){
       return welcomePanelTitle.isDisplayed();
    }

    public DashboardPage clickCheckboxSiteHealthStatus(){
        siteHealthStatusCheckbox.click();

        return new DashboardPage(getDriver());
    }

    public boolean checkSiteHealthStatusPanelIsVisible(){
        return siteHealthStatusPanel.isDisplayed();
    }

    public DashboardPage clickCheckboxAtAGlance(){
        atAGlanceCheckbox.click();

        return new DashboardPage(getDriver());
    }

    public boolean checkAtAGlancePanelPanelIsVisible(){
        return atAGlancePanel.isDisplayed();
    }

    public DashboardPage clickCheckboxActivity(){
        activityCheckbox.click();

        return new DashboardPage(getDriver());
    }

    public boolean checkActivityPanelIsVisible(){
        return activityPanel.isDisplayed();
    }

    public DashboardPage clickCheckboxQuickDraft(){
        quickDraftCheckbox.click();

        return new DashboardPage(getDriver());
    }

    public boolean checkQuickDraftPanelIsVisible(){
        return quickDraftPanel.isDisplayed();
    }

    public OuterPage scrollAndClickMeetupsButton() {
        getWait5().until(ExpectedConditions.visibilityOf(eventsAndNewsFrame));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", meetupsLink);

        meetupsLink.click();

        return new OuterPage(getDriver());
    }

    public List<String> outerPagesLinksList() {
        List<String> outerPagesLinksList = new ArrayList<>();

        for (WebElement e : outerPagesLinks) {
            outerPagesLinksList.add(e.getAttribute("href"));
        }

        return outerPagesLinksList;
    }

    public OuterPage scrollAndClickWordCampLink() {
        getWait5().until(ExpectedConditions.visibilityOf(eventsAndNewsFrame));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", wordCampsLink);

        wordCampsLink.click();

        return new OuterPage(getDriver());
    }

    public OuterPage scrollAndClickNewsLink() {
        getWait5().until(ExpectedConditions.visibilityOf(eventsAndNewsFrame));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", newsLink);

        newsLink.click();

        return new OuterPage(getDriver());
    }

    public List<String> helpPanelOuterPagesLinksList() {
        List<String> outerPagesLinksList = new ArrayList<>();

        for (WebElement e : forMoreInformationOuterPagesLinksInHelpPanel) {
            outerPagesLinksList.add(e.getAttribute("href"));
        }

        return outerPagesLinksList;
    }

    public OuterPage clickDocumentationOnDashboardLink() {
        documentationOnDashboardLink.click();

        return new OuterPage(getDriver());
    }

    public OuterPage clickSupportForumsLink() {
        supportForumsLink.click();

        return new OuterPage(getDriver());
    }

    public OuterPage clickVersionLink() {
        versionLink.click();

        return new OuterPage(getDriver());
    }
}
