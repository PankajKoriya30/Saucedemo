package com.learn.automation.driver.pages;

import com.learn.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    protected void click(By locator){
        waitUtils.waitForElementClickable(locator);
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text){
        waitUtils.waitForElementVisible(locator);
        driver.findElement(locator).sendKeys(text);
    }

    protected String getText(By locator){
        waitUtils.waitForElementVisible(locator);
        return driver.findElement(locator).getText();
    }

    protected boolean isDisplayed(By locator){
        waitUtils.waitForElementVisible(locator);
        return driver.findElement(locator).isDisplayed();
    }

    protected void clear(By locator){
        waitUtils.waitForElementVisible(locator);
        driver.findElement(locator).clear();
    }

    protected void selectValueFromDropdown(By locator, String value){
        waitUtils.waitForElementVisible(locator);
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    protected List<WebElement> getMultipleElements(By locator){
        return driver.findElements(locator);
    }
}
