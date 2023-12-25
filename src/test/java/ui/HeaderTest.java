package ui;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeaderTest extends BaseTest {

    @Test
    public void testLogOut() {
        String actualLoginMessageText = new DashboardPage(getDriver())
                .hoverOnUserProfileButton()
                .clickLogOutButton()
                .getLoginMessageText();

        Assert.assertEquals(actualLoginMessageText, "You are now logged out.");
    }

    @Test
    public void testWpLogoMenuItems() {
        final List<String> expectedLogoMenuItems = List.of(
                "About WordPress",
                "Get Involved",
                "WordPress.org",
                "Documentation",
                "Learn WordPress",
                "Support",
                "Feedback");

        List<String> actualLogoMenuItems = new DashboardPage(getDriver())
                .hoverOnWpLogo()
                .getWpLogoMenuItemsList();

        for (String item : actualLogoMenuItems) {
            System.out.println("********************");
            System.out.println(item);
        }

        Assert.assertEquals(actualLogoMenuItems, expectedLogoMenuItems);
    }
}