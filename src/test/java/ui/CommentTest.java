package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

import java.util.List;

public class CommentTest extends BaseTest {

    @Test
    public void testCreateComment() {
        String comment = "new comment to first post";

        List<String> commentsList = new DashboardPage(getDriver())
                .getHeader()
                .goToNAHomePage()
                .clickFirstPostLink()
                .typeNewComment(comment)
                .clickPostCommentButton()
                .getCommentsText();

        Assert.assertTrue(commentsList.contains(comment));
    }
}