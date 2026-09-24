package com.learn.automation.cucumber.stepdefinitions;

import com.learn.automation.driver.DriverFactory;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.TestDataReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp()
    {
        ConfigReader.loadProperties("qa");
        TestDataReader.loadProperties("testdata");
        DriverFactory.createDriver(ConfigReader.getProperty("browser"));
    }

    @After
    public void tearDown(){
        DriverFactory.quitDriver();
    }

}
