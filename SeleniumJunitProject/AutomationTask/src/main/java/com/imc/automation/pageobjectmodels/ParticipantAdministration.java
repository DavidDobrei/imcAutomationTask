package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class ParticipantAdministration extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(ParticipantAdministration.class);
    String partialText = "DevOps Fundamentals: Tools and Techniques";
    //Locators


    private By contentFrame = By.id("contentframe");
    private By frameParticipants = By.id("iframe_participants.3.900507");
    private By frameBookedParticipant = By.id("iframe_c_participants_booked_person_3");
    //private By frame3Canceled = By.id("iframe_participants.3.900507");
    private By frameCanceledParticipants = By.id("iframe_c_participants_cancelled_person_3");
    private By searchBox = By.id("searchTerm-inputEl");
    private By searchButton = By.id("startSearchButton");
    private By participantButton = By.id("tbb_participant");
    private By cancelParticipantButton = By.id("tbi_cancelParticipant-itemEl");
    private By pageHeader = By.id("headline_mainText");
    private By userToBeFound = By.xpath("//tr[contains(@class, 'x-grid-row')]//td[contains(normalize-space(string(.)), '" + "mystaffuser2012" + "')]");
    private By userToCancel = By.xpath("//tr[contains(@class, 'x-grid-row')]//td[contains(normalize-space(string(.)), '" + "didClientStaff12" + "')]");

    private By cancelUserToBeFound =  By.xpath("//tr[td/div[text()='mystaffuser2012']]");
    private By confirmCancellationButton = By.id("layerWindowext-gen1022_btnOK");
    private By closeCancellationModal = By.id("layerWindowext-gen1022_btnCancel");
    private By cancellationRegister = By.id("tab-1014");
    private By removeParticipant = By.id("tbi_removeParticipant");
    private By xmask = By.xpath("//div[contains(@class,'x-mask') and contains(@style,'z-index')]");
    private By cancellationModal = By.id("layerWindowext-gen1022");
    private By saveAndCloseButton = By.id("default_saveClose");


    //Constructor
    public ParticipantAdministration (WebDriver driver) {
        super(driver);
    }



    // switch to iframe that contains search box and search button
    private void switchToFrame() {

        driver.switchTo().frame(driver.findElement(contentFrame));
        driver.switchTo().frame(driver.findElement(frameParticipants));
        driver.switchTo().frame(driver.findElement(frameBookedParticipant));
    }
    private void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
    //search participant
    public void searchParticipant (String username) {
        switchToFrame();
        waitForElement(searchBox).sendKeys(username);

    }
    // click search
    public void clickSearchButton () {
        waitForElementToBeClickable(searchButton).click();
    }
    // search and select in cancelation register
    public void searchAndSelectCanceledParticipant (String username) {

        waitForElementToBeClickable(searchBox).sendKeys(username);
        waitForElementToBeClickable(searchButton).click();
        waitForElementToBeClickable(userToBeFound).click();
    }
    public void selectParticipant () {
        waitForElementToBeClickable(userToBeFound).click();
    }
    public String pageHeaderText() {

        return driver.getCurrentUrl();
    }
    // check tab switch
    public String currentUrl (){
        return driver.getCurrentUrl();
    }
    //user found
    public boolean isParticipantFound(String participant) {
        return waitForElement(userToBeFound).isDisplayed();
    }
    // cancel participant
    public void cancelParticipant () {
        waitForElementToBeClickable(userToBeFound).click();
        waitForElementToBeClickable(participantButton).click();
        waitForElementToBeClickable(cancelParticipantButton).click();
        waitForElementToBeClickable(confirmCancellationButton).click();

    }
    // tryout dinamic locator
    public By userToBeCanceled (String username) {
        return By.xpath("//tr[contains(@class, 'x-grid-row')]//td[contains(normalize-space(string(.)), '" + username + "')]");
    }
    // Cancel user with dynamic locator
    public void cancelAnyParticipant (String username) {
        waitForElementToBeClickable(userToBeCanceled(username)).click();
        waitForElementToBeClickable(participantButton).click();
        waitForElementToBeClickable(cancelParticipantButton).click();
        waitForElementToBeClickable(confirmCancellationButton).click();
        switchToDefaultContent();
    }
    //Another take to cancellation register
    public void selectCancelledTab (){
        driver.switchTo().frame(driver.findElement(contentFrame));
        driver.switchTo().frame(driver.findElement(frameParticipants));
        waitForElement(cancellationRegister).click();
        driver.switchTo().defaultContent();

    }
    // Search cancelled user #2
    public void findAndRemoveCancelledParticipant(String username) throws InterruptedException {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(contentFrame));
        driver.switchTo().frame(driver.findElement(frameParticipants));
        driver.switchTo().frame(driver.findElement(frameCanceledParticipants));
        waitForElement(searchBox).sendKeys(username);
        waitForElementToBeClickable(searchButton).click();
        Thread.sleep(1000);
        waitForElementToBeClickable(userToBeCanceled(username)).click();
        removeParticipant();
        driver.switchTo().defaultContent();
    }
    // Save and close + logout
    public void saveAndCloseParticipantAdministration (){
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(contentFrame));
        driver.switchTo().frame(driver.findElement(frameParticipants));
        waitForElementToBeClickable(saveAndCloseButton).click();
    }

    // open cancellation register
    public void openCancellationRegister () {
        driver.switchTo().parentFrame();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cancellationModal));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(xmask));
        waitForElementToBeClickable(cancellationRegister).click();


    }
    // save and close
    public void saveAndClose() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(contentFrame));
        driver.switchTo().frame(driver.findElement(frameParticipants));
        wait.until(ExpectedConditions.elementToBeClickable(saveAndCloseButton));

    }

    //search canceled user
    public void searchCanceledParticipant (String username) {
        waitForElement(searchBox).clear();
        waitForElement(searchBox).sendKeys(username);
        clickSearchButton();
    }
    //select canceled user
    public void selectCanceledUser(String username) {
        By userRow = By.xpath("//tr[td/div[text()='" + username + "']]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement row = wait.until(ExpectedConditions.elementToBeClickable(userRow));
        row.click();
    }
    // remove user from course from cancellation register
    public void removeParticipant (){
        waitForElement(participantButton).click();
        waitForElementToBeClickable(removeParticipant).click();
    }

    // switch to toolbar frame
    public void switchToToolbarFrame() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("contentframe");
        driver.switchTo().frame("iframe_participants.3.900507");
    }

}