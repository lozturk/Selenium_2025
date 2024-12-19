package com.company.utils;

import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Log4j2
public class Configuration {

    private static Map<String, String> props = null;
    private static final String ROOT_KEY_ENV = (System.getProperty(Constants.TEST_ENVIRONMENT) == null ||
            System.getProperty(Constants.TEST_ENVIRONMENT).isEmpty()) ? Constants.DEV : System.getProperty(Constants.TEST_ENVIRONMENT);
    private static Configuration instance = null;

    private Configuration() throws IOException {
        synchronized (Configuration.class) {
            props = loadConfig().get(ROOT_KEY_ENV);
        }
    }

    public static Configuration getInstance() {
        if (instance == null) {
            try {
                instance = new Configuration();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return instance;
    }

    public String getProperty(String prop) {
        return props.get(prop);
    }

    public Map<String, Map<String, String>> loadConfig() {
        InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(Constants.CONFIG_YAML);
        return new Yaml().load(inputStream);

    }
}
