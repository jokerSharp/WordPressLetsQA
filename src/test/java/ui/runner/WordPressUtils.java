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

    static void setDefaultColorScheme(WebDriver driver) {
        driver.get("http://localhost:8000/wp-admin/profile.php");
        if (!driver.findElement(By.cssSelector(".color-option.selected")).getText().equals("Default")) {
            driver.findElement(By.id("admin_color_fresh")).click();
        }
    }
}
