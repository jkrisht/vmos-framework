package com.vmos.factories;


import com.typesafe.config.Config;
import com.vmos.config.Browser;
import com.vmos.config.VmosConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.logging.Level;

import static java.lang.Boolean.parseBoolean;

public class CapabilitiesFactory {
    private static final Config config = VmosConfigFactory.getInstance().getConfig();

    private static final boolean HEADLESS = parseBoolean(config.getString("HEADLESS"));
    private static final String SELENIUM_LOG_LEVEL = config.getString("SELENIUM_LOG_LEVEL");

    public static Capabilities getCapabilities(Browser browser) {
        switch (browser) {
            case CHROME:
                return getChromeOptions();
            default:
                throw new IllegalStateException(String.format("%s is not a valid browser choice. Pick your browser from %s.", browser, java.util.Arrays.asList(Browser.values())));
        }
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(HEADLESS);
        chromeOptions.addArguments("--window-size=1920,1080");

        // To get error console logs
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.parse(SELENIUM_LOG_LEVEL));
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        return chromeOptions;
    }
}
