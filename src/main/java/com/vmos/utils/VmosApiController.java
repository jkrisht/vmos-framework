package com.vmos.utils;

import com.typesafe.config.Config;
import com.vmos.config.VmosConfigFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apiguardian.api.API;

public class VmosApiController {
    private static String BASE_URI;
    private static String API_VERSION;
    private final Logger logger = Logger.getLogger(VmosDriver.class);

    public static VmosApiController getVmosController() {
        Config config = VmosConfigFactory.getInstance().getConfig();
        BASE_URI = config.getString("API_BASE_URI");
        API_VERSION = config.getString("API_VERSION");
        VmosApiController controller = new VmosApiController();
        return controller;
    }

    public RequestSpecification getRequestSpec() {
        RequestSpecification specification = RestAssured.given();
        specification.baseUri(BASE_URI);
        specification.accept("application/json, text/plain, */*");
        specification.contentType(ContentType.JSON);
        return specification;
    }

    public String getResourceWithApiVersion(String resource) {
        return String.format(resource, API_VERSION);
    }

    public Response postRequest(RequestSpecification specification, String resource, Object body) {
        return specification.when().body(body).log().all().post(resource).then().log().all().extract().response();
    }

    public Response getRequest(RequestSpecification specification) {
        return specification.when().get().then().log().all().extract().response();
    }
}
