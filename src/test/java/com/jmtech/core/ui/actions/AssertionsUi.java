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

    public AssertionsUi(WebDriver driver) {
        this.webDriver = driver;
    }

    public void assertElementTextEquals(WebElement element, String expectedText) {
        String actualText = element.getText();
        Assert.assertEquals(expectedText, "Text does not match!", actualText);
    }

    public void assertElementIsDisplayed(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed!");
    }

    public void assertElementIsEnabled(WebElement element) {
        Assert.assertTrue(element.isEnabled(), "Element is not enabled!");
    }

    public void assertElementAttributeContains(WebElement element, String attribute, String expectedValue) {
        String actualValue = element.getAttribute(attribute);
        assert actualValue != null;
        Assert.assertTrue(actualValue.contains(expectedValue), "Attribute value does not contain expected text!");
    }

    public void assertElementSelected(WebElement element) {
        Assert.assertTrue(element.isSelected(), "Element is not selected!");
    }

    public void assertDropdownSelectedValue(WebElement dropdownElement, String expectedValue) {
        Select dropdown = new Select(dropdownElement);
        String actualValue = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(expectedValue, "Dropdown selected value does not match!", actualValue);
    }

    public void assertTitleContains(String expectedTitleFragment) {
        String actualTitle = webDriver.getTitle();
        assert actualTitle != null;
        Assert.assertTrue(actualTitle.contains(expectedTitleFragment), "Page title does not contain expected text!");
    }
}
