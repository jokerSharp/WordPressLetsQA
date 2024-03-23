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
import java.util.Arrays;
import java.util.stream.Collectors;

import org.testng.annotations.*;
import ui.ApiTest;
import ui.model.LoginPage;
import ui.model.installation.SelectLanguagePage;

public abstract class BaseTest {
    static private final String login = ProjectUtils.getAdminName();
    static private final String password = ProjectUtils.getAdminPassword();
    static private Network dockerNetwork;
    GenericContainer mysql;
    GenericContainer wordpress;
    private WebDriver driver;

    protected String baseUrl;

    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    private OrderUtils.MethodsOrder<Method> methodsOrder;

    private void startDriver() {
        LoggerUtils.logInfo("Browser open");
        driver = ProjectUtils.createDriver();
    }

    private void getWeb() {
        LoggerUtils.logInfo("Get web page");
        ProjectUtils.get(driver, baseUrl + "wp-admin/");
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

    private String getBaseUrl() {
        return baseUrl;
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
        methodsOrder = OrderUtils.createMethodsOrder(
                Arrays.stream(this.getClass().getMethods())
                        .filter(m -> m.getAnnotation(Test.class) != null && m.getAnnotation(Ignore.class) == null)
                        .collect(Collectors.toList()),
                Method::getName,
                m -> m.getAnnotation(Test.class).dependsOnMethods());

        wordpress = new GenericContainer("wordpress:6.4.3-php8.1-apache");
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
        baseUrl = "http://localhost:" + wordpress.getFirstMappedPort() + "/";
        for(int i = 0; i < 30; ++i) {
            driver.get(baseUrl);
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
        if(getClass().equals(ApiTest.class)) {
            LoggerUtils.logInfo("API TEST START");
        }
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        LoggerUtils.logSuccess(String.format("Run %s.%s", this.getClass().getName(), method.getName()));
        if(!method.getName().substring(0,7).equals("testApi")) {
            try {
                if (!methodsOrder.isGroupStarted(method) || methodsOrder.isGroupFinished(method)) {
                    startDriver();
                    getWeb();
                    LoginPage
                            .open(getDriver(), baseUrl)
                            .login(login, password);
                } else {
                    getWeb();
                }
            } catch (Exception e) {
                stopDriver();
                throw new RuntimeException(e);
            } finally {
                methodsOrder.markAsInvoked(method);
            }
        }else {
            ApiUtils.setBaseUrl(baseUrl);
        }
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        if(!method.getName().substring(0,7).equals("testApi")) {
            if (!testResult.isSuccess()) {
                LoggerUtils.logError(String.format("ERROR: %s.%s", this.getClass().getName(), method.getName()));
                ProjectUtils.takeScreenshot(driver, method.getName(), this.getClass().getName());
            }

            if (methodsOrder.isGroupFinished(method) && testResult.isSuccess()) {
                stopDriver();
            }
        }
        LoggerUtils.logInfo(String.format("Execution time is %o sec\n\n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000));
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
