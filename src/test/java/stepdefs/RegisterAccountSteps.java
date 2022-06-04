package stepdefs;

import com.vmos.components.login.CreateAccountTab;
import com.vmos.factories.PageFactory;
import com.vmos.pojo.User;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefs.base.BaseStep;
import stepdefs.base.Hooks;

import static com.vmos.utils.DataUtil.generateEmail;

public class RegisterAccountSteps extends BaseStep {
    private User user;

    public RegisterAccountSteps() {
        this.driver = Hooks.getDriver();
    }

    @Then("^user switch to create account tab$")
    public void switchToCreateAccountTab() {
        CreateAccountTab accountTab = PageFactory.registerSignInPage(driver).createAccountTab();
        accountTab.switchTab();
    }

    @When("^user enters ([^\\\"]*) and ([^\\\"]*) and clicks create account button$")
    public void enterLoginDetails(String emailPrefix, String password) {
        user = new User(generateEmail(emailPrefix), password, emailPrefix);
        CreateAccountTab accountTab = PageFactory.registerSignInPage(driver).createAccountTab();
        accountTab.createAccount(user);
    }
}
