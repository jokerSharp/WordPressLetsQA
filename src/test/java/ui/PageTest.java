package ui;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.nonAdminPages.NAMainPage;
import ui.model.pages.AllPagesPage;
import ui.runner.BaseTest;
import ui.runner.ProjectUtils;

import java.util.List;

public class PageTest extends BaseTest {

    @Test
    public void testCreateNewPage() {
        String pageTitle = "IDDQD";
        new DashboardPage(getDriver())
                .getSidePanel()
                .hoverOnSideMenuPageButton()
                .clickAddNewPageButton()
                .clickPattern()
                .typeTitle(pageTitle)
                .clickPublishButton()
                .clickFinalPublishButton();

        WebDriver newDriver = ProjectUtils.createDriver();
        String actualTitle = NAMainPage
                .open(newDriver, baseUrl)
                .clickPage(pageTitle)
                .postTitleGetText();

        newDriver.quit();

        Assert.assertEquals(actualTitle, pageTitle);
    }

    @Test
    public void testMovePageToTrash() {
        String pageTitle = "IDKFA";
        AllPagesPage allPagesPage = new DashboardPage(getDriver())
                .getSidePanel()
                .hoverOnSideMenuPageButton()
                .clickAddNewPageButton()
                .clickPattern()
                .typeTitle(pageTitle)
                .clickPublishButton()
                .clickFinalPublishButton()
                .clickViewPagesButton();
        List<String> pageListBeforeDeletion = allPagesPage.getPages();
        Assert.assertListContainsObject(pageListBeforeDeletion, pageTitle, "newly created page");

        allPagesPage.trash(pageTitle);
        List<String> pageListAfterDeletion = new AllPagesPage(getDriver()).getPages();
        Assert.assertListNotContainsObject(pageListAfterDeletion, pageTitle, "delete page");
    }
}
