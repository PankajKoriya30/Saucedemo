package com.learn.automation.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class ProductsPage extends BasePage{

    private By productsPageTitle = By.cssSelector(".title");
    private By backpackName = By.xpath("//div[contains(text(), 'Backpack')]");
    private By sortProductsDropdown = By.cssSelector(".product_sort_container");
    private By productPrices = By.xpath("//div[@data-test='inventory-list']//div[@class='inventory_item_price']");
    private By openCartLink = By.cssSelector(".shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductsPageTitle() {
        return getText(productsPageTitle);
    }

    public String getBackpackName() {
        return getText(backpackName);
    }

    public void sortProductsPriceLowToHigh() {
        selectValueFromDropdown(sortProductsDropdown, "lohi");
    }

    public List<WebElement> getProductPrices() {
        return getMultipleElements(productPrices);
    }

    public double getPriceOfFirstProduct() {
        List<WebElement> listOfProductPrices = getMultipleElements(productPrices);
        WebElement firstProductPrice = listOfProductPrices.get(0);
        String firstProdPrice = firstProductPrice.getText().substring(1);
        return Double.parseDouble(firstProdPrice);
    }

    public double getPriceOfSecondProduct() {
        List<WebElement> listOfProductPrices = getMultipleElements(productPrices);
        WebElement secondProductPrice = listOfProductPrices.get(1);
        String secondProdPrice = secondProductPrice.getText().substring(1);
        return Double.parseDouble(secondProdPrice);
    }

    public By addToCartButton(String productName){
        String formattedProductName = productName.toLowerCase().replace(" ", "-");
        return By.xpath("//div[@class='pricebar']/button[@id='add-to-cart-"+formattedProductName+"']");
    }
    public void clickProductAddToCartButton(String productName) {
        click(addToCartButton(productName));
    }

    public CartPage openShoppingCart() {
        click(openCartLink);
        return new CartPage(driver);
    }
}
