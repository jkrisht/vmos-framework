package stepdefs;

import com.vmos.factories.BundleFile;
import com.vmos.pojo.User;
import com.vmos.utils.PropertiesUtil;
import com.vmos.utils.VmosApiController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import stepdefs.base.Hooks;

public class LoginApiSteps {
    VmosApiController controller;
    Response response;

    public LoginApiSteps() {
        controller = Hooks.getApiController();
    }

    @Given("^user ([^\\\"]*) and ([^\\\"]*) and make ([^\\\"]*) call$")
    public void authAPIRequest(String email, String password, String api) {
        User user = new User(email, password);
        String resource = PropertiesUtil.getBundle(BundleFile.API_RESOURCES).getString(api);
        resource = controller.getResourceWithApiVersion(resource);
        RequestSpecification specification = controller.getRequestSpec();
        response = controller.postRequest(specification, resource, user);
    }

    @Then("^user should get ([^\\\"]*) with ([^\\\"]*) in response$")
    public void validateSigninResponse(int statusCode, String fields) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Login API request is not successful");
        String[] keys = fields.split(",");
        for (String key : keys) {
            String value = response.jsonPath().getString(key);
            Assertions.assertTrue(value != null && !value.isEmpty(),
                    key + " value is not returned in the response");
        }
    }
}
