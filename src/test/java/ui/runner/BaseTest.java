package ui.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testng.ITestResult;
import java.lang.reflect.Method;
import java.time.Duration;

import org.testng.annotations.*;
import ui.model.LoginPage;
import ui.model.installation.SelectLanguagePage;

public abstract class BaseTest {
    static private final String login = ProjectUtils.getAdminName();
    static private final String password = ProjectUtils.getAdminPassword();
    static private Network dockerNetwork;
    GenericContainer mysql;
    GenericContainer wordpress;
    private WebDriver driver;

    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    private void startDriver() {
        LoggerUtils.logInfo("Browser open");
        driver = ProjectUtils.createDriver();
    }

    private void acceptAlert() {
        ProjectUtils.acceptAlert(getDriver());
    }

    private void stopDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait2 = null;
            wait5 = null;
            wait10 = null;
            LoggerUtils.logInfo("Browser closed");
        }
    }

    @BeforeSuite
    protected void beforeSuite() {
        dockerNetwork = Network.newNetwork();
        mysql = new GenericContainer("mysql:5.7");
        mysql.addEnv("MYSQL_ROOT_PASSWORD", "admin");
        mysql.addEnv("MYSQL_DATABASE", "wp");
        mysql.addEnv("MYSQL_USER", "wp");
        mysql.addEnv("MYSQL_PASSWORD", "wp");
        mysql.setNetwork(dockerNetwork);
        mysql = mysql.withNetworkAliases("mysql");
        mysql.start();
    }

    @BeforeClass
    protected void beforeClass() {
        wordpress = new GenericContainer("wordpress:6.4.2-php8.2-apache");
        wordpress.addEnv("WORDPRESS_DB_HOST", "mysql:3306");
        wordpress.addEnv("WORDPRESS_DB_USER", "wp");
        wordpress.addEnv("WORDPRESS_DB_PASSWORD", "wp");
        wordpress.addEnv("WORDPRESS_DB_NAME", "wp");
        wordpress.addEnv("WORDPRESS_TABLE_PREFIX", this.getClass().getName().replace('.', '_') + "_");
        wordpress.addExposedPorts(80);
        wordpress.setNetwork(dockerNetwork);
        wordpress.start();
        WebDriver driver = ProjectUtils.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        for(int i = 0; i < 30; ++i) {
            driver.get("http://localhost:" + wordpress.getFirstMappedPort());
            try {
                driver.findElement(By.id("logo"));
            } catch(NoSuchElementException e) {
                continue;
            }
            LoggerUtils.logInfo("Found logo");
            break;
        }
        new SelectLanguagePage(driver)
                .selectEnglishLanguage()
                .clickContinue()
                .inputWeblogTitle("Let's QA")
                .inputUsername(login)
                .clearPassword()
                .inputPassword(password)
                .inputAdminEmail("admin@gmail.com")
                .submit()
                .proceedToLogin();
        driver.quit();
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        LoggerUtils.logSuccess(String.format("Run %s.%s", this.getClass().getName(), method.getName()));
        startDriver();
        try {
            String baseUrl = "http://localhost:" + wordpress.getFirstMappedPort() + "/";
            LoginPage
                    .open(getDriver(), baseUrl)
                    .login(login, password);
        } catch (Exception e) {
            stopDriver();
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            LoggerUtils.logError(String.format("ERROR: %s.%s", this.getClass().getName(), method.getName()));
            ProjectUtils.takeScreenshot(driver, method.getName(), this.getClass().getName());
        }
        LoggerUtils.logInfo(String.format("Execution time is %o sec\n\n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000));
        stopDriver();
    }

    @AfterClass
    protected void afterClass() {
        wordpress.stop();
    }

    @AfterSuite
    protected void afterSuite() {
        mysql.stop();
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
