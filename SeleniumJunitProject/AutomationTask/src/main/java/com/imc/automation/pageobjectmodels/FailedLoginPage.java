package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FailedLoginPage extends BasePage {

    // Locators
    private  By usernameField = By.id("j_id_1j:3:loginform:userdatalogin");
    private  By passwordField = By.id("j_id_1j:3:loginform:password");
    private  By loginButton = By.id("j_id_1j:3:loginform:loginButton");
    private By errorMessage = By.id("idm-error");

    public FailedLoginPage (WebDriver driver) {
        super(driver);
    }
    public void enterUsername (String username) {
        waitForElement(usernameField).clear();
        waitForElement(usernameField).sendKeys(username);
    }
    public void enterWrongPassword (String password) {
        waitForElement(passwordField).clear();
        waitForElement(passwordField).sendKeys(password);
    }
    public void clickLoginButton() {
        waitForElement(loginButton).click();
    }
    public String getErrorMessage () {
        WebElement element = driver.findElement(errorMessage);
        return element.getText();
    }

}

