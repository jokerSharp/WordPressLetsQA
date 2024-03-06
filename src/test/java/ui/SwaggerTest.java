package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.base.SidePanel;
import ui.runner.BaseTest;
import ui.runner.LoggerUtils;

public class SwaggerTest extends BaseTest {

    private final String settingsUpdatedText = "Permalink structure updated.";

    private final String SWAGGER = "swagger";
    private final String ACTIVATED = "Plugin activated.";

    @Test
    public void testSwaggerEnable() {
        String actual = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuSettingsButton()
                .clickSideMenuSettingsPermalinksChoice()
                .setCustomStructureYear()
                .submit()
                .getTextSettingsUpdated();

        Assert.assertEquals(actual, settingsUpdatedText);

        LoggerUtils.logInfo("First is OK");

        actual = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuPluginsButton()
                .clickAddNewPluginButton()
                .enterTextToSearchPluginsTextArea(SWAGGER)
                .clickInstallWpApiSwaggerUiPlugin()
                .clickActivateWpApiSwaggerUiPlugin()
                .getNoticeMessage();

        LoggerUtils.logInfo("actual = " + actual);
        LoggerUtils.logInfo("expect = " + ACTIVATED);

        Assert.assertEquals(actual, ACTIVATED, "Dismiss this notice.");
    }
}
