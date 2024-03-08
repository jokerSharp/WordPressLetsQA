package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

public class SwaggerTest extends BaseTest {

    private final String SETTINGS_UPDATED = "Permalink structure updated.";
    private final String SWAGGER = "swagger";
    private final String ACTIVATED = "Plugin activated.";
    private final String SWAGGER_API_DOCS_URL = "?page=swagger-ui";

    @Test
    public void testPermalinksChange() {
        String actual = new DashboardPage(getDriver())
                .getSidePanel()
                .hoverSideMenuSettingsButton()
                .clickSideMenuSettingsPermalinksChoice()
                .setCustomStructureYear()
                .submit()
                .getTextSettingsUpdated();

        Assert.assertEquals(actual, SETTINGS_UPDATED);
    }

    @Test(dependsOnMethods = "testPermalinksChange")
    public void testAddSwaggerPlugin() {
        String actual = new DashboardPage(getDriver())
                .getSidePanel()
                .hoverSideMenuPluginsButton()
                .clickAddNewPluginButton()
                .enterTextToSearchPluginsTextArea(SWAGGER)
                .clickInstallWpApiSwaggerUiPlugin()
                .clickActivateWpApiSwaggerUiPlugin()
                .getNoticeMessage();

        Assert.assertEquals(actual, ACTIVATED);
    }

    @Test(dependsOnMethods = "testAddSwaggerPlugin")
    public void testSwaggerEnable() {
        String actual = new DashboardPage(getDriver())
                .getSidePanel()
                .hoverSideMenuSettingsButton()
                .clickSideMenuSettingsSwaggerButton()
                .clickApiDocsUrl()
                .getUrlSwaggerApidocs();

        Assert.assertEquals(actual, SWAGGER_API_DOCS_URL);
    }
}
