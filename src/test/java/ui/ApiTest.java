package ui;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.ApiUtils;
import ui.runner.BaseTest;
import ui.runner.LoggerUtils;

public class ApiTest extends BaseTest {

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

    @Test(dependsOnMethods = {"testAddSwaggerPlugin", "testPermalinksChange"})
    public void testSwaggerEnable() {
        String basePath = new DashboardPage(getDriver())
                .getSidePanel()
                .hoverSideMenuSettingsButton()
                .clickSideMenuSettingsSwaggerButton()
                .getBasePath();

        Assert.assertEquals(basePath, "wp/v2");
    }

    @Test(dependsOnMethods = "testPermalinksChange")
    public void testGetUsersList() {
        String url = getDriver().getCurrentUrl().substring(0,23);
        Response users = ApiUtils.getListUsers(url);

        Assert.assertEquals(users.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = {"testAddSwaggerPlugin", "testPermalinksChange"})
    public void testPostNewUser() {
        String url = getDriver().getCurrentUrl().substring(0,23);

        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "user25");
        requestBody.put("first_name", "Ivan");
        requestBody.put("last_name", "Petrov");
        requestBody.put("password", "12345gdh");
        requestBody.put("email", "user25@gmail.com");

        boolean resp = ApiUtils.postNewUser(requestBody.toString(), url);

        Assert.assertTrue(resp);
    }
}
