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
import org.testng.collections.CollectionUtils;

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


    @BeforeClass
    public void doBasics(){
        ENVIRONMENT = System.getProperty(Constants.TEST_ENVIRONMENT).toUpperCase();
        PATH_TO_PROPERTIES = Utilities.getPathToDataProperties(ENVIRONMENT);
        propertyReader = new PropertiesReader(PATH_TO_PROPERTIES);
        faker = new Faker();
    }


    @BeforeMethod
    public void setUp(ITestResult iTestResult) throws MalformedURLException {
        browser = System.getProperty(Constants.BROWSER);
        if (Boolean.getBoolean("selenium.grid.enabled")) {
            log.info("Setting up the remote Webdriver for browser: {}", browser);
            driver = getRemoteDriver(browser);
        } else {
            log.info("Setting up the local Webdriver for browser: {}", browser);
            driver = getLocalDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        testName = iTestResult.getMethod().getMethodName();
        log.info("Starting test: {} in environment: {}", testName, ENVIRONMENT);
    }

    private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
        return DriverFactory.getRemoteDriver(browser);
    }

    private WebDriver getLocalDriver() {
        return DriverFactory.getDriver(browser);
    }


    @AfterMethod
    public void tearDown(ITestResult iTestResult){

        try {

            if (iTestResult.getStatus() == ITestResult.FAILURE){
                Allure.addAttachment("Screenshot",new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                log.info("{} failed...", testName);
            }

            if (iTestResult.getStatus() == ITestResult.SUCCESS) {
                log.info("{} passed successfully...", testName);
            }
            if (driver != null){
                driver.quit();
                log.info("Closed the driver gracefully....");
            }


        } catch (Exception e) {
            log.error(e.getMessage());
        }  finally {
            log.info("Running finally...");
            if ((driver != null && iTestResult.getStatus() == ITestResult.FAILURE )
                    || (driver != null && getTestExecutionTime(iTestResult) >= 90))
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
