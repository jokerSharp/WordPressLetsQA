package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

import java.util.List;

public class CategoryTest extends BaseTest {
    public static final String CATEGORY_NAME = "categoryName";

    @Test
    public void testCreateCategoryRequiredFields() {
        List<String> categories = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuPostsButton()
                .getSidePanel()
                .clickSideMenuCategoriesButton()
                .inputCategoryName(CATEGORY_NAME)
                .clickAddNewCategory()
                .getCategoriesList();

        Assert.assertTrue(categories.contains(CATEGORY_NAME));
    }
}
