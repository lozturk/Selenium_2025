package com.company.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * BasePage is an abstract class that serves as a base for all page objects in a Selenium WebDriver test framework.
 * It provides common functionality and utilities that can be used across different page objects.
 */
@Log4j2
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait webDriverWait;
    protected final FluentWait<WebDriver> fluentWait;

    @FindBy(xpath = "//button[text() = 'Continue Shopping']")
    protected WebElement continueShoppingButton;

    /**
     * Constructor to initialize WebDriver, WebDriverWait, and FluentWait, and initialize the web elements.
     *
     * @param driver the WebDriver instance to interact with the web browser
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        if (this.driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null.");
        }
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver, this);
    }

    /**
     * Opens the specified URL in the web browser.
     *
     * @param url the URL to open
     */
    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Returns a JavascriptExecutor instance for executing JavaScript in the context of the currently selected frame or window.
     *
     * @return a JavascriptExecutor instance
     */
    public JavascriptExecutor getJS() {
        return (JavascriptExecutor) driver;
    }

    /**
     * Clicks on the "Continue Shopping" button.
     */
    public void clickOnContinueShoppingButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(continueShoppingButton)).isDisplayed();
        continueShoppingButton.click();
        log.info("Clicked on Continue Shopping button successfully");
    }

    /**
     * Returns an Actions instance for performing complex user interactions.
     *
     * @return an Actions instance
     */
    public Actions getActions() {
        return new Actions(driver);
    }

    /**
     * Waits until the URL contains the specified page.
     *
     * @param page the page to wait for in the URL
     */
    public void waitForUrlContainsSpecificPage(String page) {
        webDriverWait.until(ExpectedConditions.urlContains(page));
    }

    /**
     * Waits until the document state is "complete".
     */
    public void waitForDocumentState() {
        webDriverWait.until(driver -> Objects.equals(getJS().executeScript("return document.readyState"), "complete"));
    }
}