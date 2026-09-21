package com.learn.automation.driver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage{

    private By checkoutOverviewPageTitle = By.xpath("//span[@data-test='title' and contains(text(),'Overview')]");
    private By checkoutProductNames = By.cssSelector(".inventory_item_name");
    private By itemTotal = By.cssSelector("div[data-test=subtotal-label]");
    private By tax = By.cssSelector("div[data-test=tax-label]");
    private By total = By.xpath("//div[@data-test='total-label']");
    private By finishButton = By.cssSelector("button[data-test=finish]");

    public CheckoutOverviewPage(WebDriver driver){
        super(driver);
    }

    public String getCheckoutOverviewPageTitle(){
        return getText(checkoutOverviewPageTitle);
    }

    public List<String> getCheckedOutProductNames(){

        List<WebElement> listOfProducts = getMultipleElements(checkoutProductNames);
        List<String> productNames = new ArrayList<>();
        for(WebElement product: listOfProducts){
            productNames.add(product.getText());
        }
        return productNames;
    }

    public double getItemTotal(){
        String itemTotalValue = getText(itemTotal).split("\\$")[1];
        return Double.parseDouble(itemTotalValue);
    }

    public double getTax(){
        String taxValue = getText(tax).split("\\$")[1];
        return Double.parseDouble(taxValue);
    }

    public double getTotal(){
        String totalAmount = getText(total).split("\\$")[1];
        return Double.parseDouble(totalAmount);
    }

    public OrderConfirmationPage clickFinish(){
        click(finishButton);
        return new OrderConfirmationPage(driver);
    }
}
