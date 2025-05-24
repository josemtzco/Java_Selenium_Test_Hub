package com.jmtech.webpages.pinterest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.jmtech.core.ui.actions.BasePageObject;
import static com.jmtech.core.webdriver.WebDriverFactory.getWebDriver;

public class PinterestLoginPage extends BasePageObject {
    // Locators
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("[data-test-id='login-button']");
    private final By forgotPasswordLink = By.cssSelector("[data-test-id='forgot-password']");
    private final By signUpButton = By.cssSelector("[data-test-id='signup-button']");
    private final By errorMessage = By.cssSelector("[data-test-id='login-error']");
    private final String BASE_URL = "https://mx.pinterest.com/#login";

    public PinterestLoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        getWebDriver().get(BASE_URL);
        waitUtils().waitForSeconds(2);
    }

    public void enterEmail(String email) {
        waitUtils().waitForCondition(
            () -> elementFinder().findElement(emailInput).isDisplayed(), 
            5000, 
            500
        );
        interactions().sendKeys(elementFinder().findElement(emailInput), email);
    }

    public void enterPassword(String password) {
        interactions().sendKeys(elementFinder().findElement(passwordInput), password);
    }

    public void clickLoginButton() {
        interactions().clickElement(elementFinder().findElement(loginButton));
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void clickForgotPassword() {
        interactions().clickElement(elementFinder().findElement(forgotPasswordLink));
    }

    public void clickSignUp() {
        interactions().clickElement(elementFinder().findElement(signUpButton));
    }

    public String getErrorMessage() {
        return elementFinder().findElement(errorMessage).getText();
    }

    public boolean isErrorMessageDisplayed() {
        return elementFinder().findElement(errorMessage).isDisplayed();
    }

    public org.openqa.selenium.WebElement getErrorMessageElement() {
        return elementFinder().findElement(errorMessage);
    }
} 