package com.company.tests;

import com.company.utilities.Configuration;
import com.company.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@Log4j2
public class BaseTest {

    protected WebDriver driver;
    protected String url, browser;

    @BeforeClass
    public void setUp(){
        browser = Configuration.getInstance().getProperty("browser");
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        url = Configuration.getInstance().getProperty("url");
        log.info("URL: {}", url);
        driver.get(url);
    }


    @AfterClass
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }





}
