package com.learn.automation.tests;

import com.learn.automation.driver.DriverFactory;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(String browser){

        ConfigReader.loadProperties("qa");
        TestDataReader.loadProperties("testdata");
        DriverFactory.createDriver(browser);
        driver = DriverFactory.getDriver();
        System.out.println(
                "Thread: " + Thread.currentThread().getId()
                        + " | Browser: " + browser
                        + " | Driver: " + driver
        );
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
