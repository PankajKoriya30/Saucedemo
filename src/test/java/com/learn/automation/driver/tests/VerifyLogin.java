package com.learn.automation.driver.tests;

import com.learn.automation.driver.DriverFactory;
import com.learn.automation.driver.pages.LoginPage;
import com.learn.automation.driver.pages.ProductsPage;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyLogin {

        private WebDriver driver;
        LoginPage loginPage;

        @BeforeMethod
        public void setup(){
            ConfigReader.loadProperties("qa");
            TestDataReader.loadProperties("testdata");
            driver = DriverFactory.createDriver(ConfigReader.getProperty("browser"));
            driver.get(ConfigReader.getProperty("url"));
            loginPage = new LoginPage(driver);
        }

        @Test
        public void verifySuccessLogin(){
            loginPage.enterUsername(TestDataReader.getTestData("username"));
            loginPage.enterPassword(TestDataReader.getTestData("password"));
            ProductsPage productsPage = loginPage.clickLogin();

            String productsPageTitle = productsPage.getProductsPageTitle();
            Assert.assertEquals(productsPageTitle, "Products");
            System.out.println("Page title: " + productsPageTitle);

            String title = driver.getTitle();
            Assert.assertEquals(title, "Swag Labs");
            System.out.println("Page title: " + title);
        }

        @AfterMethod
        public void tearDown() {
            DriverFactory.quitDriver();
        }
}