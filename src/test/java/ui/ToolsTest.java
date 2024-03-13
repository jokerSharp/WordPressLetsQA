package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

public class ToolsTest extends BaseTest {

    final String EXPECTED_IMPORT_CONFIRMATION = "Importer installed successfully.";

    @Test
    public void testAddLiveJournalTool() {

        String actualImportConfirmation = new DashboardPage(getDriver())
                .getSidePanel()
                .hoverOnSideMenuToolsButton()
                .clickSubMenuToolsImportButton()
                .clickliveJournalInstallNowButton()
                .getImportConfirmationText();

        Assert.assertEquals(actualImportConfirmation, EXPECTED_IMPORT_CONFIRMATION);
    }
}
