package com.vmos.components.login;

import com.vmos.factories.BundleFile;
import com.vmos.factories.PageFactory;
import com.vmos.pages.HomePage;
import com.vmos.pojo.User;
import com.vmos.utils.PropertiesUtil;
import com.vmos.utils.VmosDriver;
import org.openqa.selenium.By;

public class SignInTab extends AbstractRegisterSignInComponent {

    public SignInTab(VmosDriver driver, BundleFile file) {
        this.driver = driver;
        this.bundle = PropertiesUtil.getBundle(file);
    }

    public SignInTab(VmosDriver driver) {
        this.driver = driver;
    }

    public By signInTab() {
        return By.xpath(String.format(tabElement, bundle.getString("login")));
    }

    public By loginButton() {
        return By.xpath(String.format(button, bundle.getString("login")));
    }

    @Override
    public void switchTab() {
        logger.info("SignInTab - switchTab()");
        driver.waitAndClickElement(signInTab(), 10);
        driver.waitUntilElementVisible(emailInput(), 10);
    }

    /***
     * Sign In into Vita Mojo account
     * @param user
     * @return
     */
    public HomePage signInToAccount(User user) {
        logger.info("SignInTab - signInToAccount(): " + user);
        waitForComponentToLoad(10);
        driver.waitUntilElementClickable(loginButton(), 10);
        driver.sendKeys(emailInput(), user.getEmail());
        driver.sendKeys(passwordInput(), user.getPassword());
        driver.clickElement(loginButton());
        waitForComponentToDisappear(10);
        return PageFactory.homePage(driver);
    }
}
