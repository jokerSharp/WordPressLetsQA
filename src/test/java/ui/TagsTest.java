package ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.posts.CreateEditPostPage;
import ui.runner.BaseTest;

import java.util.List;

public class TagsTest extends BaseTest {
    public static final String TAG_NAME = RandomStringUtils.randomAlphanumeric(3,10);

    @Test
    public void testCreateTagRequiredFields() {
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

    @Test (dependsOnMethods = {"testCreateTagRequiredFields"})
    public void testAssignTagToPost() {
        String messageText = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuPostsButton()
                .clickAnyPostTitle()
                .scrollToTagsSection()
                .closeWelcomeWindowIfAppears()
                .inputTagsName(TAG_NAME)
                .clickTagNameFromDropdown()
                .clickFinalPublishOrUpdateButton()
                .getSuccessMessageText();

        Assert.assertTrue(messageText.contains("Post updated."));

        List<String> actualTagsList = new CreateEditPostPage(getDriver())
                .clickWpLogo()
                .getTagsList();

        Assert.assertTrue(actualTagsList.contains(TAG_NAME));
    }
}
