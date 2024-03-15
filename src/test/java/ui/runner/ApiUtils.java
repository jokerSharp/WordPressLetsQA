package ui.runner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ui.model.api.UserReq;
import ui.model.api.UserResp;
import ui.model.base.BasePage;

public class ApiUtils extends BasePage {

    private static final String apiLogin = ProjectUtils.getAdminName();
    private static final String apiPassword = ProjectUtils.getAdminPassword();
    private static final String apiUrl = "wp-json/wp/v2";
    private static final String USERS_LIST = "/users?context=view&page=1&per_page=10&order=desc&orderby=id";
    private static final String USER_POST = "/users";
    public int new_user_id = 0;

    public ApiUtils(WebDriver driver) {
        super(driver);
    }

    public static Response getListUsers(String baseURL) {
        Response resp = RestAssured
                .given()
                    .auth().preemptive().basic(apiLogin, apiPassword)
                    .log().all()
                .when()
                    .get(baseURL + apiUrl + USERS_LIST)
                .then()
                    .statusCode(200)
                    .extract().response();

        return resp;
    }

    public static UserResp postNewUser(UserReq userReq, String baseUrl) {
        UserResp userResp = new UserResp();
        Response resp = RestAssured
                .given()
                    .auth().preemptive().basic(apiLogin, apiPassword)
                    .contentType(ContentType.JSON)
                    .body(userReq)
                .when()
                    .post(baseUrl + apiUrl + USER_POST)
                .then()
                    .log().body()
                    .assertThat().statusCode(201)
                    .extract().response();
        userResp.setEmail(resp.path("email"));
        userResp.setUsername(resp.path("username"));
        userResp.setId(resp.path("id"));
        return userResp;
    }
}
