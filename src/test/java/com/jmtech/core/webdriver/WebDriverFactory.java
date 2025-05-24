package com.jmtech.core.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;

public class WebDriverFactory {

    // Using ConcurrentHashMap for better thread safety and performance
    private static final ConcurrentHashMap<Long, WebDriver> driverMap = new ConcurrentHashMap<>();

    private WebDriverFactory() {
        // Private constructor to prevent instantiation
    }

    public static synchronized void createWebDriver(String browser, boolean useRemote) {
        Long threadId = Thread.currentThread().threadId();
        if (!driverMap.containsKey(threadId)) {
            WebDriver driver = useRemote ? createRemoteWebDriver(browser) : createLocalWebDriver(browser);
            driverMap.put(threadId, driver);
        }
    }

    private static WebDriver createLocalWebDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver(CapabilitiesHelper.getChromeCapabilities());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver(CapabilitiesHelper.getFirefoxCapabilities());
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver(CapabilitiesHelper.getEdgeCapabilities());
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static WebDriver createRemoteWebDriver(String browser) {
        DesiredCapabilities capabilities = CapabilitiesHelper.getRemoteCapabilities(browser);
        try {
            return new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    }

    /**
     * Obtener el Web Driver correspondiente a cada hilo.
     *
     * @return Get Current WebDriver
     */
    public static WebDriver getWebDriver() {
        Long threadId = Thread.currentThread().threadId();
        WebDriver driver = driverMap.get(threadId);
        if (driver == null) {
            throw new IllegalStateException("WebDriver has not been initialized. Call createWebdriver first.");
        }
        return driver;
    }

    public static void quitWebDriver() {
        Long threadId = Thread.currentThread().threadId();
        WebDriver driver = driverMap.remove(threadId);
        if (driver != null) {
            driver.quit();
        }
    }
}
