package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DenialOfAccessManager extends BasePage {


    //Locators
    private By iFrameNavi1246 = By.id("iframe_navi:1246");
    private By iFrameContent = By.id("contentframe");
    private By searchBox = By.id("searchTerm-inputEl");
    private By searchButton = By.id("startSearchButton");
    private By denialOfAccessButton = By.id("tbb_reset");
    private By resertSelectedLoginsButton = By.id ("tbi_reset-itemEl");
    private By lockedUser = By.xpath("//tr[.//td[contains(., 'mystaffuser2015')]]");
    private By userProfile = By.className("avatarWrapper");
    private By signOutButton = By.xpath("//a[.//span[text()='Sign out']]");
    private By resetButton = By.xpath ("//a[.//span[text()='Reset']]");
    private By notificationToast = By.xpath("//td[text()='Login attempts reset successfully']");
    //Constructor
    public DenialOfAccessManager (WebDriver driver) {
        super(driver);
    }
    // switch to navi frame
    public void switchToIFrameNavi1246() {
        driver.switchTo().frame(driver.findElement(iFrameContent));
        driver.switchTo().frame(driver.findElement(iFrameNavi1246));
    }
    // enter user in search box
    public void enterUserInSearchField (String username) {
        waitForElementToBeClickable(searchBox).sendKeys(username);
    }
    // unlock user
    public void unlockUser (String username) {
        switchToIFrameNavi1246();
        enterUserInSearchField(username);
        waitForElementToBeClickable(searchButton).click();
        waitForElementToBeClickable(lockedUser).click();
        waitForElementToBeClickable(denialOfAccessButton).click();
        waitForElementToBeClickable(resertSelectedLoginsButton).click();
        waitForElementToBeClickable(resetButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(notificationToast));
    }
    public void signOut() {
        driver.switchTo().defaultContent();
        waitForElement(userProfile).click();
        waitForElementToBeClickable(signOutButton).click();
    }
}
