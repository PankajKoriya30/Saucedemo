package com.learn.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver createDriver(String browser){

        if (browser==null || browser.trim().isEmpty()){
            throw new IllegalArgumentException("Browser is not configured.");
        }

        switch (browser.trim().toLowerCase()){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();

                // Disable Chrome password manager
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver(String browser)
    {
        createDriver(browser);
        return driver;
    }
    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
