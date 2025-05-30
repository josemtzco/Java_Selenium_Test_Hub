package com.jmtech.core.webdriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesHelper {

    public static ChromeOptions getChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();

        // Maximize the window and disable notifications
        options.addArguments("--window-size=1920,1080", "--disable-notifications");

        // Disable extensions to avoid interference
        options.addArguments("--disable-extensions");

        // Disable GPU acceleration for better compatibility in some environments
        options.addArguments("--disable-gpu");

        // Disable infobars to avoid unnecessary UI clutter
        options.addArguments("--disable-infobars");

        // Ignore certificate errors
        options.addArguments("--ignore-certificate-errors");

        // Avoid crashes due to sandboxing
        options.addArguments("--no-sandbox");

        // Prevent hanging due to session restore prompts
        options.addArguments("--disable-session-crashed-bubble");
        options.addArguments("--disable-save-password-bubble");

        // Enable verbose logging for debugging flaky issues
        options.addArguments("--enable-logging", "--v=1");

        // Set a custom user agent to avoid detection as a bot (if needed)
        options.addArguments("user-agent=CustomAgent/1.0");

        // Enable deterministic rendering
        options.addArguments("--disable-dev-shm-usage");

        // Handle issues with automation being detected
        options.addArguments("--disable-blink-features=AutomationControlled");
        return options;
    }

    public static FirefoxOptions getFirefoxCapabilities() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920", "--height=1080");
        return options;
    }

    public static EdgeOptions getEdgeCapabilities() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate");
        return options;
    }

    public static DesiredCapabilities getRemoteCapabilities(String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browser.toLowerCase()) {
            case "chrome":
                capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeCapabilities());
                break;
            case "firefox":
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getFirefoxCapabilities());
                break;
            case "edge":
                capabilities.setCapability(EdgeOptions.CAPABILITY, getEdgeCapabilities());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return capabilities;
    }
}


