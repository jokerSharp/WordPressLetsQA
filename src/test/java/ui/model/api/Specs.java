package ui.model.api;

import com.github.dockerjava.transport.DockerHttpClient;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.internal.http.HTTPBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.testcontainers.shaded.org.bouncycastle.asn1.x509.RSAPublicKeyStructure;

import java.security.PublicKey;

public class Specs {

    public static RequestSpecification requestSpec(String baseURI, int port, String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setPort(port)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    public static void installSpec(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
