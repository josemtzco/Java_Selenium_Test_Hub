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
        loginPage.login("test@example.com", "validPassword123");
        // Add assertions for successful login
    }

} 