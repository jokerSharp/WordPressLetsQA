package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

public class MainPageTest extends BaseTest {
    @Test
    public void testLogin() {
        System.out.println(new DashboardPage(getDriver()).getTitle());
        Assert.assertTrue(new DashboardPage(getDriver()).getTitle().contains("LetsQA"));
    }

    @Test
    public void testHelpPanelIsOpened(){
       boolean actualResult = new DashboardPage(getDriver())
                .helpButtonClick()
               .tabOverviewIsDisplayed();

        Assert.assertTrue(actualResult);

    }

}
