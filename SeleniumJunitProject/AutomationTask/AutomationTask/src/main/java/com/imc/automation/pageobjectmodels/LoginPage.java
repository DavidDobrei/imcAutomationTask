package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BasePage {

    // Locators
    private  By usernameField = By.id("externalForm:login");
    private  By passwordField = By.id("externalForm:password");
    private  By loginButton = By.id("externalForm:loginButtonAbsolute");
    private By acceptCookiePolicy = By.id("closeButton");

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUsernameFieldVisible() {
        WebElement username = driver.findElement(usernameField);
        waitForVisibility(username);
        return username.isDisplayed();
    }
    //Accept cookies pollicy

    // Login method. Credentials in properties file
    public PersonalDashboardPage login (String username, String password) {
        waitForElement(usernameField).sendKeys(username);
        waitForElement(passwordField).sendKeys(password);
        waitForElementToBeClickable(loginButton).click();
        return new PersonalDashboardPage(driver);
    }
    public FailedLoginPage loginUnsuccessful (String username, String password) {
        waitForElement(usernameField).sendKeys(username);
        waitForElement(passwordField).sendKeys(password);
        waitForElementToBeClickable(loginButton).click();
        return new FailedLoginPage(driver);
    }
    //Accept cookie policy
    public void acceptCookiePolicy() {
        if (isDisplayed(acceptCookiePolicy)) {
            driver.findElement(acceptCookiePolicy).click();
        }
    }

}
