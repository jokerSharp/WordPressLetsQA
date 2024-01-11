package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

public class DashboardTest extends BaseTest{

    @Test
    public void testDashboardSideMenuColorWhenPressed() {
        String color = new DashboardPage(getDriver())
                .getDashboardSideMenyButtonColor();

        Assert.assertTrue(color.contains("rgb(34, 113, 177)"));
    }
}
