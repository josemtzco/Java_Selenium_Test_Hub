package com.jmtech.webpages.bing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.jmtech.core.ui.actions.UIInteractions;
import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;

public class BingPage extends UIInteractions {

    // Using page object patterns if the page is stable.
    @FindBy(css = "#sb_form_q")
    private WebElement searchBar;

    @FindBy(css = "#search_icon")
    private WebElement searchButton;

    public BingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openBingWebsite() {
        getWebDriver().get("https://www.bing.com");
        assertions().assertTitleContains("Bing");
    }

    public void searchFor(String searchQuery) {
        interactions().sendKeys(searchBar, searchQuery);
    }

    public void clickSearchButton() {
        elementFinder().waitForElementToBeVisible(searchButton);
        interactions().clickElement(searchButton);
    }
}
