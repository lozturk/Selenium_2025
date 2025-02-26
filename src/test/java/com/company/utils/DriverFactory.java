package com.company.utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Log4j2
public class DriverFactory {

    public static Capabilities capabilities;

    private static final Supplier<WebDriver> chromeSupplier = ChromeDriver::new;
    private static final Supplier<WebDriver> firefoxSupplier = FirefoxDriver::new;
    private static final Supplier<WebDriver> edgeSupplier = EdgeDriver::new;


    private static final Map<String, Supplier<WebDriver> > DRIVER_POOL = new HashMap<>();

    static {
        DRIVER_POOL.put(Constants.CHROME,chromeSupplier);
        DRIVER_POOL.put(Constants.FIREFOX,firefoxSupplier);
        DRIVER_POOL.put(Constants.EDGE,edgeSupplier);
    }

    public static WebDriver getLocalDriver(String browser){
        log.info("Setting up the local Webdriver for browser: {}", browser);
        return DRIVER_POOL.get(browser).get();
    }

    public static WebDriver getRemoteDriver(String browser) throws MalformedURLException {
        log.info("Setting up the remote Webdriver for browser: {}", browser);
        switch(browser) {
            case Constants.CHROME:
                capabilities = new ChromeOptions();
                break;
            case Constants.FIREFOX:
                capabilities = new FirefoxOptions();
                break;
            case Constants.EDGE:
                capabilities = new EdgeOptions();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        String urlFormat = Config.getInstance().getProperty(Constants.GRID_URL_FORMAT);
        String hubHost = Config.getInstance().getProperty(Constants.GRID_HUB_HOST);
        String seleniumGridUrl = String.format(urlFormat, hubHost);
        log.info( "selenium grid url : {} ", seleniumGridUrl);
        return new RemoteWebDriver(new URL(seleniumGridUrl), capabilities);
    }





}
