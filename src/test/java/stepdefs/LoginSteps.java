package stepdefs;

import com.vmos.components.login.SignInTab;
import com.vmos.pages.HomePage;
import com.vmos.pages.RegisterSignInPage;
import com.vmos.pojo.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import stepdefs.base.BaseStep;
import stepdefs.base.Hooks;

import static com.vmos.factories.PageFactory.homePage;

public class LoginSteps extends BaseStep {
    private RegisterSignInPage registerSignInPage;
    private User user;

    public LoginSteps() {
        this.driver = Hooks.getDriver();
    }

    @Given("^user is on login page$")
    public void navigateLoginPage() {
        driver.navigateToHomePage();
        HomePage homePage = homePage(driver);
        registerSignInPage = homePage.clickLoginButton();
    }

    @When("^user enters ([^\\\"]*) and ([^\\\"]*) and clicks login button$")
    public void enterLoginDetails(String email, String password) {
        user = new User(email, password);
        SignInTab signInTab = registerSignInPage.signInTab();
        signInTab.signInToAccount(user);
    }

    @Then("^user navigated to the HomePage with ([^\\\"]*) as profile name$")
    public void verifyUserLoggedInAccount(String userName) {
        HomePage homePage = homePage(driver);
        Assertions.assertTrue(homePage.isUserLoggedIn(userName), "Following user is not loggedin successfully: " + userName);
    }
}
