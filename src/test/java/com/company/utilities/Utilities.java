package com.company.utilities;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class Utilities {


    protected static final String PATH_TO_DEV_DATA_PROPERTIES = "dev_data.properties";
    protected static final String PATH_TO_TEST_DATA_PROPERTIES = "test_data.properties";
    protected static final String PATH_TO_STAGE_DATA_PROPERTIES = "stage_data.properties";
    protected static String pathToDataProperties;



    public static String getPathToDataProperties(String environment) {

        switch (environment) {
            case "test":
                pathToDataProperties = PATH_TO_TEST_DATA_PROPERTIES;
                break;
            case "stage":
                pathToDataProperties = PATH_TO_STAGE_DATA_PROPERTIES;
                break;
            default:
                pathToDataProperties = PATH_TO_DEV_DATA_PROPERTIES;
        }
        return pathToDataProperties;
    }











}
