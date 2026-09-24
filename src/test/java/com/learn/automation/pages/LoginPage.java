package com.learn.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private By usernameTxtbox = By.id("user-name");
    private By passwordTxtbox = By.cssSelector("[data-test=password]");
    private By loginBtn = By.cssSelector("#login-button");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void enterUsername(String username){
        clear(usernameTxtbox);
        type(usernameTxtbox, username);
    }
    public void enterPassword(String password){
        clear(passwordTxtbox);
        type(passwordTxtbox, password);
    }
    public ProductsPage clickLogin(){
        click(loginBtn);
        return new ProductsPage(driver);
    }
}
