package com.imc.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
    public static String getBrowser() {
        String sysProp = System.getProperty("browser");
        return (sysProp != null && !sysProp.isEmpty())
                ? sysProp
                : properties.getProperty("browser");
    }
}

