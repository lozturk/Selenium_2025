package com.company.utilities;

import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Log4j2
public class Configuration {

    private static Map<String, String> props = null;
    private static final String ROOT_KEY_ENV = (System.getProperty("test.environment") == null || System.getProperty("test.environment").isEmpty()) ? "dev" : System.getProperty("test.environment");
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
        InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("config.yaml");
        return new Yaml().load(inputStream);

    }
}
