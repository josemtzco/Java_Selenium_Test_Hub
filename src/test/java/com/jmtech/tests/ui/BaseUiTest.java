package com.jmtech.tests.ui;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jmtech.core.api.RestAssuredFactory;
import com.jmtech.core.ui.actions.BasePageObject;
import com.jmtech.core.webdriver.WebDriverFactory;
import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;

@Test
public class BaseUiTest {

    private static final Logger log = LoggerFactory.getLogger(BaseUiTest.class);

    protected BasePageObject baseHelper;

    @BeforeMethod
    public void setUp() {
        log.info("Setting up in the Base UI Test");
        WebDriverFactory.createWebDriver("edge", false);
        RestAssuredFactory.createRestAssured();
        WebDriver driver = getWebDriver();
        baseHelper = new BasePageObject(driver) {}; // anonymous subclass
    }

    protected BasePageObject helper() {
        return baseHelper;
    }

    @AfterMethod
    public void tearDown() {
        log.info("Tearing down in the Base UI Test");
        WebDriverFactory.quitWebDriver();
        RestAssuredFactory.resetRestAssured();
    }
}
