package com.learn.automation.driver.tests;

import com.learn.automation.driver.DriverFactory;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup(){

        ConfigReader.loadProperties("qa");
        TestDataReader.loadProperties("testdata");
        driver = DriverFactory.getDriver(ConfigReader.getProperty("browser"));
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
