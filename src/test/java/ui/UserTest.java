package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.runner.BaseTest;

public class UserTest extends BaseTest {

    private static final String USERNAME = "User";
    private static final String EMAIL = "user@gmail.com";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String WEBSITE = "google.com";

    @Test
    public void testNewUserCreation () {

        String actualUsername = new DashboardPage(getDriver())
                .hoverOnNewButton()
                .clickNewUserButton()
                .typeUsername(USERNAME)
                .typeEmail(EMAIL)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .typeWebsite(WEBSITE)
                .clickAddNewUserButton()
                .getUsernameValue();

        Assert.assertEquals(actualUsername, USERNAME);
    }
}