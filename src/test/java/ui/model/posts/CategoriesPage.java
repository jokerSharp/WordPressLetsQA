package ui.model.posts;

import org.openqa.selenium.WebDriver;
import ui.model.base.CategoriesTagsBasePage;

public class CategoriesPage extends CategoriesTagsBasePage<CategoriesPage> {

    @Override
    protected CategoriesPage createPage() {
        return new CategoriesPage(getDriver());
    }

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }
}
