package com.learn.automation.driver.tests;

import com.learn.automation.driver.pages.LoginPage;
import com.learn.automation.driver.pages.ProductsPage;
import com.learn.automation.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyProduct extends BaseTest {

    private LoginPage loginPage;

    @Test
    public void verifyProductBackpack() {
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(TestDataReader.getTestData("username"));
        loginPage.enterPassword(TestDataReader.getTestData("password"));
        ProductsPage productsPage = loginPage.clickLogin();

        String productsPageTitle = productsPage.getProductsPageTitle();
        Assert.assertEquals(productsPageTitle, "Products", "Products page " +
                "title is not matching.");
        System.out.println("Page title: " + productsPageTitle);

        String backpackName = productsPage.getBackpackName();
        Assert.assertEquals(backpackName, TestDataReader.getTestData("backpack"));
        System.out.println("Page title: " + backpackName);
    }
}