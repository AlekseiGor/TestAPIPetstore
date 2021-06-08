package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseMethods {

    public String createJsonFromObject(Object o) {
        var result = "";
        try {
            result = new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            System.out.println("Error when generating JSON from object " + e.getMessage());
        }
        return result;
    }

    public RequestSpecification getBaseSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    private String getBaseUrl() {
        final String PATH_RESOURCES = "/src/test/resources/app.properties";
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(System.getProperty("user.dir") + PATH_RESOURCES));
        } catch (IOException e) {
            System.out.println("Error when getting properties " + e.getMessage());
        }
        return properties.getProperty("baseUrl");
    }
}
