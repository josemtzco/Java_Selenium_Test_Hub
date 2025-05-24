package com.jmtech.tests.ui;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;
import com.jmtech.webpages.aa.AASearchFlight;

@Test
public class BingTest extends BaseUiTest {

    @Test
    public void validateBingHomePage() throws InterruptedException {
       getWebDriver().get("https://www.bing.com");
       helper().assertions().assertTitleContains("Bing");
       AASearchFlight aaSearchFlight = new AASearchFlight(getWebDriver());
       aaSearchFlight.navigateToSearchFlight();
       helper().waitUtils().waitForSeconds(4);
       helper().waitUtils().waitForCondition(() -> helper().elementFinder().findElement(By.id("id_s")).isDisplayed(), 10000, 1000);
       aaSearchFlight.assertAmericanAirlinesBookFlightPage();
       aaSearchFlight.setLeavingFromCity("MEX");
       getWebDriver().get("https://www.aa.com/booking/find-flights");
    }
} 