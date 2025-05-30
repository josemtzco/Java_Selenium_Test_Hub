package com.jmtech.webpages.bing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.jmtech.core.ui.actions.UIInteractions;
import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;

public class BingPage extends UIInteractions {

    //Using page object patterns if the page is stable.
    @FindBy(css = "sb_form_q")
    private WebElement searchBar;

    public BingPage(WebDriver driver) {
        super(driver);
    }

    public void openBingWebsite(){
        getWebDriver().get("https://www.bing.com");
        assertions().assertTitleContains("Bing");
    }

    public void searchFor(String searchQuery){
        interactions().sendKeys(searchBar, searchQuery);
    }
}
