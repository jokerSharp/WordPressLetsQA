package ui;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.api.UserReq;
import ui.model.api.UserResp;
import ui.runner.ApiUtils;
import ui.runner.BaseTest;

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
        Response resp = null;
        UserReq userReq = new UserReq("user25", "Ivan", "Petrov",
                "12345gdh", "user25@gmail.com");
        UserResp userResp = ApiUtils.postNewUser(userReq, url);

        Assert.assertEquals(userResp.getEmail(), userReq.getEmail());
        Assert.assertEquals(userResp.getUsername(), userReq.getUsername());
    }
}
