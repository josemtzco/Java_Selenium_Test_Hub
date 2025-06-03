package com.jmtech.tests.ui;

import org.testng.annotations.Test;

import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;
import com.jmtech.webpages.bing.BingPage;

@Test
public class BingTest extends BaseUiTest {

    // Best practice is to create a method in the page object to validate the page.
    // but for the sake of time, we will validate the page here.
    @Test
    public void validateBingHomePage() throws InterruptedException {
        BingPage bingPage = new BingPage(getWebDriver());
        bingPage.openBingWebsite();
        uiInteractions.assertions().assertTitleContains("Bing");
        bingPage.searchFor("Jose Martinez is the best.");
        uiInteractions.waitUtils().sleep(5_000);
    }
}