package ui.model.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.model.base.AdminTable;
import ui.model.base.BasePage;

import java.util.List;

public class AllPagesPage extends BasePage {

    @FindBy(css = "table.pages")
    private WebElement adminTableElement;

    private AdminTable adminTable;

    public AllPagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        adminTable = new AdminTable(driver, adminTableElement);
    }

    public List<String> getPages() {
        return adminTable.getColumn("Title");
    }

    public void trash(String pageName) {
        adminTable.getRow("Title", pageName).doAction("Trash");
    }
}
