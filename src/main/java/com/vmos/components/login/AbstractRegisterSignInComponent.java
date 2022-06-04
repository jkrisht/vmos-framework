package com.vmos.components.login;

import com.vmos.components.BaseComponent;
import org.openqa.selenium.By;

public abstract class AbstractRegisterSignInComponent extends BaseComponent {
    protected final String formId = "auth";
    protected final String name = "firstName";
    protected final String email = "email";
    protected final String password = "password";
    protected final String tabElement = "//ul[@role]//li[normalize-space()='%s']";
    protected final String button = "//button[normalize-space()='%s']";

    public abstract void switchTab();

    @Override
    public boolean isPresent() {
        return driver.findElement(formId()).isDisplayed();
    }

    @Override
    public void waitForComponentToLoad(int timeoutInSeconds) {
        driver.waitUntilElementVisible(formId(), timeoutInSeconds);
    }

    @Override
    public void waitForComponentToDisappear(int timeoutInSeconds) {
        driver.waitUntilElementNotVisible(formId(), timeoutInSeconds);
    }

    public By getElementById(String id) {
        return By.id(id);
    }

    public By formId() {
        return getElementById(formId);
    }

    public By emailInput() {
        return getElementById(email);
    }

    public By passwordInput() {
        return getElementById(password);
    }

    public By nameInput() {
        return getElementById(name);
    }
}
