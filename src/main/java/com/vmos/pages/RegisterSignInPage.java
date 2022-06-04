package com.vmos.pages;

import com.vmos.components.login.CreateAccountTab;
import com.vmos.components.login.SignInTab;
import com.vmos.factories.BundleFile;
import com.vmos.utils.PropertiesUtil;
import com.vmos.utils.VmosDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterSignInPage extends BasePage {

    public RegisterSignInPage(VmosDriver driver, BundleFile file) {
        this.driver = driver;
        this.bundle = PropertiesUtil.getBundle(file);
        waitForPageLoad();
    }

    public SignInTab signInTab() {
        return new SignInTab(driver, BundleFile.REGISTER_SIGNIN_PAGE);
    }

    public CreateAccountTab createAccountTab() {
        return new CreateAccountTab(driver, BundleFile.REGISTER_SIGNIN_PAGE);
    }

    @Override
    public void waitForPageLoad() {
        logger.info("RegisterSignInPage - waitForPageLoad");
        driver.isPageLoadComplete();
        driver.waitUntilElementVisible(signInTab().formId(), 10);
        assertEquals(driver.getTitle(), bundle.getString("pageTitle"),
                "Vmos home page browser title is incorrect.");
    }
}
