package com.learn.automation.driver.tests;

import com.learn.automation.driver.pages.CartPage;
import com.learn.automation.driver.pages.LoginPage;
import com.learn.automation.driver.pages.ProductsPage;
import com.learn.automation.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyAddProductToCart extends BaseTest{

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @Test(groups = {"smoke","regression"})
    public void verifyAddToCart() {

        loginPage = new LoginPage(driver);
        loginPage.enterUsername(TestDataReader.getTestData("username"));
        loginPage.enterPassword(TestDataReader.getTestData("password"));
        productsPage = loginPage.clickLogin();

        Assert.assertTrue(productsPage.getProductsPageTitle().equalsIgnoreCase("Products"),
                "Products page is not displaying");
        productsPage.clickProductAddToCartButton(TestDataReader.getTestData("backpack"));
        productsPage.clickProductAddToCartButton(TestDataReader.getTestData("bikelight"));

        cartPage = productsPage.openShoppingCart();
        Assert.assertTrue(cartPage.getCartPageTitle().equalsIgnoreCase("Your Cart"),
                "Cart page is not displayed");

        Assert.assertTrue(cartPage.getCartProductNames().contains(TestDataReader.getTestData("backpack")),
                "Backpack is present on the cart page.");
        Assert.assertTrue(cartPage.getCartProductNames().contains(TestDataReader.getTestData("bikelight")),
                "Bike light is present on the cart page.");

        Assert.assertEquals(cartPage.getCartProductNames().size(), 2,
                "cart contains exactly 2 products");
    }
}
