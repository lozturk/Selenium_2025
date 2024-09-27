package org.properties;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class PropertiesReader {

    public Properties properties;

    public PropertiesReader(String fileName) {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        log.debug("Requested property key: {}, value: {}", key, value);
        return value;
    }
}