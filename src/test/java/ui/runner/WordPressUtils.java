package ui.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WordPressUtils {
    private static final  String login = "admin";
    private static final  String password = "admin";

    static void login(WebDriver driver) {
        driver.findElement(By.id("user_login")).sendKeys(login);
        driver.findElement(By.id("user_pass")).sendKeys(password);
        driver.findElement(By.id("wp-submit")).click();
    }
}
