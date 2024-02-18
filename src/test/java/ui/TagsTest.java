package ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

import java.util.List;

public class TagsTest extends BaseTest {
    public static final String TAG_NAME = RandomStringUtils.randomAlphanumeric(3,10);

    @Test
    public void testCreateCategoryRequiredFields() {
        List<String> tags = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuPostsButton()
                .getSidePanel()
                .clickSideMenuTagsButton()
                .inputElementName(TAG_NAME)
                .clickAddNewElement()
                .getElementsList();

        Assert.assertTrue(tags.contains(TAG_NAME));
    }

    @Test (dependsOnMethods = {"testCreateCategoryRequiredFields", "testCreateNewPost"})
    public void testAssignTagToPost() {
        String messageText = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuPostsButton()
                .clickAnyPostTitle()
                .scrollToTagsSection()
                .inputTagAndPushEnter(TAG_NAME)
                .clickFinalPublishOrUpdateButton()
                .getSuccessMessageText();

        Assert.assertEquals(messageText, "Post updated.");
    }

    @Test
    public void testCreateNewPost() {
        String title = new DashboardPage(getDriver())
                .getHeader()
                .hoverOnNewContentButton()
                .clickNewPostButton()
                .typeTitle("new post")
                .clickPreliminaryPublishButton()
                .clickFinalPublishOrUpdateButton()
                .clickViewPost()
                .getTitle();

        Assert.assertTrue(title.contains("new post"));
    }
}
