package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

public class MainPageTest extends BaseTest {
    @Test
    public void testLogin() {
        Assert.assertTrue(new DashboardPage(getDriver()).getTitle().contains("LetsQA"));
    }
}
