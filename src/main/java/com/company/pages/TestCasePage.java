package com.company.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

// page_url = https://automationexercise.com/test_cases

@Log4j2
public class TestCasePage extends BasePage {
    public TestCasePage(WebDriver driver) {
        super(driver);
    }
}