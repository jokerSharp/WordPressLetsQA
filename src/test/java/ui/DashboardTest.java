package ui;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.OuterPage;
import ui.runner.BaseTest;

import java.util.ArrayList;

public class DashboardTest extends BaseTest {

    @Test
    public void testDashboardSideMenuColorWhenPressed() {
        String color = new DashboardPage(getDriver())
                .getDashboardSideMenyButtonColor();

        Assert.assertTrue(color.contains("rgb(34, 113, 177)"));
    }

    @Test
    public void testLogin() {
        Assert.assertTrue(new DashboardPage(getDriver()).getTitle().contains("Dashboard"));
    }

    @Test
    public void testHelpPanelIsOpened() {
        boolean actualResult = new DashboardPage(getDriver())
                .helpButtonClick()
                .tabOverviewIsDisplayed();

        Assert.assertTrue(actualResult);

    }

    @Test
    public void testScreenOptionsPanelIsOpened() {
        String actualTitle = new DashboardPage(getDriver())
                .screenOptionsButtonClick()
                .getScreenOptionsTitle();

        Assert.assertEquals(actualTitle, "Screen elements");
    }

    @Test
    public void testWelcomePanelHidingViaScreenOptions() {
        DashboardPage dashboard = new DashboardPage(getDriver());
        if (!dashboard.verifyWelcomePanelIsOpened()) {
            dashboard.screenOptionsButtonClick().clickCheckboxWelcomePanelHide();
        }
        dashboard.screenOptionsButtonClick()
                .clickCheckboxWelcomePanelHide();
        Assert.assertFalse(dashboard.verifyWelcomePanelIsOpened());
    }

    @Test
    public void testSiteHealthStatusPanelHidingViaScreenOptions() {
        DashboardPage dashboard = new DashboardPage(getDriver());
        if (!dashboard.checkSiteHealthStatusPanelIsVisible()) {
            dashboard.screenOptionsButtonClick().clickCheckboxSiteHealthStatus();
        }
        dashboard.screenOptionsButtonClick()
                .clickCheckboxSiteHealthStatus();
        Assert.assertFalse(dashboard.checkSiteHealthStatusPanelIsVisible());
    }

    @Test
    public void testAtAGlancePanelPanelHidingViaScreenOptions() {
        DashboardPage dashboard = new DashboardPage(getDriver());
        if (!dashboard.checkAtAGlancePanelPanelIsVisible()) {
            dashboard.screenOptionsButtonClick().clickCheckboxAtAGlance();
        }
        dashboard.screenOptionsButtonClick()
                .clickCheckboxAtAGlance();
        Assert.assertFalse(dashboard.checkAtAGlancePanelPanelIsVisible());
    }

    @Test
    public void testActivityPanelHidingViaScreenOptions() {
        DashboardPage dashboard = new DashboardPage(getDriver());
        if (!dashboard.checkActivityPanelIsVisible()) {
            dashboard.screenOptionsButtonClick().clickCheckboxActivity();
        }
        dashboard.screenOptionsButtonClick()
                .clickCheckboxActivity();
        Assert.assertFalse(dashboard.checkActivityPanelIsVisible());
    }

    @Test
    public void testQuickDraftPanelHidingViaScreenOptions() {
        DashboardPage dashboard = new DashboardPage(getDriver());
        if (!dashboard.checkQuickDraftPanelIsVisible()) {
            dashboard.screenOptionsButtonClick().clickCheckboxQuickDraft();
        }
        dashboard.screenOptionsButtonClick()
                .clickCheckboxQuickDraft();
        Assert.assertFalse(dashboard.checkQuickDraftPanelIsVisible());
    }

    @Test
    public void testClickMeetupButton() {
        String expectedUrl = "https://www.meetup.com/pro/wordpress/";

        String actualUrl = new DashboardPage(getDriver())
                .scrollAndClickMeetupsButton()
                .getOuterPageUrl();

        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
