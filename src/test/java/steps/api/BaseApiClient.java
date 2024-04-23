package steps.api;

import config.EnvironmentConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiClient {

    protected RequestSpecification getBaseRequestSpecification() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(EnvironmentConfig.getBaseUri());
    }
}
