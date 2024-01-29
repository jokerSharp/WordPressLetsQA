package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.posts.AllPostsPage;
import ui.runner.BaseTest;

public class PostTest extends BaseTest {

    private final String POST_TITLE = "qwerty";

    @Test
    public void testCreateNewPost() {
        new DashboardPage(getDriver())
                .getHeader()
                .hoverOnNewContentButton()
                .clickNewPostButton()
                .typeTitle(POST_TITLE)
                .clickPreliminaryPublishButton()
                .clickFinalPublishButton()
                .clickViewPost();

        Assert.assertTrue(getDriver().getTitle().contains(POST_TITLE));
    }

    @Test(dependsOnMethods = "testCreateNewPost")
    public void testDeleteNewPost() {

        int expectedPostsAmount = new DashboardPage(getDriver())
                .sidePanel()
                .clickSideMenuPostsButton()
                .getPostsListSize();

        int actualPostsAmount = new AllPostsPage(getDriver())
                .hoverOnPostTitle(POST_TITLE)
                .clickTrashButton(POST_TITLE)
                .getPostsListSize();

        Assert.assertEquals(actualPostsAmount, expectedPostsAmount-1);
    }
}
