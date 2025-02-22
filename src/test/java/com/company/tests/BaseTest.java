package com.company.tests;

import com.company.utils.Configuration;
import com.company.utils.Constants;
import com.company.utils.DriverFactory;
import com.company.utils.Utilities;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;

import com.company.utils.PropertiesReader;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.time.Duration;

@Log4j2
public abstract class BaseTest {

    
    protected WebDriver driver;
    protected String browser,testName, name,email,password,month,year,firstName,lastName,company,
            address,address2,country,state,city,zip,mobileNumber,product_1,product_2;
    protected String ENVIRONMENT,PATH_TO_PROPERTIES;
    protected PropertiesReader propertyReader;
    protected Faker faker;
    protected int day,selected_item_count;




    @Ignore
    @BeforeClass
    public void doBasics(){
        ENVIRONMENT = System.getProperty(Constants.TEST_ENVIRONMENT).toUpperCase();
        PATH_TO_PROPERTIES = Utilities.getPathToDataProperties(ENVIRONMENT);
        propertyReader = new PropertiesReader(PATH_TO_PROPERTIES);
        faker = new Faker();
    }


    @BeforeTest
    public void setUp(ITestContext testContext) throws MalformedURLException {
        ENVIRONMENT = System.getProperty(Constants.TEST_ENVIRONMENT).toUpperCase();
        PATH_TO_PROPERTIES = Utilities.getPathToDataProperties(ENVIRONMENT);
        propertyReader = new PropertiesReader(PATH_TO_PROPERTIES);

        browser = System.getProperty(Constants.BROWSER);
        if (Boolean.getBoolean("selenium.gird.enabled")) {
            log.info("Setting up the remote Webdriver for browser: {}", browser);
            driver = getRemoteDriver(browser);
        } else {
            log.info("Setting up the local Webdriver for browser: {}", browser);
            driver = getLocalDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        testName = testContext.getCurrentXmlTest().getName();
        log.info("Starting test: {} in environment: {}", testName, ENVIRONMENT);
        faker = new Faker();
    }

    private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
        return DriverFactory.getRemoteDriver(browser);
    }

    private WebDriver getLocalDriver() {
        return DriverFactory.getDriver(browser);
    }


    @AfterTest
    public void tearDown(ITestResult testResult){

        try {

            if (testResult.getStatus() == ITestResult.FAILURE){
                Allure.addAttachment("Screenshot",new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                log.info("{} failed...", testName);
            }

            if (testResult.getStatus() == ITestResult.SUCCESS) {
                log.info("{} passed successfully...", testName);
            }

            if (driver != null)
                driver.quit();

        } catch (Exception e) {
            log.error(e.getMessage());
        }  finally {
            log.info("Running finally...");
            if ((driver != null && testResult.getStatus() == ITestResult.FAILURE )
                    || (driver != null && getTestExecutionTime(testResult) >= 90))
                driver.quit();
        }
    }




    protected String getPropertyValue(String key){
        if (propertyReader == null){
            log.error("PropertyReader is not initialized!");
            return null;
        }
        return propertyReader.getProperty(key);
    }

    protected long getTestExecutionTime(ITestResult testResult){
        long startTime = testResult.getStartMillis();
        long endTime = testResult.getEndMillis();
        long executionTimeSeconds =  (endTime - startTime) / 1000;
        log.info("Test execution time: {} seconds", executionTimeSeconds);
        return executionTimeSeconds;
    }

}
