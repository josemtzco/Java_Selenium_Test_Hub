package com.jmtech.tests.ui;

import org.testng.annotations.Test;

import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;
import com.jmtech.webpages.aa.AASearchFlight;

@Test
public class AmericanAirlinesFlightsTests extends BaseUiTest {

    @Test
    public void verifySearchFly() throws InterruptedException {
        AASearchFlight aaSearchFlight = new AASearchFlight(getWebDriver());
        aaSearchFlight.navigateToSearchFlight();
        Thread.sleep(4000);
        aaSearchFlight.assertAmericanAirlinesBookFlightPage();
        aaSearchFlight.setLeavingFromCity("MEX");
    }
}
