package stepdefs;

import com.vmos.factories.BundleFile;
import com.vmos.pojo.User;
import com.vmos.utils.DataUtil;
import com.vmos.utils.PropertiesUtil;
import com.vmos.utils.VmosApiController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import stepdefs.base.Hooks;

import java.util.UUID;

public class RegisterApiSteps {
    VmosApiController controller;
    Response response;

    public RegisterApiSteps() {
        controller = Hooks.getApiController();
    }

    @Given("^user details ([^\\\"]*), ([^\\\"]*) and make register ([^\\\"]*) call$")
    public void authAPIRequest(String emailPrefix, String password, String api) {
        User user = new User(DataUtil.generateEmail(emailPrefix), password, emailPrefix);
        String resource = PropertiesUtil.getBundle(BundleFile.API_RESOURCES).getString(api);
        resource = controller.getResourceWithApiVersion(resource);
        RequestSpecification specification = controller.getRequestSpec();
        specification.header("tenant", UUID.randomUUID().toString());
        response = controller.postRequest(specification, resource, user);
    }

    @Then("^user should get ([^\\\"]*) with ([^\\\"]*) in register api response$")
    public void validateSigninResponse(int statusCode, String fields) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Register API request is not successful");
        String[] keys = fields.split(",");
        for (String key : keys) {
            String value = response.jsonPath().getString(key);
            Assertions.assertTrue(value != null && !value.isEmpty(),
                    key + " value is not returned in the response");
        }
    }
}
