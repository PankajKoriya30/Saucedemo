package com.learn.automation.driver.tests;

import com.learn.automation.driver.pages.LoginPage;
import com.learn.automation.driver.pages.ProductsPage;
import com.learn.automation.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyLogin extends BaseTest{

        private LoginPage loginPage;

        @Test(groups = "smoke")
        public void verifySuccessLogin(){
            loginPage = new LoginPage(driver);
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
}