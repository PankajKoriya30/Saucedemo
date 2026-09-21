package com.learn.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static void loadProperties(String environment){
        properties = new Properties();
        try{
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\config\\"
                + environment +".properties");
        properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration file: "
                     + environment, e);
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
