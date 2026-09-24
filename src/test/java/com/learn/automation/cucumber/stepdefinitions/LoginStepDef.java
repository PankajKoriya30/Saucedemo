package com.learn.automation.cucumber.stepdefinitions;

import com.learn.automation.driver.DriverFactory;
import com.learn.automation.pages.LoginPage;
import com.learn.automation.pages.ProductsPage;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.TestDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginStepDef {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Given("user is on the sauce demo login page")
    public void userIsOnTheSauceDemoLoginPage(){
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("url"));
        loginPage = new LoginPage(driver);
    }

    @When("user enters valid credentials")
    public void userEntersValidCredentials(){
        loginPage.enterUsername(TestDataReader.getTestData("username"));
        loginPage.enterPassword(TestDataReader.getTestData("password"));
        productsPage = loginPage.clickLogin();

    }

    @Then("user redirects to products page")
    public void userRedirectsToProductsPage(){

        Assert.assertTrue(productsPage.getProductsPageTitle().equalsIgnoreCase("Products"),
                "Products page is not displayed after login");
    }
}
