package com.company.utils;


import lombok.extern.log4j.Log4j2;
import org.testng.ITestResult;

@Log4j2
public class Utilities {


    protected static String pathToDataProperties;

    public static String getPathToDataProperties(String environment) {

        switch (environment) {
            case "test":
                pathToDataProperties = Constants.PATH_TO_TEST_DATA_PROPERTIES;
                break;
            case "stage":
                pathToDataProperties = Constants.PATH_TO_STAGE_DATA_PROPERTIES;
                break;
            default:
                pathToDataProperties = Constants.PATH_TO_DEV_DATA_PROPERTIES;
        }
        return pathToDataProperties;
    }

    protected static long getTestExecutionTime(ITestResult testResult){
        long startTime = testResult.getStartMillis();
        long endTime = testResult.getEndMillis();
        long executionTimeSeconds =  (endTime - startTime) / 1000;
        log.info("Test execution time: {} seconds", executionTimeSeconds);
        return executionTimeSeconds;
    }

    private static ThreadLocal<ITestResult> testResult = new ThreadLocal<>();

    public static void setTestResult(ITestResult result) {
        testResult.set(result);
    }

    public static ITestResult getTestResult() {
        return testResult.get();
    }

    public static void clear() {
        testResult.remove();
    }












}
