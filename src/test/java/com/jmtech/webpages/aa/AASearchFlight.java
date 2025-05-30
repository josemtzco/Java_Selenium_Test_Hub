package com.jmtech.webpages.aa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jmtech.core.ui.actions.UIInteractions;
import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;

public class AASearchFlight extends UIInteractions {

    //Locator store as css because if the page is constantly updated is better to fetch the element
    // on the spot to avoid stale element errors.
    private final String FINDFLIGHT_HEADER_CSS = "#findFlightsHeader";
    private final String inputFromCityCss = "adc-auto-complete#originAirport[label-text='From'] > input[type='text']";
    private final String departDateCss = "";

    public AASearchFlight(WebDriver driver) {
        super(driver); // this initializes the UIInteractions.
    }

    public void navigateToSearchFlight() {
        getWebDriver().get("https://www.aa.com/booking/find-flights");
    }

    public void assertAmericanAirlinesBookFlightPage() {
        System.out.println(getWebDriver().getTitle());
        assertions().assertTitleContains("American Airlines");
    }

    public void setLeavingFromCity(String city) {
        WebElement inputLeavingFromCity = elementFinder().findShadowElement(inputFromCityCss);
        assertions().assertElementIsDisplayed(inputLeavingFromCity);
        interactions().clickElement(inputLeavingFromCity);
        interactions().sendKeys(inputLeavingFromCity, city);
        interactions().clickElement(elementFinder().findElement(By.cssSelector(FINDFLIGHT_HEADER_CSS)));
        assertions().assertElementTextEquals(inputLeavingFromCity, city);
    }

    public void setDepartDate(String date) {
        WebElement inputDepartDate = elementFinder().findShadowElement(departDateCss);
        assertions().assertElementIsDisplayed(inputDepartDate);
        interactions().sendKeys(inputDepartDate, date);

    }

}
