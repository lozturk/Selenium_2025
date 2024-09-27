package com.company.pages;


import com.company.utilities.Configuration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

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

}
