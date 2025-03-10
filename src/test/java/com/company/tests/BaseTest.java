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
    protected String browser,testName, name,email,password,month,year,firstName,lastName,company,
            address,address2,country,state,city,zip,mobileNumber,product_1,product_2;
    protected String environment, pathToProperties;
    protected PropertiesReader propertyReader;
    protected Faker faker;
    protected int day,selected_item_count;
    protected static Config config;

    @BeforeSuite
    public static void setup() {
        config = Config.getInstance(); // Load and log config once before all tests
    }

    @BeforeClass
    public void doBasics(){
        environment = System.getProperty(Constants.TEST_ENVIRONMENT).toUpperCase();
        pathToProperties = Utilities.getPathToDataProperties(environment);
        propertyReader = new PropertiesReader(pathToProperties);
        faker = new Faker();
    }


    @BeforeMethod
    public void setUp(ITestResult iTestResult, ITestContext iTestContext) throws MalformedURLException {
        browser = System.getProperty(Constants.BROWSER);
        driver = Boolean.parseBoolean(config.getProperty(Constants.GRID_ENABLED)) ? getRemoteDriver(browser) : getLocalDriver(browser);
        iTestContext.setAttribute(Constants.DRIVER, driver);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        testName = iTestResult.getMethod().getMethodName();
        log.info("Starting test: {} in environment: {}", testName, environment);
    }

    private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
        return DriverFactory.getRemoteDriver(browser);
    }

    private WebDriver getLocalDriver(String browser) {
        return DriverFactory.getLocalDriver(browser);
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
