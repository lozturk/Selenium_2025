package com.company.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Log4j2
public class DriverFactory {

    private static final Supplier<WebDriver> chromeSupplier = () -> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };

    private static final Supplier<WebDriver> firefoxSupplier = () -> {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };

    private static final Map<String, Supplier<WebDriver> > DRIVERPOOL = new HashMap<>();

    static {
        DRIVERPOOL.put("chrome",chromeSupplier);
        DRIVERPOOL.put("firefox",firefoxSupplier);
    }

    public static WebDriver getDriver(String browser){
        return DRIVERPOOL.get(browser).get();
    }





}
