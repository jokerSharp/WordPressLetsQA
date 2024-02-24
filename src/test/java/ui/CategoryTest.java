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
                .inputElementName(CATEGORY_NAME)
                .clickAddNewElement()
                .getElementsList();

        Assert.assertTrue(categories.contains(CATEGORY_NAME));
    }

    @Test
    public void testDeleteCategory() {
        testCreateCategoryRequiredFields();

        List<String> categories = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuPostsButton()
                .getSidePanel()
                .clickSideMenuCategoriesButton()
                .clickDeleteElement(CATEGORY_NAME)
                .getElementsList();

        Assert.assertFalse(categories.contains(CATEGORY_NAME));
    }

    @Test
    public void testSearchCategory() {
        testCreateCategoryRequiredFields();

        String itemCounter = new DashboardPage(getDriver())
                .getSidePanel()
                .clickSideMenuPostsButton()
                .getSidePanel()
                .clickSideMenuCategoriesButton()
                .inputElementName(CATEGORY_NAME)
                .inputSearchValue(CATEGORY_NAME)
                .clickSearchButton()
                .getItemCounter();

        Assert.assertEquals(itemCounter, "1 item");
    }
}
