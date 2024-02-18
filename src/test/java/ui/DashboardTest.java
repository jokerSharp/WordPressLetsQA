package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.OuterPage;
import ui.runner.BaseTest;

import java.util.List;

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
                .clickHelpButton()
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
    public void testOuterLinksWork() {
        String expectedUrlMeetupsPage = "https://www.meetup.com/pro/wordpress/";

        List<String> expectedUrlsOfOuterPages = new DashboardPage(getDriver())
                .outerPagesLinksList();

        String actualUrlMeetupsLink = new DashboardPage(getDriver())
                .scrollAndClickMeetupsButton()
                .goToOuterPageTab()
                .getCurrentUrl();

        Assert.assertEquals(actualUrlMeetupsLink, expectedUrlMeetupsPage);

        String actualUrlWordCampLink = new OuterPage(getDriver())
                .closeOuterPageTabAndReturnToDashboardPage()
                .scrollAndClickWordCampLink()
                .goToOuterPageTab()
                .getCurrentUrl();

        Assert.assertTrue(expectedUrlsOfOuterPages.contains(actualUrlWordCampLink));

        String actualUrlNewsLink = new OuterPage(getDriver())
                .closeOuterPageTabAndReturnToDashboardPage()
                .scrollAndClickNewsLink()
                .goToOuterPageTab()
                .getCurrentUrl();

        Assert.assertTrue(expectedUrlsOfOuterPages.contains(actualUrlNewsLink));
    }

    @Test
    public void testOuterLinksInHelpPanelWork() {
        List<String> expectedUrlsOfOuterPages = new DashboardPage(getDriver())
                .helpPanelOuterPagesLinksList();

        String actualUrlDocumentationOnDashboardLink = new DashboardPage(getDriver())
                .clickHelpButton()
                .clickDocumentationOnDashboardLink()
                .getCurrentUrl();

        Assert.assertTrue(expectedUrlsOfOuterPages.contains(actualUrlDocumentationOnDashboardLink));

        String actualUrlSupportForumsLink = new OuterPage(getDriver())
                .returnToPreviousPage(new DashboardPage(getDriver()))
                .clickHelpButton()
                .clickSupportForumsLink()
                .getCurrentUrl();

        Assert.assertTrue(expectedUrlsOfOuterPages.contains(actualUrlSupportForumsLink));

        String actualVersionLink = new OuterPage(getDriver())
                .returnToPreviousPage(new DashboardPage(getDriver()))
                .clickHelpButton()
                .clickVersionLink()
                .getCurrentUrl();

        Assert.assertTrue(expectedUrlsOfOuterPages.contains(actualVersionLink));
    }
}
