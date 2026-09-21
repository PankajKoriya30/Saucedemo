package com.learn.automation.driver.tests;

import com.learn.automation.driver.DriverFactory;
import com.learn.automation.driver.pages.LoginPage;
import com.learn.automation.driver.pages.ProductsPage;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerifyProductSorting {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void setup() {
        System.out.println("Creating browser");
        ConfigReader.loadProperties("qa");
        TestDataReader.loadProperties("testdata");
        driver = DriverFactory.createDriver(ConfigReader.getProperty("browser"));
        driver.get(ConfigReader.getProperty("url"));
        loginPage = new LoginPage(driver);
    }

    @Test
    public void verifyProductSorting() {
        loginPage.enterUsername(TestDataReader.getTestData("username"));
        loginPage.enterPassword(TestDataReader.getTestData("password"));
        productsPage = loginPage.clickLogin();
        String pageTitle = productsPage.getProductsPageTitle();
        Assert.assertEquals(pageTitle, "Products");
        productsPage.sortProductsPriceLowToHigh();
        Assert.assertTrue(productsPage.getPriceOfFirstProduct() < productsPage.getPriceOfSecondProduct(),
                "Products are not sorted Price Low To High.");
    }

    @Test
    public void verifyProductSortingWithAllProducts() {
        loginPage.enterUsername(TestDataReader.getTestData("username"));
        loginPage.enterPassword(TestDataReader.getTestData("password"));
        productsPage = loginPage.clickLogin();
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
        loginPage.enterUsername(TestDataReader.getTestData("username"));
        loginPage.enterPassword(TestDataReader.getTestData("password"));
        productsPage = loginPage.clickLogin();
        Assert.assertEquals(productsPage.getProductsPageTitle(),
                "Products",
                "Products page title is not matching.");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing browser");
        DriverFactory.quitDriver();
    }
}
