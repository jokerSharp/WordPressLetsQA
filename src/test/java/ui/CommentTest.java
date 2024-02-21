package ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.comments.CommentsPage;
import ui.model.nonAdminPages.NAPostPage;
import ui.runner.BaseTest;

import java.util.List;

public class CommentTest extends BaseTest {

    private final String COMMENT = RandomStringUtils.randomAlphabetic(20);

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

        List<String> commentsTextList = new NAPostPage(getDriver())
                .goToDashboardPage()
                .getSidePanel()
                .clickSideMenuCommentsButton()
                .getCommentsTextList();

        Assert.assertTrue(commentsTextList.contains(COMMENT));
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
