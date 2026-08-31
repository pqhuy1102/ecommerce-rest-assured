package api.specifications;

import api.config.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {
    private RequestSpecFactory(){}

    public static RequestSpecification defaultSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("base.url"))
                .setAccept(ContentType.JSON)
                .addFilter(allureRestAssuredFilter())
                .build();
    }

    private static AllureRestAssured allureRestAssuredFilter() {
        return new AllureRestAssured()
                .setRequestAttachmentName("API request")
                .setResponseAttachmentName("API response");
    }
}
