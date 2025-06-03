package com.jmtech.core.ui.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Assertions for web elements
 */
public class AssertionsUi {

    private final WebDriver webDriver;
    private final WaitUtils waitUtils;

    public AssertionsUi(WebDriver driver) {
        this.webDriver = driver;
        this.waitUtils = new WaitUtils();
    }

    public void assertElementTextEquals(WebElement element, String expectedText) {
        waitUtils.waitForCondition(() -> !element.getText().isEmpty(), 5000, 500);
        String actualText = element.getText();
        Assert.assertEquals(expectedText, "Text does not match!", actualText);
    }

    public void assertElementIsDisplayed(WebElement element) {
        Assert.assertTrue(waitUtils.waitForCondition(() -> element.isDisplayed(), 10000, 500), "Element is not displayed!");
    }

    public void assertElementIsEnabled(WebElement element) {
        Assert.assertTrue(waitUtils.waitForCondition(() -> element.isEnabled(), 5000, 500), "Element is not enabled!");
    }

    public void assertElementAttributeContains(WebElement element, String attribute, String expectedValue) {
        // Wait for attribute to be present and contain expected value
        waitUtils.waitForCondition(() -> {
            String actualValue = element.getAttribute(attribute);
            return actualValue != null && actualValue.contains(expectedValue);
        }, 5000, 500);
        
        String actualValue = element.getAttribute(attribute);
        assert actualValue != null;
        Assert.assertTrue(actualValue.contains(expectedValue), "Attribute value does not contain expected text!");
    }

    public void assertElementSelected(WebElement element) {
        Assert.assertTrue(waitUtils.waitForCondition(() -> element.isSelected(), 5000, 500), "Element is not selected!");
    }

    public void assertDropdownSelectedValue(WebElement dropdownElement, String expectedValue) {
        // Wait for dropdown to have the expected selected value
        waitUtils.waitForCondition(() -> {
            Select dropdown = new Select(dropdownElement);
            String actualValue = dropdown.getFirstSelectedOption().getText();
            return expectedValue.equals(actualValue);
        }, 5000, 500);
        
        Select dropdown = new Select(dropdownElement);
        String actualValue = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(expectedValue, "Dropdown selected value does not match!", actualValue);
    }

    public void assertTitleContains(String expectedTitleFragment) {
        // Wait for page title to contain expected fragment
        waitUtils.waitForCondition(() -> {
            String actualTitle = webDriver.getTitle();
            return actualTitle != null && actualTitle.contains(expectedTitleFragment);
        }, 10000, 500);
        
        String actualTitle = webDriver.getTitle();
        assert actualTitle != null;
        Assert.assertTrue(actualTitle.contains(expectedTitleFragment), "Page title does not contain expected text!");
    }

    // Additional assertion methods that leverage WaitUtils
    public void assertElementTextEqualsWithTimeout(WebElement element, String expectedText, long timeoutMillis) {
        waitUtils.waitForCondition(() -> expectedText.equals(element.getText()), timeoutMillis, 500);
        String actualText = element.getText();
        Assert.assertEquals(expectedText, "Text does not match!", actualText);
    }

    public void assertElementBecomesVisible(WebElement element, long timeoutMillis) {
        boolean isVisible = waitUtils.waitForCondition(() -> element.isDisplayed(), timeoutMillis, 500);
        Assert.assertTrue(isVisible, "Element did not become visible within timeout!");
    }

    public void assertElementBecomesInvisible(WebElement element, long timeoutMillis) {
        boolean isInvisible = waitUtils.waitForCondition(() -> !element.isDisplayed(), timeoutMillis, 500);
        Assert.assertTrue(isInvisible, "Element did not become invisible within timeout!");
    }
}
