package com.vmos.utils;

import com.typesafe.config.Config;
import com.vmos.config.VmosConfigFactory;
import com.vmos.factories.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class VmosDriver {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String HOME_PAGE_URL;
    private final Logger logger = Logger.getLogger(VmosDriver.class);

    public VmosDriver(WebDriver driver) {
        VmosDriver.driver.set(driver);
    }

    public static VmosDriver getVmosDriver() {
        Config config = VmosConfigFactory.getInstance().getConfig();
        HOME_PAGE_URL = config.getString("URL");
        VmosDriver aDriver = new VmosDriver(DriverFactory.getDriver());
        return aDriver;
    }

    protected WebDriver getDriver() {
        return driver.get();
    }

    public void maximizeWindow() {
        logger.info("Maximizing the browser window.");
        Dimension dimension = new Dimension(1920, 1080);
        getDriver().manage().window().setSize(dimension);
    }

    public void navigateToHomePage() {
        get(HOME_PAGE_URL);
    }

    public void get(String url) {
        logger.info("Navigating to " + url);
        getDriver().get(url);
    }

    // Click the element
    public void clickElement(By by) {
        logger.info("Locate and click the following element: " + by);
        findElement(by).click();
    }

    // Get the current browser title
    public String getTitle() {
        String title = getDriver().getTitle();
        logger.info("Current Browser Window Title: " + title);
        return title;
    }

    // Locate the web element
    public WebElement findElement(By by) {
        logger.info("Locate the following element: " + by);
        try {
            return getDriver().findElement(by);
        } catch (Exception e) {
            logger.trace("Exception occurred while locating the element: " + by, e);
        }
        return null;
    }

    public void sendKeys(By by, String text) {
        logger.info("Enter the text into element: " + by + ", text: " + text);
        findElement(by).sendKeys(text);
    }

    // Move to the element
    public void scrollToElement(By by) {
        logger.info("Scroll to the element: " + by);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(findElement(by)).build().perform();
    }

    // Hover on the given element
    public void clickElementUsingJS(By by) {
        logger.info("Click on element using JS: " + by);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click()", findElement(by));
    }

    public void isPageLoadComplete() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));

        Function pageLoad = new Function() {
            @Override
            public Boolean apply(Object o) {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete");
            }
        };

        wait.until(pageLoad);
    }

    public void waitAndClickElement(By by, long seconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
        wait.pollingEvery(Duration.ofMillis(5)).until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    // Wait until element is visible
    public void waitUntilElementVisible(By by, long seconds) {
        logger.info("Wait until following element is visible till " + seconds + " seconds : " + by);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
        wait.pollingEvery(Duration.ofMillis(5)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    // Wait until element not visible
    public void waitUntilElementNotVisible(By by, int seconds) {
        logger.info("Wait until following element is not visible till " + seconds + " seconds : " + by);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
        wait.pollingEvery(Duration.ofMillis(5)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    // Wait until element is visible
    public void waitUntilElementClickable(By by, long seconds) {
        logger.info("Wait until following element is visible till " + seconds + " seconds : " + by);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    // Verify the element is displayed or not
    public boolean isDisplayed(By by) {
        logger.info("isDisplayed(By by): " + by);
        boolean isDisplayed = false;

        try {
            isDisplayed = findElement(by).isDisplayed();
        } catch (Exception e) {
            isDisplayed = false;
        }

        return isDisplayed;
    }

    // Get element text
    public String getText(By by) {
        logger.info("getText(By by): " + by);
        return findElement(by).getText();
    }

    // Select the given dropdown value
    public void selectDropdownValue(By by, String value) {
        logger.info("selectDropdownValue(By by, String value): " + by + ", " + value);
        Select select = new Select(findElement(by));
        select.selectByValue(value);
    }

    // Close the opened browsers
    public void quitDriver() {
        logger.info("Quit the driver and close the browser");
        driver.get().quit();
        driver.set(null);
    }

    // Switch between the windows
    public void switchToWindow(int index) {
        logger.info("Switch to the window: " + index);
        Set<String> windows = getDriver().getWindowHandles();
        List<String> windowsList = new ArrayList<>(windows);
        getDriver().switchTo().window(windowsList.get(index - 1));
    }
}
