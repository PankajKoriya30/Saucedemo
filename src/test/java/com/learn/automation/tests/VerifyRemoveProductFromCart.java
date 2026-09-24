package com.learn.automation.tests;

import com.learn.automation.pages.CartPage;
import com.learn.automation.pages.LoginPage;
import com.learn.automation.pages.ProductsPage;
import com.learn.automation.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyRemoveProductFromCart extends BaseTest{

        private LoginPage loginPage;
        private ProductsPage productsPage;
        private CartPage cartPage;

        @Test(groups = "regression")
        public void verifyRemoveProductFromCart(){
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
                    "Backpack is not present on the cart page.");
            Assert.assertTrue(cartPage.getCartProductNames().contains(TestDataReader.getTestData("bikelight")),
                    "Bike light is not present on the cart page.");

            Assert.assertEquals(cartPage.getCartProductNames().size(), 2,
                    "cart doesn't contains exactly 2 products");

            cartPage.removeProductFromCart(TestDataReader.getTestData("backpack"));
            List<String> productsOnCartPage = cartPage.getCartProductNames();
            Assert.assertFalse(productsOnCartPage.contains(TestDataReader.getTestData("backpack")),
                    "Backpack is not removed from cart page.");
            Assert.assertTrue(productsOnCartPage.contains(TestDataReader.getTestData("bikelight")),
                    "Back light is not available on cart page.");

            Assert.assertEquals(productsOnCartPage.size(), 1,
                    "cart doesn't contains only 1 product.");
        }
    }
