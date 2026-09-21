package com.learn.automation.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {

    private static Properties properties;

    public static void loadProperties(String testData){
        properties = new Properties();
        try{
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testdata\\"
                + testData +".properties");
        properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load test data file: "
                     + testData, e);
        }
    }

    public static String getTestData(String key){
        return properties.getProperty(key);
    }
}
