package com.learn.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{

    private By cartPageTitle = By.xpath("//span[@data-test='title' and text()='Your Cart']");
    private By allCartProducts = By.xpath("//div[@data-test='cart-list']//a");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver){
        super(driver);
    }

    public String getCartPageTitle()
    {
        return getText(cartPageTitle);
    }

    public List<String> getCartProductNames(){
        List<WebElement> cartProducts = getMultipleElements(allCartProducts);
        List<String> cartProductNames = new ArrayList<>();
        for(WebElement product: cartProducts){
            cartProductNames.add(product.getText());
        }
        return cartProductNames;
    }

    public By removeButton(String productName)
    {
        String formattedProductName = productName.toLowerCase().replace(" ", "-");
        return By.id("remove-"+formattedProductName);
    }
    public void removeProductFromCart(String product) {
        click(removeButton(product));
    }

    public CheckoutInfoPage clickCheckout()
    {
        click(checkoutButton);
        return new CheckoutInfoPage(driver);
    }
}
