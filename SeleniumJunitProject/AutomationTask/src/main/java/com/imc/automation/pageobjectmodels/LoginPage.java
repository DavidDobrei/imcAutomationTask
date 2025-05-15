package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    // Locators
    private  By usernameField = By.id("externalForm:login");
    private  By passwordField = By.id("externalForm:password");
    private  By loginButton = By.id("externalForm:loginButtonAbsolute");

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUsernameFieldVisible() {
        WebElement username = driver.findElement(usernameField);
        waitForVisibility(username);
        return username.isDisplayed();
    }
    // Login method. Credentials in properties file
    public PersonalDashboardPage login (String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new PersonalDashboardPage(driver);
    }
    public FailedLoginPage loginUnsuccessful (String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new FailedLoginPage(driver);
    }

}
