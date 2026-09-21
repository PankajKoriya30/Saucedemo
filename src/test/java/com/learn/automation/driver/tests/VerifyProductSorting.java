package com.learn.automation.driver.tests;

import com.learn.automation.driver.pages.LoginPage;
import com.learn.automation.driver.pages.ProductsPage;
import com.learn.automation.utils.TestDataReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerifyProductSorting extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeMethod(alwaysRun = true)
    public void commomOperation() {
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(TestDataReader.getTestData("username"));
        loginPage.enterPassword(TestDataReader.getTestData("password"));
        productsPage = loginPage.clickLogin();
    }

    @Test(groups = "regression")
    public void verifyProductSorting() {

        String pageTitle = productsPage.getProductsPageTitle();
        Assert.assertEquals(pageTitle, "Products");
        productsPage.sortProductsPriceLowToHigh();
        Assert.assertTrue(productsPage.getPriceOfFirstProduct() < productsPage.getPriceOfSecondProduct(),
                "Products are not sorted Price Low To High.");
    }

    @Test
    public void verifyProductSortingWithAllProducts() {
        String pageTitle = productsPage.getProductsPageTitle();
        Assert.assertEquals(pageTitle, "Products");
        productsPage.sortProductsPriceLowToHigh();
        List<WebElement> productPrices = productsPage.getProductPrices();
        List<Double> actualSorting = new ArrayList<>();
        for (WebElement price : productPrices) {
            actualSorting.add(Double.parseDouble(price.getText().substring(1)));
        }
        List<Double> expectedSorting = new ArrayList<>(actualSorting);
        Collections.sort(expectedSorting);
        Assert.assertEquals(actualSorting, expectedSorting, "Products are not sorted Price Low To High.");
    }

    @Test
    public void verifyProductsPageTitle(){
        Assert.assertEquals(productsPage.getProductsPageTitle(),
                "Products",
                "Products page title is not matching.");
    }
}
