package com.company.tests;

import com.company.utils.*;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;

import org.openqa.selenium.*;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.time.Duration;

@Log4j2
@Listeners({TestListener.class})
public abstract class BaseTest {

    protected WebDriver driver;
    protected String browser, testName, name, email, password, month, year, firstName, lastName, company,
            address, address2, country, state, city, zip, mobileNumber, product_1, product_2, invalidEmail, invalidPassword;
    protected String environment, pathToProperties;
    protected PropertyReader propertyReader;
    protected Faker faker;
    protected int day, selected_item_count;

    @BeforeTest(alwaysRun = true)
    public void setUp(ITestContext iTestContext) throws MalformedURLException {
        faker = new Faker();
        environment = System.getProperty(Constants.TEST_ENVIRONMENT).trim();
        log.info("Environment : {}", environment);
        pathToProperties = Utilities.getPathToDataProperties(environment);
        log.info("Path To Properties : {}", pathToProperties);
        propertyReader = new PropertyReader(pathToProperties);
        browser = System.getProperty(Constants.BROWSER);
        driver = Boolean.parseBoolean(Config.getInstance().getProperty(Constants.GRID_ENABLED)) ? getRemoteDriver(browser) : getLocalDriver(browser);
        iTestContext.setAttribute(Constants.DRIVER, driver);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.DEFAULT_IMPLICIT_WAIT));
        testName = iTestContext.getCurrentXmlTest().getName();
        log.info("Starting test: {} in environment: {}", testName, environment);
    }

    private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
        return DriverFactory.getRemoteDriver(browser);
    }

    private WebDriver getLocalDriver(String browser) {
        return DriverFactory.getLocalDriver(browser);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        try {
            ITestResult result = Utilities.getTestResult();
            if (result != null && result.getStatus() == ITestResult.FAILURE) {
                Allure.addAttachment(Constants.SCREENSHOT, new ByteArrayInputStream(
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                log.info("Screenshot taken for Allure report.");
            }
            if (driver != null) {
                driver.quit();
                log.info(Constants.DRIVER_QUIT_MESSAGE);
            }
        } catch (WebDriverException e) {
            log.error("WebDriverException: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
    }

    protected String getPropertyValue(String key) {
        if (propertyReader == null) {
            log.error("PropertyReader is not initialized!");
            throw new IllegalStateException("PropertyReader is not initialized!");
        }
        return propertyReader.getProperty(key);
    }
}
