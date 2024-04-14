package ui;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.api.*;
import ui.model.api.pojo.CommentPOJO;
import ui.model.plugins.PluginsPage;
import ui.runner.ApiUtils;
import ui.runner.BaseTest;

import static ui.model.api.builders.CommentBuilder.getRandomComment;

public class ApiTest extends BaseTest {

    private final String SETTINGS_UPDATED = "Permalink structure updated.";
    private final String JWT = "WordPress REST API Authentication";
    private final String ACTIVATED = "Plugin activated.";
    private final String CONFIGURED = "JWT Authentication Method is configured successfully.";
    private UserResp userResp = new UserResp();
    private PostResponse postResponse = new PostResponse();
    private final String REASSIGN_PARAMETER = "reassign=1";
    private final String FORCE_PARAMETER = "force=true";
    private final String POST_TITLE = "New Post";
    private final String POST_STATUS = "publish";
    private final String POST_COMMENT_STATUS = "open";
    private final String POST_COMMENT_CONTENT = "test comment";

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
    public void testApiGetToken() {
        Auth auth = new Auth();
        ApiUtils.setTOKEN(auth);

        Assert.assertTrue(true);
    }

    @Test(priority = 2)
    public void testApiGetUsersList() {
        Response users = ApiUtils.getListUsers();

        Assert.assertEquals(users.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testApiPostNewUser() {
        UserReq userReq = new UserReq("user25", "Ivan", "Petrov",
                "12345gdh", "user25@gmail.com");
        userResp = ApiUtils.postNewUser(userReq);

        Assert.assertEquals(userResp.getEmail(), userReq.getEmail());
        Assert.assertEquals(userResp.getUsername(), userReq.getUsername());
    }

    @Test(priority = 3)
    public void testApiPostNewPost() {
        PostRequest postRequest = new PostRequest(POST_STATUS, POST_TITLE, userResp.getId(), POST_COMMENT_STATUS);

        postResponse = ApiUtils.postNewPost(postRequest);

        Assert.assertEquals(postResponse.getAuthorId(), userResp.getId());
        Assert.assertEquals(postResponse.getTitle(), POST_TITLE);
    }

    @Test(priority = 15)
    public void testApiDeleteNewUser() {
        String request = String.format("%s?%s&%s", userResp.getId(), FORCE_PARAMETER, REASSIGN_PARAMETER);

        Response resp = ApiUtils.deleteNewUser(request);

        Assert.assertTrue(resp.statusCode() == 200);
        Assert.assertTrue(resp.path("deleted"));
    }

    @Test(priority = 4)
    public void testApiPostComment() {
        CommentPOJO request = getRandomComment(postResponse.getPostId(), userResp.getId());

        CommentPOJO response = ApiUtils.postNewComment(request);

        Assert.assertEquals(response.getAuthor(), userResp.getId());
        Assert.assertEquals(response.getPost(), postResponse.getPostId());
        Assert.assertEquals(response.getContent(), request.getContent());
    }
}