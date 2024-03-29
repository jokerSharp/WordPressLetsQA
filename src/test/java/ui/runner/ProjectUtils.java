package ui.runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public final class ProjectUtils {
    private static final String PREFIX_PROP = "local.";
    private static final String PROP_HOST = PREFIX_PROP + "host";
    private static final String PROP_PORT = PREFIX_PROP + "port";
    private static final String PROP_ADMIN_USERNAME = PREFIX_PROP + "admin.username";
    private static final String PROP_ADMIN_PAS = PREFIX_PROP + "admin.password";
    private static final String ENV_CHROME_OPTIONS = "CHROME_OPTIONS";
    private static final String ENV_APP_OPTIONS = "APP_OPTIONS";
    private static final String PROP_CHROME_OPTIONS = PREFIX_PROP + ENV_CHROME_OPTIONS.toLowerCase();
    private static Properties properties;

    private static void initProperties() {
        if (properties == null) {
            properties = new Properties();
            if (isServerRun()) {
                properties.setProperty(PROP_CHROME_OPTIONS, System.getenv(ENV_CHROME_OPTIONS));
                if (System.getenv(ENV_APP_OPTIONS) != null) {
                    for (String option : System.getenv(ENV_APP_OPTIONS).split(";")) {
                        String[] optionArr = option.split("=");
                        properties.setProperty(PREFIX_PROP + optionArr[0], optionArr[1]);
                    }
                }
            } else {
                try {
                    InputStream inputStream = ProjectUtils.class.getClassLoader().getResourceAsStream("local.properties");
                    if (inputStream == null) {
                        LoggerUtils.logError("ERROR: The \u001B[31mlocal.properties\u001B[0m file not found in src/test/resources/ directory.");
                        LoggerUtils.logError("You need to create it from local.properties.TEMPLATE file.");
                        System.exit(1);
                    }
                    properties.load(inputStream);
                } catch (IOException ignore) {
                }
            }
        }
    }

    static final ChromeOptions chromeOptions;

    static {
        initProperties();

        chromeOptions = new ChromeOptions();
        String options = System.getProperty(PROP_CHROME_OPTIONS);
        if (options == null) {
            options = properties.getProperty(PROP_CHROME_OPTIONS);
        }
        if (options != null) {
            chromeOptions.addArguments(options.split(";"));
        }

        WebDriverManager.chromedriver().setup();
    }

    static boolean isServerRun() {
        return System.getenv("CI_RUN") != null;
    }

    static String getAdminName() {
        return properties.getProperty(PROP_ADMIN_USERNAME);
    }

    static String getAdminPassword() {
        return properties.getProperty(PROP_ADMIN_PAS);
    }

    public static WebDriver createDriver() {
        WebDriver driver = new ChromeDriver(ProjectUtils.chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public static void get(WebDriver driver, String url) {
        driver.get(url);
    }

    static File takeScreenshot(WebDriver driver, String methodName, String className) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(String.format("screenshots/%s.%s.png", className, methodName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
