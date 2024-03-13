package ui;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.api.ApiPage;
import ui.runner.BaseTest;
import ui.model.api.ApiPage.*;
import ui.runner.LoggerUtils;
import ui.runner.ProjectUtils;

import static io.restassured.RestAssured.given;

public class ApiTest extends BaseTest {

    private final String SETTINGS_UPDATED = "Permalink structure updated.";
    private final String SWAGGER = "swagger";
    private final String ACTIVATED = "Plugin activated.";
    private final String SWAGGER_API_DOCS_URL = "?page=swagger-ui";
    private final String USERS_LIST = "/users?context=view&page=1&per_page=10&order=desc&orderby=id";

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
        String actual = new DashboardPage(getDriver())
                .getSidePanel()
                .hoverSideMenuSettingsButton()
                .clickSideMenuSettingsSwaggerButton()
                .clickApiDocsUrl()
                .getUrlSwaggerApidocs();

        Assert.assertEquals(actual, SWAGGER_API_DOCS_URL);
    }

    @Test(dependsOnMethods = "testPermalinksChange")
    public void getUsers() {
        String url = getDriver().getCurrentUrl().substring(0,23);
        final String apiUrl = url + "wp-json/wp/v2";

        given().auth().preemptive().basic("admin", "Asdf.1234.qpwoei")
                .when().get(apiUrl + USERS_LIST).then().statusCode(200);

        Response users = ApiPage.getUserList(apiUrl + USERS_LIST);
        LoggerUtils.logInfo(users.asPrettyString());

        Assert.assertEquals(users.getStatusCode(), 200);
    }
}
