package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.comments.CommentsPage;
import ui.runner.BaseTest;

import java.util.List;

public class CommentTest extends BaseTest {

    private final String COMMENT = "new comment to first post";

    @Test
    public void testCreateComment() {

        List<String> commentsList = new DashboardPage(getDriver())
                .getHeader()
                .goToNAHomePage()
                .clickFirstPostLink()
                .typeNewComment(COMMENT)
                .clickPostCommentButton()
                .getCommentsText();

        Assert.assertTrue(commentsList.contains(COMMENT));
    }

    @Test(dependsOnMethods = "testCreateComment")
    public void testDeleteComment() {

        int expectedCommentsListSize =
                new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuCommentsButton()
                .getCommentsListSize();

        int actualCommentsListSizeAfterDeletion = new CommentsPage(getDriver())
                .clickSelectAllCheckbox()
                .clickWordPressCommenterCheckbox()
                .clickBulkActionSelector()
                .ClickMoveToTrashOption()
                .clickApplyButton()
                .getCommentsListSize();

        Assert.assertEquals(actualCommentsListSizeAfterDeletion, expectedCommentsListSize -1);
    }
}
