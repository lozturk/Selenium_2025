package com.company.tests;

import com.company.utilities.Configuration;
import com.company.utilities.DriverFactory;
import com.company.utilities.Utilities;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.properties.PropertiesReader;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.ByteArrayInputStream;
import java.time.Duration;

@Log4j2
public class BaseTest {

    protected WebDriver driver;
    protected String browser, testName;
    protected static String ENVIRONMENT,PATH_TO_PROPERTIES;
    protected static final String NAME = "name";
    protected static final String EMAIL = "email";
    protected PropertiesReader propertyReader;
    protected Faker faker;
    protected String name,email,password,month,year,firstName,lastName,company,address,address2,country,state,city,zip,mobileNumber;
    protected int day;


    @BeforeClass
    public void doBasics(){
        ENVIRONMENT = System.getProperty("test.environment");
        log.info("ENVIRONMENT: {}", ENVIRONMENT);
        PATH_TO_PROPERTIES = Utilities.getPathToDataProperties(ENVIRONMENT);
        propertyReader = new PropertiesReader(PATH_TO_PROPERTIES);
        faker = new Faker();
    }


    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestResult testResult){
        browser = Configuration.getInstance().getProperty("browser");
        log.info("Setting up the driver for browser: {}", browser);
        driver = DriverFactory.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        testName = testResult.getMethod().getMethodName();
        log.info("Starting test: {} in environment: {}", testName, ENVIRONMENT);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult){
        if (testResult.getStatus() == ITestResult.FAILURE){
            Allure.addAttachment("Screenshot",new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            log.info("{} failed...", testName);
        }

        if (testResult.getStatus() == ITestResult.SUCCESS) {
            log.info("{} passed successfully...", testName);
        }

        if (driver != null)
            driver.quit();

        getTestExecutionTime(testResult);

    }

    protected String getPropertyValue(String key){
        if (propertyReader == null){
            log.error("PropertyReader is not initialized");
            return null;
        }
        return propertyReader.getProperty(key);
    }

    protected void getTestExecutionTime(ITestResult testResult){
        long startTime = testResult.getStartMillis();
        long endTime = testResult.getEndMillis();
        long executionTimeMillis =  (endTime - startTime) / 1000;
        log.info("Test execution time: {} seconds", executionTimeMillis);
    };


}
