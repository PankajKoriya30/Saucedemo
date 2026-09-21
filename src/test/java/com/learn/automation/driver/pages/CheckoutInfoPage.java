package com.learn.automation.driver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage{

    private By checkoutPageTitle = By.xpath("//span[@data-test='title' and contains(text(),'Checkout')]");
    private By firstNameTextBox = By.id("first-name");
    private By lastNameTextBox = By.id("last-name");
    private By zipCodeTextBox = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessage = By.xpath("//h3[@data-test='error']");

    public CheckoutInfoPage(WebDriver driver){
        super(driver);
    }

    public String getCheckoutPageTitle(){
        return getText(checkoutPageTitle);
    }

    public void enterFirstName(String firstName){
        type(firstNameTextBox, firstName);
    }

    public void enterLastName(String lastName){
        type(lastNameTextBox, lastName);
    }

    public void enterZipCode(String zipcode){
        type(zipCodeTextBox, zipcode);
    }

    public CheckoutOverviewPage clickContinue(){
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public String getErrorMessage() {
        return  getText(errorMessage);
    }
}
