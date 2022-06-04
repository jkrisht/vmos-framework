package com.vmos.pages;

import com.vmos.factories.BundleFile;
import com.vmos.factories.PageFactory;
import com.vmos.utils.PropertiesUtil;
import com.vmos.utils.VmosDriver;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BasePage {

    // Elements
    private final String postCode = "postCode";
    private final String loginButton = "//a[normalize-space()='%s']";

    public HomePage(VmosDriver driver, BundleFile file) {
        this.driver = driver;
        this.bundle = PropertiesUtil.getBundle(file);
        waitForPageLoad();
    }

    @Override
    public void waitForPageLoad() {
        logger.info("HomePage - waitForPageLoad");
        driver.isPageLoadComplete();
        driver.waitUntilElementVisible(postCode(), 10);
        assertEquals(driver.getTitle(), bundle.getString("pageTitle"),
                "Vmos home page browser title is incorrect.");
    }

    public By postCode() {
        return By.id(postCode);
    }

    public By loginButton() {
        return By.xpath(String.format(loginButton, bundle.getString("loginButton")));
    }

    public RegisterSignInPage clickLoginButton() {
        logger.info("HomePage - clickLoginButton()");
        driver.waitAndClickElement(loginButton(), 10);
        driver.waitUntilElementNotVisible(postCode(), 10);
        return PageFactory.registerSignInPage(driver);
    }
}
