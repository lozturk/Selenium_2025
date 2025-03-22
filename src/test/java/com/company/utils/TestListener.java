package com.company.utils;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.ByteArrayInputStream;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Utilities.setTestResult(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("{} failed & getting screenshots...", result.getName());

        // get screenshot for testng html report
        TakesScreenshot driver = (TakesScreenshot) result.getTestContext().getAttribute(Constants.DRIVER);
        String screenshot = driver.getScreenshotAs(OutputType.BASE64);
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
        String htmlImage = String.format(htmlImageFormat, screenshot);
        Reporter.log(htmlImage);
        Utilities.setTestResult(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Handle success
        Utilities.setTestResult(result);
        log.info("{} passed successfully...", result.getName());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        Utilities.setTestResult(result);
        log.info("{} : skipped...", result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Utilities.clear();
    }

}
