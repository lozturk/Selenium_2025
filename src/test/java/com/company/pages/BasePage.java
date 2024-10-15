package com.company.pages;


import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

@Log4j2
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    @FindBy(xpath = "//button[text() = 'Continue Shopping']")
    protected WebElement continueShoppingButton;

    // Constructor to initialize WebDriver and WebDriverWait
    public BasePage(WebDriver driver) {
        this.driver = driver;
        if (this.driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null.");
        }
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Common methods accessible to all pages can be added here
    public void openUrl(String url) {
        driver.get(url);
    }

    public JavascriptExecutor getJS() {
        return (JavascriptExecutor) driver;
    }

    public void clickOnContinueShoppingButton(){
        webDriverWait.until(ExpectedConditions.visibilityOf(continueShoppingButton)).isDisplayed();
        continueShoppingButton.click();
        log.info("Clicked on Continue Shopping button successfully");
    }

    public Actions getActions() {
        return new Actions(driver);
    }

    public void waitForUrlContainsSpecificPage(String page) {
        webDriverWait.until(ExpectedConditions.urlContains(page));
    }

    public void waitForDocumentState(){
        webDriverWait.until(driver -> Objects.equals(getJS().executeScript("return document.readyState"), "complete"));
    }
}
