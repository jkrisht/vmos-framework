package com.vmos.factories;

import com.typesafe.config.Config;
import com.vmos.config.Browser;
import com.vmos.config.VmosConfigFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
public class DriverFactory {
    private static final Config config = VmosConfigFactory.getInstance().getConfig();
    private static final Browser BROWSER = Browser.parse(config.getString("BROWSER"));

    private DriverFactory() {
        throw new IllegalStateException("Static factory class");
    }

    public static WebDriver getDriver() {
        return getLocalWebDriver();
    }

    private static WebDriver getLocalWebDriver() {
        log.info("Getting driver for browser: {}", BROWSER);
        switch (BROWSER) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(CapabilitiesFactory.getChromeOptions());
            default:
                throw new IllegalStateException(String.format("%s is not a valid browser choice. Pick your browser from %s.", BROWSER, java.util.Arrays.asList(Browser.values())));
        }
    }
}
