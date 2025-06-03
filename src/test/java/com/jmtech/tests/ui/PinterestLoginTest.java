package com.jmtech.tests.ui;

import org.testng.annotations.Test;

import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;
import com.jmtech.webpages.pinterest.PinterestLoginPage;

@Test
public class PinterestLoginTest extends BaseUiTest {

    @Test
    public void testValidLogin() {
        PinterestLoginPage loginPage = new PinterestLoginPage(getWebDriver());
        loginPage.navigateToLoginPage();
        uiInteractions.assertions().assertTitleContains("Pinterest");
        loginPage.login("test@example.com", "validPassword123");
    }
} 