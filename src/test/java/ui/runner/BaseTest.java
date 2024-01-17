package ui.runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import java.lang.reflect.Method;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    private WebDriver driver;

    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    private void startDriver() {
        ProjectUtils.log("Browser open");

        driver = ProjectUtils.createDriver();
    }

    private void clearData() {
        ProjectUtils.log("Clear data");
        WordPressUtils.clearData();
    }

    private void loginWeb() {
        ProjectUtils.log("Login");
        WordPressUtils.login(driver);
    }

    private void getWeb() {
        ProjectUtils.log("Get web page");
        ProjectUtils.get(driver);
    }

    private void acceptAlert() {
        ProjectUtils.acceptAlert(getDriver());
    }

    private void stopDriver() {
        try {
            WordPressUtils.logout(driver);
        } catch (Exception ignore) {
        }
        closeDriver();
    }

    private void closeDriver() {
        if (driver != null) {
            driver.quit();

            driver = null;
            wait2 = null;
            wait5 = null;
            wait10 = null;

            ProjectUtils.log("Browser closed");
        }
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        ProjectUtils.logf("Run %s.%s", this.getClass().getName(), method.getName());
        try {
            clearData();
            startDriver();
            getWeb();
            loginWeb();
        } catch (Exception e) {
            closeDriver();
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        if (ProjectUtils.isServerRun() && !testResult.isSuccess()) {
            ProjectUtils.takeScreenshot(driver, method.getName(), this.getClass().getName());
        }
        stopDriver();
        ProjectUtils.logf("Execution time is %o sec\n\n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }

        return wait2;
    }

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }

        return wait5;
    }

    protected WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }

        return wait10;
    }
}
