package com.jmtech.core.ui.actions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.sukgu.Shadow;

public class ElementFinder {

    private final WebDriver driver;
    private final Shadow shadow;
    private final WebDriverWait wait;
    private final WaitUtils waitUtils;

    public ElementFinder(WebDriver driver) {
        this.driver = driver;
        this.shadow = new Shadow(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.waitUtils = new WaitUtils();
    }

    public WebElement findElement(By locator) {
        waitForCondition(() -> driver.findElement(locator) != null, 30_000, 1_000);
        return driver.findElement(locator);
    }

    public WebElement findShadowElement(String cssSelector) {
        waitForCondition(() -> shadow.findElement(cssSelector) != null, 30_000, 1_000);
        return shadow.findElement(cssSelector);
    }

    public WebElement findNestedShadowElement(String... selectors) {
        waitForCondition(() -> shadow.findElement(Arrays.toString(selectors)) != null, 30_000, 1_000);
        return shadow.findElement(Arrays.toString(selectors));
    }

    public List<WebElement> findElements(By locator) {
        waitForCondition(() -> driver.findElements(locator) != null, 30_000, 1_000);
        return driver.findElements(locator);
    }

    public List<WebElement> findShadowElements(String cssSelector) {
        waitForCondition(() -> shadow.findElements(cssSelector) != null, 30_000, 1_000);
        return shadow.findElements(cssSelector);
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        waitForCondition(() -> element.isDisplayed(), 30_000, 1000);
        return element;
    }

    public boolean waitForElementToBeInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForPresenceOfElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Enhanced methods using WaitUtils for more flexible waiting
    public WebElement findElementWithRetry(By locator, long timeoutMillis) {
        WebElement[] foundElement = new WebElement[1];
        boolean found = waitUtils.waitForCondition(() -> {
            try {
                foundElement[0] = driver.findElement(locator);
                return foundElement[0] != null;
            } catch (Exception e) {
                return false;
            }
        }, timeoutMillis, 500);

        if (found) {
            return foundElement[0];
        }
        throw new RuntimeException("Element not found within timeout: " + locator);
    }

    public WebElement findShadowElementWithRetry(String cssSelector, long timeoutMillis) {
        WebElement[] foundElement = new WebElement[1];
        boolean found = waitUtils.waitForCondition(() -> {
            try {
                foundElement[0] = shadow.findElement(cssSelector);
                return foundElement[0] != null;
            } catch (Exception e) {
                return false;
            }
        }, timeoutMillis, 500);

        if (found) {
            return foundElement[0];
        }
        throw new RuntimeException("Shadow element not found within timeout: " + cssSelector);
    }

    public boolean waitForElementTextToContain(WebElement element, String expectedText, long timeoutMillis) {
        return waitUtils.waitForCondition(() -> {
            try {
                String actualText = element.getText();
                return actualText != null && actualText.contains(expectedText);
            } catch (Exception e) {
                return false;
            }
        }, timeoutMillis, 500);
    }

    public boolean waitForElementAttributeToContain(WebElement element, String attribute, String expectedValue,
            long timeoutMillis) {
        return waitUtils.waitForCondition(() -> {
            try {
                String actualValue = element.getAttribute(attribute);
                return actualValue != null && actualValue.contains(expectedValue);
            } catch (Exception e) {
                return false;
            }
        }, timeoutMillis, 500);
    }

    public List<WebElement> waitForElementsToBePresent(By locator, int expectedCount, long amountOfSecondsToWait) {
        AtomicReference<List<WebElement>> foundElements = new AtomicReference<>();
        boolean found = waitUtils.waitForCondition(() -> {
            try {
                foundElements.set(driver.findElements(locator));
                return foundElements.get().size() >= expectedCount;
            } catch (Exception e) {
                return false;
            }
        }, amountOfSecondsToWait, 500);

        if (found) {
            return foundElements.get();
        }
        throw new RuntimeException("Expected number of elements not found within timeout. Expected: " + expectedCount
                + ", Locator: " + locator);
    }

    public boolean waitForCondition(BooleanSupplier condition, long maxWaitTime) {
        return waitUtils.waitForCondition(condition, maxWaitTime, 1000);
    }

    public boolean waitForCondition(BooleanSupplier condition, long maxWaitTime, long retryIntervals) {
        return waitUtils.waitForCondition(condition, maxWaitTime, retryIntervals);
    }

    public boolean waitForCondition(BooleanSupplier condition) {
        return waitUtils.waitForCondition(condition, 30_000, 1_000);
    }

}