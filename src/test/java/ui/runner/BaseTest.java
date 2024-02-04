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

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    GenericContainer mysql;
    GenericContainer wordpress;
    private WebDriver driver;

    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    private void startDriver() {
        ProjectUtils.log("Browser open");

        driver = ProjectUtils.createDriver();
    }

    private void loginWeb() {
        ProjectUtils.log("Login");
        WordPressUtils.login(driver, wordpress.getFirstMappedPort());
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
            ProjectUtils.log("Browser closed");
        }
    }

    @BeforeClass
    protected void beforeClass() throws InterruptedException {
        Network network = Network.newNetwork();
        mysql = new GenericContainer("mysql:5.7");
        mysql.addEnv("MYSQL_ROOT_PASSWORD", "MyR00tMySQLPa$$5w0rD");
        mysql.addEnv("MYSQL_DATABASE", "MyWordPressDatabaseName");
        mysql.addEnv("MYSQL_USER", "MyWordPressUser");
        mysql.addEnv("MYSQL_PASSWORD", "Pa$$5w0rD");
        mysql.setNetwork(network);
        mysql = mysql.withNetworkAliases("mysql");
        mysql.start();
        wordpress = new GenericContainer("wordpress:latest");
        wordpress.addEnv("WORDPRESS_DB_HOST", "mysql:3306");
        wordpress.addEnv("WORDPRESS_DB_USER", "MyWordPressUser");
        wordpress.addEnv("WORDPRESS_DB_PASSWORD", "Pa$$5w0rD");
        wordpress.addEnv("WORDPRESS_DB_NAME", "MyWordPressDatabaseName");
        wordpress.addExposedPorts(80);
        wordpress.setNetwork(network);
        wordpress.start();
        WebDriver driver = ProjectUtils.createDriver();
        for(int i = 0; i < 30; ++i) {
            driver.get("http://localhost:" + wordpress.getFirstMappedPort());
            try {
                driver.findElement(By.id("logo"));
            } catch(NoSuchElementException e) {
                Thread.sleep(1000);
                continue;
            }
            System.out.println("Found logo");
            break;
        }
        driver.findElement(By.xpath("//option[@lang='en']")).click();
        driver.findElement(By.id("language-continue")).click();
        driver.findElement(By.id("weblog_title")).sendKeys("Let's QA");
        driver.findElement(By.id("user_login")).sendKeys("admin");
        driver.findElement(By.id("pass1")).clear();
        driver.findElement(By.id("pass1")).sendKeys("admin");
        driver.findElement(By.className("pw-checkbox")).click();
        driver.findElement(By.id("admin_email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("submit")).click();
        driver.quit();
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        ProjectUtils.logf("Run %s.%s", this.getClass().getName(), method.getName());
        startDriver();
        try {
            loginWeb();
        } catch (Exception e) {
            stopDriver();
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        stopDriver();
        if (!testResult.isSuccess()) {
            ProjectUtils.takeScreenshot(driver, method.getName(), this.getClass().getName());
        }
        ProjectUtils.logf("Execution time is %o sec\n\n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000);
    }

    @AfterClass
    protected void afterClass() {
        mysql.stop();
        wordpress.stop();
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
