package ui.runner;

import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ui.model.base.BasePage;

import static io.restassured.RestAssured.given;

public class ApiUtils extends BasePage {

    private static final String apiLogin = ProjectUtils.getAdminName();
    private static final String apiPassword = ProjectUtils.getAdminPassword();
    private static final String apiUrl = "wp-json/wp/v2";
    private static final String USERS_LIST = "/users?context=view&page=1&per_page=10&order=desc&orderby=id";

    public ApiUtils(WebDriver driver) {
        super(driver);
    }

    public static Response getListUsers(String baseUrl) {
        Response resp = given().auth().preemptive().basic(apiLogin, apiPassword)
                .when().get(baseUrl + apiUrl + USERS_LIST);

        return resp;
    }
}
