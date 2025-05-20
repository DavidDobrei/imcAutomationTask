package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class CoursesManager extends BasePage {
    String partialText = "DevOps Fundamentals: Tools and Techniques";
    //Locators
    private By createButton = By.id("tbb_createGroup");
    private By coursesTable = By.id("gridcolumn-1038");
    private By searchBox = By.id("searchTerm-inputEl");
    private By iFrame = By.id("iframe_navi:1156");
    private By iFrame1 = By.id("contentframe");
    private By iFrameParticipants = By.id("iframe_participants.3.900507");
    private By searchButton = By.id("startSearchButton");
    private By toolbarParticipantAdministration = By.id("tbb_participants-btnIconEl");
    private By buttonParticipantAdministration = By.id("tbi_participants-itemEl");
    private By courseTableLoadingSpinner = By.id("loadmask-1133-msgEl");
    private By userProfile = By.className("avatarWrapper");
    private By signOutButton = By.xpath("//*[@id=\":rc:\"]/div/div[2]/ul[2]/li/a");

    private By courseToBeFound = By.xpath("//tr[contains(@class, 'x-grid-row')]//td[contains(normalize-space(string(.)), '" + partialText + "')]");



    //Constructor
    public CoursesManager (WebDriver driver) {
        super(driver);
    }
    // switch to iframe that contains
    private void switchToFrame() {
        driver.switchTo().frame(driver.findElement(iFrame1));
        driver.switchTo().frame(driver.findElement(iFrame));
    }
    // switch back to default content
    private void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
    // check toolbar visibility
    public boolean createButtonIsDisplayed () {
        switchToFrame();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createButton)).isDisplayed();
    }
    // check table of courses is displayed
    public boolean coursesTableIsDisplayed () {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(coursesTable)).isDisplayed();
    }
    // type course into search field
    public void searchCourses (String courseName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        driver.findElement(searchBox).sendKeys(courseName);
    }
    // click on search button
    public void clickSearchButton () {
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(courseTableLoadingSpinner));
        waitForElementToBeClickable(searchButton).click();

    }
    // check visibility of course searched
    public boolean courseToBeFoundDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(courseToBeFound)).isDisplayed();
    }
    //select course
    public void selectSearchedCourse () {

        wait.until(ExpectedConditions.visibilityOfElementLocated(courseToBeFound)).click();
    }
    // open participant administration
    public ParticipantAdministration openParticipantAdministration () {

        waitForElement(toolbarParticipantAdministration).click();
        waitForElement(buttonParticipantAdministration).click();
        String currentTab = driver.getWindowHandle();
        driver.switchTo().window(currentTab);

        return new ParticipantAdministration(driver);
    }// sign out
    public void signOut() {
        //WebElement userProfileElement = driver.findElement(userProfile);
        //click(userProfileElement);
        //WebElement signOut = driver.findElement(signOutButton);
        //click(signOut);
        waitForElementToBeClickable(userProfile).click();
        waitForElementToBeClickable(signOutButton).click();
    }


}
