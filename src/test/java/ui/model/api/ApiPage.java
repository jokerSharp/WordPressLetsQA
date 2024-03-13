package ui.model.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ui.model.base.BasePage;

public class ApiPage extends BasePage {
    public ApiPage(WebDriver driver) {
        super(driver);
    }

    public static Response getUserList(String url) {
        Response resp =  RestAssured.get(url);

        return resp;
    }
}
