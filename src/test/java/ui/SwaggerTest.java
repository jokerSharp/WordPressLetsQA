package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

public class SwaggerTest extends BaseTest {

    private final String settingsUpdatedText = "Permalink structure updated.";

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
    }
}
