package ui.runner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import ui.model.base.BasePage;

import static io.restassured.RestAssured.baseURI;
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
                .when().get(baseUrl + apiUrl + USERS_LIST).andReturn();

        return resp;
    }

    public static boolean postNewUser(String requestBody, String baseUrl) {
        RestAssured
                .given()
                .auth().preemptive().basic(apiLogin, apiPassword)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(baseUrl + apiUrl + "/users")
                .then()
                .assertThat().statusCode(201)
                .log().body();

        return true;
    }
// Asdf.1234.qpwoei
}
