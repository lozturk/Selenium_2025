package com.company.tests;

import com.company.utilities.Configuration;
import com.company.utilities.DriverFactory;
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

@Log4j2
public class BaseTest {

    protected WebDriver driver;
    protected String browser, testName;
    protected static String PATH_TO_DEV_DATA_PROPERTIES = "dev_data.properties";
    protected PropertiesReader propertyReader;
    protected Faker faker;

    @BeforeClass
    public void doBasics(){
        propertyReader = new PropertiesReader(PATH_TO_DEV_DATA_PROPERTIES);
        faker = new Faker();
    }


    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestResult testResult){
        log.info("Setting up the driver.");
        browser = Configuration.getInstance().getProperty("browser");
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        testName = testResult.getMethod().getMethodName();
        log.info("Starting test: {}", testName);
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
    }

    protected String getPropertyValue(String key){
        if (propertyReader == null){
            log.error("PropertyReader is not initialized");
            return null;
        }
        return propertyReader.getProperty(key);
    }






}
