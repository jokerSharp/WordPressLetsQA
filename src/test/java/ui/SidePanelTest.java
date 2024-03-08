package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

public class SidePanelTest extends BaseTest {

    final String EXPECTED_COLLAPSED_SIDE_PANEL_WIDTH = "36px";

    final String EXPECTED_NORMAL_SIDE_PANEL_WIDTH = "160px";

    @Test
    public void testDashboardSideMenuColorWhenPressed() {
        String color = new DashboardPage(getDriver())
                .getDashboardSideMenyButtonColor();

        Assert.assertTrue(color.contains("rgb(34, 113, 177)"));
    }

    @Test
    public void testSidePanelCollapsedUncollapsed() {
        String actualSidePanelWidth = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuCollapseMenuButton()
                .getTheWholeSidePanelWidth();

        Assert.assertEquals(actualSidePanelWidth, EXPECTED_COLLAPSED_SIDE_PANEL_WIDTH);

        actualSidePanelWidth = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuCollapseMenuButton()
                .getTheWholeSidePanelWidth();

        Assert.assertEquals(actualSidePanelWidth, EXPECTED_NORMAL_SIDE_PANEL_WIDTH);
    }
}
