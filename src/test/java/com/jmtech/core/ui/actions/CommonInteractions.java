package com.jmtech.core.ui.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class CommonInteractions {

    private final Actions actions;
    private final JavascriptExecutor jsExecutor;

    public CommonInteractions(WebDriver webDriver) {
        this.actions = new Actions(webDriver);
        this.jsExecutor = (JavascriptExecutor) webDriver;
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void clickElementUsingJavaScript(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);

    }

    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void scrollIntoView(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

}
