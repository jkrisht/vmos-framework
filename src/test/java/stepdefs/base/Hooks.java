package stepdefs.base;

import com.vmos.utils.TestConstants;
import com.vmos.utils.VmosApiController;
import com.vmos.utils.VmosDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

public class Hooks {
    private static VmosDriver driver;
    final Logger logger = Logger.getLogger(this.getClass());

    public static VmosDriver getDriver() {
        return driver;
    }

    public static VmosApiController getApiController() {
        return VmosApiController.getVmosController();
    }

    @Before
    public void setUp(Scenario scenario) {
        if (!TestConstants.isApiTest) {
            // Launch browser
            logger.info("Launching the browser for scenario: " + scenario.getName());
            driver = VmosDriver.getVmosDriver();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (!TestConstants.isApiTest) {
            logger.info("Closing the browser sessions for the following scenario: " + scenario.getName());
            driver.quitDriver();
        }
    }
}
