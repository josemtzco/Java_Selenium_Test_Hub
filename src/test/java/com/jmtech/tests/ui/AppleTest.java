package com.jmtech.tests.ui;

import org.testng.annotations.Test;

import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;

@Test
public class AppleTest extends BaseUiTest {


    @Test
    public void googleTest() throws InterruptedException {
        getWebDriver().get("http://www.google.com");
        uiInteractions.waitUtils().waitForSeconds(10);
        System.out.printf("Google Test: %s\n", getWebDriver().getTitle());
    }

}
