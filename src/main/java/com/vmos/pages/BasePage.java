package com.vmos.pages;


import com.vmos.pojo.User;
import com.vmos.utils.VmosDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.ResourceBundle;

public abstract class BasePage implements IPage {
    final Logger logger = Logger.getLogger(this.getClass());
    private final String profileName = "//a//span[normalize-space()='%s']";
    VmosDriver driver;
    ResourceBundle bundle;

    public By profileName(String userName) {
        return By.xpath(String.format(profileName, userName));
    }

    public boolean isUserLoggedIn(String userName) {
        logger.info("isUserLoggedIn()");
        try {
            System.out.println(profileName(userName));
            driver.waitUntilElementVisible(profileName(userName), 10);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
