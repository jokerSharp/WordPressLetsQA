package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.posts.AllPostsPage;
import ui.model.posts.TrashPostsPage;
import ui.runner.BaseTest;

public class PostTest extends BaseTest {

    private final String POST_TITLE = "qwerty";

    @Test(invocationCount = 5)
    public void testCreateNewPost() {
        String title = new DashboardPage(getDriver())
                .getHeader()
                .hoverOnNewContentButton()
                .clickNewPostButton()
                .typeTitle(POST_TITLE)
                .clickPreliminaryPublishButton()
                .clickFinalPublishButton()
                .clickViewPost()
                .getTitle();

        Assert.assertTrue(title.contains(POST_TITLE));
    }

    @Test(dependsOnMethods = "testCreateNewPost")
    public void testDeleteNewPost() {

        int expectedPostsAmount = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuPostsButton()
                .getPostsListSize();

        int actualPostsAmount = new AllPostsPage(getDriver())
                .hoverOnPostTitle(POST_TITLE)
                .clickTrashButtonUnderPostTitle(POST_TITLE)
                .getPostsListSize();

        Assert.assertEquals(actualPostsAmount, expectedPostsAmount-1);
    }

    @Test
    public void testEmptyTrash() {
        int amountOfallSelectedPostsOnTrashPage = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuPostsButton()
                .clickSelectAllPostsCheckbox()
                .clickHelloWorldPostCheckbox()
                .clickBulkActionSelector()
                .clickMoveToTrashOption()
                .clickApplyButton()
                .clickTrashLink()
                .getPostsListSize();

        Assert.assertTrue(amountOfallSelectedPostsOnTrashPage > 0);

        boolean permanentlyDeletedMessage = new TrashPostsPage(getDriver())
                .clickEmptyTrashButton()
                .permanentlyDeletedMessageIsVisible();

        Assert.assertTrue(permanentlyDeletedMessage);

        amountOfallSelectedPostsOnTrashPage = new TrashPostsPage(getDriver())
                .getPostsListSize();

        Assert.assertTrue(amountOfallSelectedPostsOnTrashPage == 0);
    }
}
