package ui.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AdminTable {
    final private WebDriver driver;
    final private WebElement root;
    @FindBy(css = "thead th")
    private List<WebElement> header;
    private List<AdminTableRow> rows = new ArrayList<>();

    public AdminTable(WebDriver driver, WebElement root) {
        this.driver = driver;
        this.root = root;
        PageFactory.initElements(driver, this);
        for (WebElement rowElement : root.findElements(new By.ByCssSelector("tbody tr"))) {
            rows.add(new AdminTableRow(driver, rowElement));
        }
    }

    public List<String> getColumn(String column) {
        int columnIndex = getColumnIndex(column);
        return rows.stream().map((row) -> row.getField(columnIndex)).toList();
    }

    public AdminTableRow getRow(String column, String value) {
        int columnIndex = getColumnIndex(column);

        for(AdminTableRow row : rows) {
            if (row.getField(columnIndex).equals(value))
                return row;
        }
        throw new RuntimeException("no such row in the table");
    }

    private int getColumnIndex(String column) {
        int columnIndex;
        for (columnIndex = 0; columnIndex < header.size(); columnIndex++) {
            if (header.get(columnIndex).getText().equals(column)) {
                return columnIndex;
            }
        }
        throw new RuntimeException("no such column");
    }
}
