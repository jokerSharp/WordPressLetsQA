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

        Assert.assertEquals(actualLogoMenuItems, expectedLogoMenuItems);
    }

    @Test
    public void testWpLogoMenuItems2() {
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
                .getWpLogoMenuItemsList2();

        Assert.assertEquals(actualLogoMenuItems, expectedLogoMenuItems);
    }

    @Test
    public void testWpLogoMenuItemsChangingColorsAfterHovering() {
        String expectedColor = "rgba(114, 174, 230, 1)";

        String actualString = new DashboardPage(getDriver())
                .hoverOnWpLogo()
                .hoverOnFeedbackLogoMenuItem()
                .getColorOfFeedbackLogoMenuItem();

        Assert.assertEquals(actualString, expectedColor);
    }

    @Test
    public void testWpLogoColorWhenHover() {
        String actualColor = new DashboardPage(getDriver())
                .hoverOnWpLogo()
                .getWpLogoIconColor();

        Assert.assertEquals(actualColor, "rgba(114, 174, 230, 1)");
    }
}
