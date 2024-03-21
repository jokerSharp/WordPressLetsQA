package ui;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.api.Auth;
import ui.model.api.UserReq;
import ui.model.api.UserResp;
import ui.model.plugins.PluginsPage;
import ui.runner.ApiUtils;
import ui.runner.BaseTest;

public class ApiTest extends BaseTest {

    private final String SETTINGS_UPDATED = "Permalink structure updated.";
    private final String SWAGGER = "swagger";
    private final String JWT = "WordPress REST API Authentication";
    private final String ACTIVATED = "Plugin activated.";
    private final String CONFIGURED = "JWT Authentication Method is configured successfully.";
    private final String SWAGGER_API_DOCS_URL = "?page=swagger-ui";
    private UserResp userResp = null;
    private final String REASSIGN_PARAMETER = "reassign=1";
    private final String FORCE_PARAMETER = "force=true";

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
    public void testRestApiAuthenticationPlugin() {
        String actual = new DashboardPage(getDriver())
                .getSidePanel()
                .hoverSideMenuPluginsButton()
                .clickAddNewPluginButton()
                .enterTextToSearchPluginsTextArea(JWT)
                .clickInstallRestApiAuthenticationPlugin()
                .clickActivateRestApiAuthenticationPlugin()
                .getNoticeMessage();

        Assert.assertEquals(actual, ACTIVATED);

        actual = new PluginsPage(getDriver())
                .clickConfigureRestApiAuthentication()
                .clickJwtAuthentication()
                .clickNext()
                .clickFinish()
                .getNoticeMessage();

        Assert.assertEquals(actual, CONFIGURED);
    }

    @Test(priority = 1)
    public void getToken() {
        String url = getDriver().getCurrentUrl().substring(0,23);
        Auth auth = new Auth();
        ApiUtils.setTOKEN(auth, url);

        Assert.assertTrue(true);
    }

    @Test(priority = 2)
    public void testGetUsersList() {
        String url = getDriver().getCurrentUrl().substring(0,23);
        Response users = ApiUtils.getListUsers(url);

        Assert.assertEquals(users.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testPostNewUser() {
        String url = getDriver().getCurrentUrl().substring(0,23);
        Response resp = null;
        UserReq userReq = new UserReq("user25", "Ivan", "Petrov",
                "12345gdh", "user25@gmail.com");
        userResp = ApiUtils.postNewUser(userReq, url);

        Assert.assertEquals(userResp.getEmail(), userReq.getEmail());
        Assert.assertEquals(userResp.getUsername(), userReq.getUsername());
    }

    @Test(priority = 3)
    public void testDeleteNewUser() {

        String url = getDriver().getCurrentUrl().substring(0,23);
        String request = String.format("%s?%s&%s", userResp.getId(), FORCE_PARAMETER, REASSIGN_PARAMETER);

        Response resp = ApiUtils.deleteNewUser(request, url);

        Assert.assertTrue(resp.statusCode() == 200);
        Assert.assertTrue(resp.path("deleted"));
    }
}