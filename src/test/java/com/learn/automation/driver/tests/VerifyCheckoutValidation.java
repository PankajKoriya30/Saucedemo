package com.learn.automation.driver.tests;

import com.learn.automation.driver.pages.CartPage;
import com.learn.automation.driver.pages.CheckoutInfoPage;
import com.learn.automation.driver.pages.LoginPage;
import com.learn.automation.driver.pages.ProductsPage;
import com.learn.automation.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyCheckoutValidation extends BaseTest{

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutInfoPage checkoutInfoPage;

    @BeforeMethod(alwaysRun = true)
    public void commomOperation(){
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(TestDataReader.getTestData("username"));
        loginPage.enterPassword(TestDataReader.getTestData("password"));
        productsPage = loginPage.clickLogin();

        // Adding two products to cart
        productsPage.clickProductAddToCartButton(TestDataReader.getTestData("backpack"));
        productsPage.clickProductAddToCartButton(TestDataReader.getTestData("bikelight"));
        cartPage = productsPage.openShoppingCart();
        checkoutInfoPage = cartPage.clickCheckout();

    }
    @Test(groups = {"regression", "checkout"})
    public void verifyFirstNameRequiredValidation() {
        // Verify validation message for First Name field
        checkoutInfoPage.enterLastName(TestDataReader.getTestData("lastname"));
        checkoutInfoPage.enterZipCode(TestDataReader.getTestData("zipcode"));
        checkoutInfoPage.clickContinue();
        Assert.assertEquals(checkoutInfoPage.getErrorMessage(), "Error: First Name is required",
                "First Name field error message is not matching.");
        Assert.assertEquals(checkoutInfoPage.getCheckoutPageTitle(), "Checkout: Your Information",
                "Checkout your information page is not displaying.");
    }
    @Test(groups = {"regression", "checkout"})
    public void verifyLastNameRequiredValidation() {
        // Verify validation message for Last Name field
        checkoutInfoPage.enterFirstName(TestDataReader.getTestData("firstname"));
        checkoutInfoPage.enterZipCode(TestDataReader.getTestData("zipcode"));
        checkoutInfoPage.clickContinue();
        Assert.assertEquals(checkoutInfoPage.getErrorMessage(), "Error: Last Name is required",
                "Last Name field error message is not matching.");
        Assert.assertEquals(checkoutInfoPage.getCheckoutPageTitle(), "Checkout: Your Information",
                "Checkout your information page is not displaying.");
    }

    @Test(groups = {"regression", "checkout"})
    public void verifyPostalCodeRequiredValidation(){
        // Verify validation message for Postal code field
        checkoutInfoPage.enterFirstName(TestDataReader.getTestData("firstname"));
        checkoutInfoPage.enterLastName(TestDataReader.getTestData("lastname"));
        checkoutInfoPage.clickContinue();
        Assert.assertEquals(checkoutInfoPage.getErrorMessage(), "Error: Postal Code is required",
                "Postal Code field error message is not matching.");
        Assert.assertEquals(checkoutInfoPage.getCheckoutPageTitle(), "Checkout: Your Information",
                "Checkout your information page is not displaying.");

    }
}
