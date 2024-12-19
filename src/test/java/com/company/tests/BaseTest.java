package com.company.tests;

import com.company.utils.Configuration;
import com.company.utils.Constants;
import com.company.utils.DriverFactory;
import com.company.utils.Utilities;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import com.company.utils.PropertiesReader;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.time.Duration;

@Log4j2
public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    protected WebDriver driver;
    protected String browser, grid, testName, name,email,password,month,year,firstName,lastName,company,
            address,address2,country,state,city,zip,mobileNumber,product_1,product_2;
    protected String ENVIRONMENT,PATH_TO_PROPERTIES;
    protected PropertiesReader propertyReader;
    protected Faker faker;
    protected int day,selected_item_count;

    public void setDriver(WebDriver driverInstance) {
        threadDriver.set(driverInstance);
    }
    public static WebDriver getDriver() {
        log.info("Thread name : {}", Thread.currentThread().getName());
        log.info("Thread id : {}", Thread.currentThread().getId());
        return threadDriver.get();
    }


    @BeforeClass
    public void doBasics(){
        ENVIRONMENT = System.getProperty(Constants.TEST_ENVIRONMENT).toUpperCase();
        PATH_TO_PROPERTIES = Utilities.getPathToDataProperties(ENVIRONMENT);
        propertyReader = new PropertiesReader(PATH_TO_PROPERTIES);
        faker = new Faker();
    }


    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestResult testResult) throws MalformedURLException {
        browser = Configuration.getInstance().getProperty(Constants.BROWSER);
        grid = Configuration.getInstance().getProperty(Constants.GRID);
        if (grid.contains(Constants.ENABLED)) {
            log.info("Setting up the remote Webdriver for browser: {}", browser);
            setDriver(DriverFactory.getRemoteDriver(browser));
        } else {
            log.info("Setting up the local Webdriver for browser: {}", browser);
            setDriver(DriverFactory.getDriver(browser));
        }
        driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        testName = testResult.getMethod().getMethodName();
        log.info("Starting test: {} in environment: {}", testName, ENVIRONMENT);
    }

    @AfterMethod(alwaysRun = true)
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
    };

}
