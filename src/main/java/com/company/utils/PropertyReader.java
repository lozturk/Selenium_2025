package com.company.utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class PropertyReader {

    public Properties properties;

    public PropertyReader(String fileName) {
        properties = new Properties();
        log.info("Reading properties from file {}", fileName);
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return;
            }
            properties.load(input);
            log.info("Successfully loaded property file: {}", fileName);
        } catch (IOException ex) {
            log.error("Error occurred while loading property file: {}", fileName, ex);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        log.info("Requested property key: {}, value: {}", key, value);
        return value;
    }
}