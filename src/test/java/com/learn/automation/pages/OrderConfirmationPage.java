package com.learn.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage extends BasePage{

    private By orderConfirmationPageTitle = By.xpath("//span[contains(text(),'Complete')]");
    private By orderConfirmationMsg = By.xpath("//h2[@data-test='complete-header']");

    public OrderConfirmationPage(WebDriver driver){
        super(driver);
    }

    public String getOrderConfirmationPageTitle(){
        return getText(orderConfirmationPageTitle);
    }

    public String getOrderConfirmationMessage(){
        return  getText(orderConfirmationMsg);
    }
}
