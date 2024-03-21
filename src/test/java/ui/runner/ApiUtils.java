package ui.runner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ui.model.api.Auth;
import ui.model.api.UserReq;
import ui.model.api.UserResp;
import ui.model.base.BasePage;

public class ApiUtils extends BasePage {

    private static final String apiLogin = ProjectUtils.getAdminName();
    private static final String apiPassword = ProjectUtils.getAdminPassword();
    private static String TOKEN = "";
    private static final String apiUrl = "wp-json/wp/v2";
    private static final String USERS_LIST = "/users?context=view&page=1&per_page=10&order=desc&orderby=id";
    private static final String USER_POST = "/users";
    private static final String USER_DELETE = "/users/";
    public int new_user_id = 0;

    public ApiUtils(WebDriver driver) {
        super(driver);
    }

    public static void setTOKEN(Auth auth, String baseURL) {
        auth.setUsername(apiLogin);
        auth.setPassword(apiPassword);
        Response resp = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post(baseURL + "wp-json/api/v1/token")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        TOKEN = resp.path("token_type") + " " + resp.path("jwt_token");
    }

    public static Response getListUsers(String baseURL) {
        Response resp = RestAssured
                .given()
                    .header("Authorization", TOKEN)
                .when()
                    .get(baseURL + apiUrl + USERS_LIST)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

        return resp;
    }

    public static UserResp postNewUser(UserReq userReq, String baseUrl) {
        UserResp userResp = new UserResp();
        Response resp = RestAssured
                .given()
                    .header("Authorization", TOKEN)
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

    public static Response deleteNewUser(String request, String baseUrl) {
        Response resp = RestAssured
                .given()
                .log().all()
                .header("Authorization", TOKEN)
                .when()
                .delete(baseUrl + apiUrl + USER_DELETE + request)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();

        return resp;
    }
}