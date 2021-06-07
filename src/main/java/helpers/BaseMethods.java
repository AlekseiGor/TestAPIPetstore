package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseMethods {

    public RequestSpecification getBaseSpecification(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
