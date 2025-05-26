package com.jmtech.core.ui.actions;

import org.openqa.selenium.WebDriver;

/**
 * The BasePageObject serves as a foundational class for page objects in a
 * Selenium-based UI testing framework.
 * <p>
 * It provides common utilities for interacting with web elements and managing
 * page interactions,
 * ensuring consistency and code reuse throughout the framework.
 * </p>
 *
 * This class follows the Page Object Model pattern but could be improved in a
 * few ways:
 * 1. Consider using a builder pattern for more flexible initialization
 * 2. Add lazy initialization of utility classes to improve performance
 * 3. Consider making the class abstract since it's meant to be extended
 * 4. Add methods for common page operations like waitForPageLoad()
 * 5. Consider using composition over inheritance by having page components
 *
 * Current implementation:
 * - CommonInteractions: Handles standard user interactions
 * - ElementFinder: Finds elements including shadow DOM
 * - AssertionsUi: Provides element assertions
 *
 * @author josedash
 */
public abstract class UIInteractions {

    private final WebDriver driver;
    private CommonInteractions commonInteractions;
    private ElementFinder elementFinder;
    private AssertionsUi assertionsUi;
    private WaitUtils waitUtils;

    public UIInteractions(WebDriver driver) {
        this.driver = driver;
    }

    public CommonInteractions interactions() {
        if (commonInteractions == null) {
            commonInteractions = new CommonInteractions(driver);
        }
        return commonInteractions;
    }

    public ElementFinder elementFinder() {
        if (elementFinder == null) {
            elementFinder = new ElementFinder(driver);
        }
        return elementFinder;
    }

    public AssertionsUi assertions() {
        if (assertionsUi == null) {
            assertionsUi = new AssertionsUi(driver);
        }
        return assertionsUi;
    }

    public WaitUtils waitUtils() {
        if (waitUtils == null) {
            waitUtils = new WaitUtils();
        }
        return waitUtils;
    }

}
