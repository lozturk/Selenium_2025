package com.company.utilities;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Log4j2
public class DriverFactory {

    private static final Supplier<WebDriver> chromeSupplier = ChromeDriver::new;
    private static final Supplier<WebDriver> firefoxSupplier = FirefoxDriver::new;
    private static final Supplier<WebDriver> edgeSupplier = EdgeDriver::new;


    private static final Map<String, Supplier<WebDriver> > DRIVER_POOL = new HashMap<>();

    static {
        DRIVER_POOL.put("chrome",chromeSupplier);
        DRIVER_POOL.put("firefox",firefoxSupplier);
        DRIVER_POOL.put("edge",edgeSupplier);
    }

    public static WebDriver getDriver(String browser){
        return DRIVER_POOL.get(browser).get();
    }





}
