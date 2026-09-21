package com.learn.automation.driver.tests;

import com.learn.automation.driver.pages.*;
import com.learn.automation.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyCheckoutFlow extends BaseTest{

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutInfoPage checkoutInfoPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private OrderConfirmationPage orderConfirmationPage;

    @Test
    public void verifyCheckoutE2EFlow() {

        //Log in to application
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(TestDataReader.getTestData("username"));
        loginPage.enterPassword(TestDataReader.getTestData("password"));
        productsPage = loginPage.clickLogin();

        // Varifying Products page title
        Assert.assertTrue(productsPage.getProductsPageTitle().equalsIgnoreCase("Products"),
                "Products page is not displaying.");

        // Adding two products to cart
        productsPage.clickProductAddToCartButton(TestDataReader.getTestData("backpack"));
        productsPage.clickProductAddToCartButton(TestDataReader.getTestData("bikelight"));

        // Opening the cart page
        cartPage = productsPage.openShoppingCart();
        Assert.assertTrue(cartPage.getCartPageTitle().equalsIgnoreCase("Your Cart"),
                "Cart page is not displaying.");

        // Verifying two products on cart page
        List<String> cartProducts = cartPage.getCartProductNames();
        Assert.assertTrue(cartProducts.contains(TestDataReader.getTestData("backpack")),
                "Sauce Labs Backpack is not available on cart page.");

        Assert.assertTrue(cartProducts.contains(TestDataReader.getTestData("bikelight")),
                "Sauce Labs Bike Light is not available on cart page.");

        // Verifying navigation to checkout information page
        checkoutInfoPage = cartPage.clickCheckout();
        Assert.assertTrue(checkoutInfoPage.getCheckoutPageTitle().equalsIgnoreCase("Checkout: Your Information"),
                "Checkout information page is not displaying.");

        // Adding information and navigating to checkout overview page
        checkoutInfoPage.enterFirstName(TestDataReader.getTestData("firstname"));
        checkoutInfoPage.enterLastName(TestDataReader.getTestData("lastname"));
        checkoutInfoPage.enterZipCode(TestDataReader.getTestData("zipcode"));
        checkoutOverviewPage = checkoutInfoPage.clickContinue();

        // Validating overview page title, two products, Item total, tax and grand total
        Assert.assertTrue(checkoutOverviewPage.getCheckoutOverviewPageTitle().equalsIgnoreCase("Checkout: Overview"),
                "Checkout overview page is not displaying.");
        Assert.assertTrue(checkoutOverviewPage.getCheckedOutProductNames().contains(TestDataReader.getTestData("backpack")),
                "Sauce Labs Backpack is not available on cart overview page.");
        Assert.assertTrue(checkoutOverviewPage.getCheckedOutProductNames().contains(TestDataReader.getTestData("bikelight")),
                "Sauce Labs Bike Light is not available on cart overview page.");
        Assert.assertEquals(checkoutOverviewPage.getItemTotal(), Double.parseDouble(TestDataReader.getTestData("itemtotal")), "Item Total is not matching.");
        Assert.assertEquals(checkoutOverviewPage.getTax(), Double.parseDouble(TestDataReader.getTestData("tax")), "Tax amount is not matching.");
        Assert.assertEquals(checkoutOverviewPage.getTotal(),
                checkoutOverviewPage.getItemTotal() + checkoutOverviewPage.getTax(),
                "Total amount is not matching.");

        // Navigating to order confirmation page and validating title and message
        orderConfirmationPage = checkoutOverviewPage.clickFinish();
        Assert.assertEquals(orderConfirmationPage.getOrderConfirmationPageTitle(), "Checkout: Complete!",
                "Order confirmation page is not displaying.");
        Assert.assertEquals(orderConfirmationPage.getOrderConfirmationMessage(), "Thank you for your order!",
                "Order confirmation message is not displaying.");
    }
}
