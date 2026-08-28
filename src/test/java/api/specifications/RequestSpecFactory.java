package api.specifications;

import api.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {
    private RequestSpecFactory(){}

    public static RequestSpecification defaultSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("base.url"))
                .setAccept(ContentType.JSON)
                .build();
    }
}
