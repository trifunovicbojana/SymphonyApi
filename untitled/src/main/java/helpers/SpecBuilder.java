package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class SpecBuilder {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder().setBaseUri(Endpoints.TESTING_ENVIRONMENT)
                .setContentType(ContentType.JSON).log(LogDetail.ALL)
                 .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}

