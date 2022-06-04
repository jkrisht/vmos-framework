package com.vmos.components.login;

import com.vmos.factories.BundleFile;
import com.vmos.factories.PageFactory;
import com.vmos.pages.HomePage;
import com.vmos.pojo.User;
import com.vmos.utils.PropertiesUtil;
import com.vmos.utils.VmosDriver;
import org.openqa.selenium.By;

public class CreateAccountTab extends AbstractRegisterSignInComponent {

    public CreateAccountTab(VmosDriver driver, BundleFile file) {
        this.driver = driver;
        this.bundle = PropertiesUtil.getBundle(file);
    }

    public CreateAccountTab(VmosDriver driver) {
        this.driver = driver;
    }

    public By createAccountTab() {
        return By.xpath(String.format(tabElement, bundle.getString("createAccount")));
    }

    public By createAccountButton() {
        return By.xpath(String.format(button, bundle.getString("createAccount")));
    }

    @Override
    public void switchTab() {
        logger.info("SignInTab - switchTab()");
        waitForComponentToLoad(10);
        driver.waitAndClickElement(createAccountTab(), 10);
        driver.waitUntilElementClickable(nameInput(), 10);
    }

    /**
     * Create a new account in Vita Mojo account
     *
     * @param user
     * @return
     */
    public HomePage createAccount(User user) {
        logger.info("SignInTab - signInToAccount(): " + user);
        // Clicking on Create Account button is not creating account. Still UI is on Register account page only
        // I tried different waits to solve this. But none of them are working. So I used Tread.sleep
        waitForComponentToLoad(10);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.sendKeys(nameInput(), user.getName());
        driver.sendKeys(emailInput(), user.getEmail());
        driver.sendKeys(passwordInput(), user.getPassword());
        driver.waitAndClickElement(createAccountButton(), 10);
        waitForComponentToDisappear(20);
        return PageFactory.homePage(driver);
    }
}
