package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.model.DashboardPage;
import ui.model.users.UserProfilePage;
import ui.runner.BaseTest;

import java.util.List;

public class UserTest extends BaseTest {

    private static final String USERNAME = "User";
    private static final String EMAIL = "user@gmail.com";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String WEBSITE = "google.com";

    @Test
    public void testNewUserCreation() {

        List<String> actualUsernames = new DashboardPage(getDriver())
                .getHeader()
                .hoverOnNewButton()
                .clickNewUserButton()
                .typeUsername(USERNAME)
                .typeEmail(EMAIL)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .typeWebsite(WEBSITE)
                .clickAddNewUserButton()
                .getUsernames();

        Assert.assertListContainsObject(actualUsernames, USERNAME, "No user found");
    }

    @Test(dependsOnMethods = "testNewUserCreation")
    public void testDeleteCreatedUser() {

        int initialSizeOfUserList = new DashboardPage(getDriver())
                .getSidePanel()
                .goToUserPage()
                .getUsersAmount();

        int actualSizeofUserListAfterDeletion = new DashboardPage(getDriver())
                .getSidePanel()
                .goToUserPage()
                .hoverOnUser(USERNAME)
                .clickDeleteUserButton()
                .clickConfirmDeletionButton()
                .getUsersAmount();

        Assert.assertEquals(actualSizeofUserListAfterDeletion, initialSizeOfUserList-1);
    }

    @Test
    public void testVerifyColorOfBackgroundLightScheme() {

        String expectedBackgroundColorWithLightScheme = new DashboardPage(getDriver())
                .getSidePanel()
                .goToUserPage()
                .goToUserProfilePage()
                .selectColorLightScheme()
                .getFirstColorOfLightScheme();

        String actualColorOfBackground = new UserProfilePage(getDriver())
                .getBackgroundColor();

        Assert.assertEquals(actualColorOfBackground, expectedBackgroundColorWithLightScheme);

        String expectedBackgroundColorWithBDefaultScheme = new UserProfilePage(getDriver())
                .selectColorDefaultScheme()
                .clickUpdateProfileButton()
                .getFirstColorOfDefaultScheme();

        actualColorOfBackground = new UserProfilePage(getDriver())
                .getBackgroundColor();

        Assert.assertEquals(actualColorOfBackground, expectedBackgroundColorWithBDefaultScheme);
    }
}