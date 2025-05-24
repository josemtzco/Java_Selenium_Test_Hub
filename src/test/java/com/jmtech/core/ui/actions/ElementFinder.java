package com.jmtech.core.ui.actions;

import io.github.sukgu.Shadow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class ElementFinder {

    private final WebDriver driver;
    private final Shadow shadow;
    private final WebDriverWait wait;


    public ElementFinder(WebDriver driver) {
        this.driver = driver;
        this.shadow = new Shadow(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public WebElement findShadowElement(String cssSelector) {
        return shadow.findElement(cssSelector);
    }

    public WebElement findNestedShadowElement(String... selectors) {
        return shadow.findElement(Arrays.toString(selectors));
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public List<WebElement> findShadowElements(String cssSelector) {
        return shadow.findElements(cssSelector);
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitForElementToBeInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForPresenceOfElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}