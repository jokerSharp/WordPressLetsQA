package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

public class HeaderTest extends BaseTest {

    @Test
    public void testLogOut() {
        String actualLoginMessageText = new DashboardPage(getDriver())
                .hoverOnUserProfileButton()
                .clickLogOutButton()
                .getLoginMessageText();

        Assert.assertEquals(actualLoginMessageText, "You are now logged out.");
    }
}
