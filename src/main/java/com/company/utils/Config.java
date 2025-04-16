package com.company.utils;

import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Log4j2
public class Config {

    private static Map<String, String> props = null;
    private static final String ROOT_KEY_ENV = (System.getProperty(Constants.TEST_ENVIRONMENT) == null ||
            System.getProperty(Constants.TEST_ENVIRONMENT).isEmpty()) ? Constants.DEV : System.getProperty(Constants.TEST_ENVIRONMENT);
    private static Config instance = null;

    private Config() throws IOException {
        synchronized (Config.class) {
            props = loadConfig().get(ROOT_KEY_ENV);

            // Check for any override
            for (Map.Entry<String, String> entry : props.entrySet()) {
                if (System.getProperties().containsKey(entry.getKey())) {
                    props.put(entry.getKey(), System.getProperty(entry.getKey()));
                }
            }

            // Print config data
            log.info("------------------------");
            for (Map.Entry<String, String> entry : props.entrySet()) {
                log.info("{}: {}", entry.getKey(), entry.getValue());
            }
            log.info("------------------------");
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            try {
                instance = new Config();
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
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(Constants.CONFIG_YAML);
        return new Yaml().load(inputStream);
    }
}