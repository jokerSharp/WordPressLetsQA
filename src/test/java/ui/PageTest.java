package ui;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.nonAdminPages.NAMainPage;
import ui.runner.BaseTest;
import ui.runner.ProjectUtils;

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
}
