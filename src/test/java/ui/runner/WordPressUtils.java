package ui.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordPressUtils {

    private static final HttpClient client = HttpClient.newBuilder().build();

    private static String sessionId;

    private static String getCrumbFromPage(String page) {
        final String CRUMB_TAG = "data-crumb-value=\"";

        int crumbTagBeginIndex = page.indexOf(CRUMB_TAG) + CRUMB_TAG.length();
        int crumbTagEndIndex = page.indexOf('"', crumbTagBeginIndex);

        return page.substring(crumbTagBeginIndex, crumbTagEndIndex);
    }

    private static Set<String> getSubstringsFromPage(String page, String from, String to) {
        // 255 - максимально возможная длинна имени, но если используется не латиница или специальные символы, страка будет длинней из-за кодирования (пробел - %20)
        return getSubstringsFromPage(page, from, to, 256);
    }

    private static Set<String> getSubstringsFromPage(String page, String from, String to, int maxSubstringLength) {
        Set<String> result = new HashSet<>();

        int index = page.indexOf(from);
        while (index != -1) {
            index += from.length();
            int endIndex = page.indexOf(to, index);

            if (endIndex != -1 && endIndex - index < maxSubstringLength) {
                result.add(page.substring(index, endIndex));
            } else {
                endIndex = index;
            }

            index = page.indexOf(from, endIndex);
        }

        return result;
    }

    private static String[] getHeader() {
        List<String> result = new ArrayList<>(List.of("Content-Type", "application/x-www-form-urlencoded"));
        if (sessionId != null) {
            result.add("Cookie");
            result.add(sessionId);
        }
        return result.toArray(String[]::new);
    }

    private static HttpResponse<String> getHttp(String url) {
        try {
            return client.send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .headers(getHeader())
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpResponse<String> postHttp(String url, String body) {
        try {
            return client.send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .headers(getHeader())
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getPage(String uri) {
        HttpResponse<String> page = getHttp(ProjectUtils.getUrl() + uri);
        if (page.statusCode() != 200) {
            final String HEAD_COOKIE = "set-cookie";

            HttpResponse<String> loginPage = getHttp(ProjectUtils.getUrl() + "login?from=%2F");
            sessionId = loginPage.headers().firstValue(HEAD_COOKIE).orElse(null);

            // Поле sessionId используется внутри postHttp
            HttpResponse<String> indexPage = postHttp(ProjectUtils.getUrl() + "j_spring_security_check",
                    String.format("j_username=%s&j_password=%s&from=%%2F&Submit=",
                            URLEncoder.encode(ProjectUtils.getUserName(), StandardCharsets.UTF_8),
                            URLEncoder.encode(ProjectUtils.getPassword(), StandardCharsets.UTF_8)));
            sessionId = indexPage.headers().firstValue(HEAD_COOKIE).orElse("");

            page = getHttp(ProjectUtils.getUrl() + uri);
        }

        if (page.statusCode() == 403) {
            throw new RuntimeException(String.format("Authorization does not work with user: \"%s\" and password: \"%s\"", ProjectUtils.getUserName(), ProjectUtils.getPassword()));
        } else if (page.statusCode() != 200) {
            throw new RuntimeException("Something went wrong while clearing data");
        }

        return page.body();
    }

    static void login(WebDriver driver, int port) {
        driver.get("http://localhost:" + port + "/wp-admin/");
        driver.findElement(By.id("user_login")).sendKeys(ProjectUtils.getUserName());
        driver.findElement(By.id("user_pass")).sendKeys(ProjectUtils.getPassword());
        driver.findElement(By.id("wp-submit")).click();
    }

    static void logout(WebDriver driver) {
        ProjectUtils.get(driver);
        driver.findElement(By.id("wp-admin-bar-logout")).click();
    }
}


