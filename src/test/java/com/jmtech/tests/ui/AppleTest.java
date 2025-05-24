package com.jmtech.tests.ui;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.jmtech.core.webdriver.WebDriverFactory;
import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;

@Test
public class AppleTest extends BaseUiTest {


    @Test
    public void googleTest() throws InterruptedException {
        getWebDriver().get("http://www.google.com");
        Thread.sleep(10000);
        String title = getWebDriver().getTitle();
        System.out.printf("Google Test: %s\n", title);
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.quitWebDriver();
    }

}
