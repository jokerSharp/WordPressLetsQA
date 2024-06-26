package ui.runner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ui.model.api.*;
import ui.model.api.pojo.CommentPOJO;
import ui.model.base.BasePage;

public class ApiUtils {

    private static final String apiLogin = ProjectUtils.getAdminName();
    private static final String apiPassword = ProjectUtils.getAdminPassword();
    private static String baseURL;
    private static String TOKEN = "";
    private static final String apiUrl = "wp-json/wp/v2";
    private static final String USERS_LIST = "/users?context=view&page=1&per_page=10&order=desc&orderby=id";
    private static final String USER_POST = "/users";
    private static final String USER_DELETE = "/users/";
    private static final String POST_POST = "/posts";
    private static final String COMMENT_POST = "/comments";
    private static final String CONTEXT_VIEW = "context=view";
    private static final String QUESTION_MARK = "?";
    private static final String EQUAL_SIGN = "=";
    private static final String AND_SIGN = "&";
    private static final String PER_PAGE_10 = "per_page=10";
    private static final String ORDER_BY_DATE = "orderby=date";

    static void setBaseUrl(String url) {
    baseURL = url;
    }

    public static void setTOKEN(Auth auth) {
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

    public static Response getListUsers() {
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

    public static UserResp postNewUser(UserReq userReq) {
        UserResp userResp = new UserResp();
        Response resp = RestAssured
                .given()
                    .header("Authorization", TOKEN)
                    .contentType(ContentType.JSON)
                    .body(userReq)
                .when()
                    .post(baseURL + apiUrl + USER_POST)
                .then()
                    .log().body()
                    .assertThat().statusCode(201)
                    .extract().response();
        userResp.setEmail(resp.path("email"));
        userResp.setUsername(resp.path("username"));
        userResp.setId(resp.path("id"));
        return userResp;
    }

    public static Response deleteNewUser(String request) {
        Response resp = RestAssured
                .given()
                    .log().all()
                    .header("Authorization", TOKEN)
                .when()
                    .delete(baseURL + apiUrl + USER_DELETE + request)
                .then()
                    .log().all()
                    .assertThat().statusCode(200)
                    .extract().response();

        return resp;
    }

    public static PostResponse postNewPost(PostRequest postRequest) {
        PostResponse postResponse = new PostResponse();

        Response resp = RestAssured
                .given()
                    .header("Authorization", TOKEN)
                    .contentType(ContentType.JSON)
                    .body(postRequest)
                .when()
                    .post(baseURL + apiUrl + POST_POST)
                .then()
                    .log().body()
                    .assertThat().statusCode(201)
                    .extract().response();

        postResponse.setTitle(resp.path("title.raw"));
        postResponse.setStatus(resp.path("status"));
        postResponse.setPostId(resp.path("id"));
        postResponse.setAuthorId(resp.path("author"));
        postResponse.setFormat(resp.path("format"));
        postResponse.setCommentStatus(resp.path("comment_status"));

        return postResponse;
    }

    public static CommentPOJO postNewComment(CommentPOJO request) {
        CommentPOJO response = new CommentPOJO();

        Response resp = RestAssured
                .given()
                .log().all()
                .header("Authorization", TOKEN)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(baseURL + apiUrl + COMMENT_POST)
                .then()
                .log().body()
                .assertThat().statusCode(201)
                .extract().response();

        response.setId(resp.path("id"));
        response.setPost(resp.path("post"));
        response.setAuthor(resp.path("author"));
        response.setContent(resp.path("content.raw"));

        return response;
    }

    public static Response deletePost(String request) {
        Response resp = RestAssured
                .given()
                .log().all()
                .header("Authorization", TOKEN)
                .when()
                .delete(baseURL + apiUrl + POST_POST + request)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();

        return resp;
    }

    public static Response getPostsList() {
        Response resp = RestAssured
                .given()
                .header("Authorization", TOKEN)
                .when()
                .get(baseURL + apiUrl + POST_POST + QUESTION_MARK + CONTEXT_VIEW + AND_SIGN + PER_PAGE_10
                        + AND_SIGN + ORDER_BY_DATE)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        return resp;
    }
}