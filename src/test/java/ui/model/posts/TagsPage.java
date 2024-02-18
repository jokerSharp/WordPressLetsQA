package ui.model.posts;

import org.openqa.selenium.WebDriver;
import ui.model.base.CategoriesTagsBasePage;

public class TagsPage extends CategoriesTagsBasePage<TagsPage> {

    @Override
    protected TagsPage createPage() {
        return new TagsPage(getDriver());
    }

    public TagsPage(WebDriver driver) {
        super(driver);
    }
}
