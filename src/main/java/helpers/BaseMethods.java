package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseMethods {

    private final String PATH_RESOURCES = "/src/test/resources/app.properties";

    public RequestSpecification getBaseSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    private String getBaseUrl() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(System.getProperty("user.dir") + PATH_RESOURCES));
        } catch (IOException e) {
            System.out.println("Error when getting properties " + e.getMessage());
        }
        return properties.getProperty("baseUrl");
    }
}
