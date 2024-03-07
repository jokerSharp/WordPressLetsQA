package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.appearance.AppearancePage;
import ui.runner.BaseTest;

public class AppearanceTest extends BaseTest {

    final String ACTIVE_STATUS = "Active:";
    final String NEW_THEME_ACTIVATED = "New theme activated. Visit site";

    @Test
    public void testChangeAppearanceTheme() {

        String activeStatus = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuAppearanceButton()
                .getTwentyTwentyFourThemeActiveStatus();

        Assert.assertEquals(activeStatus, ACTIVE_STATUS);

        String actualNewThemeActivatedMessage = new AppearancePage(getDriver())
                .hoverOntwentyTwentyTwoTheme()
                .clickActivateTwentyTwentyTwoTheme()
                .getNewThemeActivatedMessage();

        String actualNewActiveStatus = new AppearancePage(getDriver())
                .getTwentyTwentyTwoThemeActiveStatus();

        Assert.assertEquals(actualNewThemeActivatedMessage, NEW_THEME_ACTIVATED);
        Assert.assertEquals(actualNewActiveStatus, ACTIVE_STATUS);
    }
}