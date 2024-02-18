package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

import java.util.List;

public class TagsTest extends BaseTest {
    public static final String TAG_NAME = "tagName";

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
}
