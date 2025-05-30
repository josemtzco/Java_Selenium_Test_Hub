package com.jmtech.tests.ui;

import com.jmtech.core.ui.actions.UIInteractions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jmtech.core.webdriver.WebDriverFactory;

@Test
public class BaseUiTest {

    private static final Logger log = LoggerFactory.getLogger(BaseUiTest.class);

    protected UIInteractions uiInteractions;

    @BeforeMethod
    public void setUp() {
        log.info("Setting up in the Base UI Test");
        WebDriverFactory.createWebDriver("edge", false);
        uiInteractions = new UIInteractions(WebDriverFactory.getWebDriver());
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Tearing down in the Base UI Test");
        WebDriverFactory.quitWebDriver();
    }
}
