package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CourseSyllabusPage extends BasePage{
    //Locators
    private By courseNameSyllabus = By.className("dir-header__content-container-course__left__title-container-wrapper__title-container");
    private By courseImageSyllabus = By.xpath("//*[@id=\"courseroom\"]/div/div[1]/div/div[1]/div/div/div/div/div[1]/div");
    private By courseStatus = By.cssSelector("span[id*='strCourseSubscriptionState']");
    private By rating = By.className("no-cursor");
    private By cancelButton = By.xpath("//*[@id=\"courseroom\"]/div/div[1]/div/div[1]/div/div/div/div/div[2]/div[5]/button");
    private By courseActions = By.id("dropDownButtonId");
    private By courseProgress = By.xpath("//*[@id=\"courseroom\"]/div/div[1]/div/div[1]/div/div/div/div/div[2]/div[3]/div[2]");
    private By media1 = By.xpath("//*[@id=\"tileAnchorFor_Syllabus_750725_0\"]/div[1]/div[1]/div[2]/div[1]");
    private By media2 = By.xpath("//*[@id=\"tileAnchorFor_Syllabus_540024_0\"]/div[1]/div[1]/div[2]/div[1]");
    private By media1StatusIcon = By.cssSelector("#tileAnchorFor_Syllabus_750725_0 > div.dir-tile.dir-tile_status__passed > div.dir-tile__content > div.dir-tile__state-indicator > span");
    private By media2StatusIcon = By.cssSelector("#tileAnchorFor_Syllabus_540024_0 > div.dir-tile.dir-tile_status__waiting > div.dir-tile__content > div.dir-tile__state-indicator > span");
    private By courseProgressPercentage = By.cssSelector("#courseroom > div > div.angular-app-content.main.ng-scope > div > div.main__header.cell.ng-scope > div > div > div > div > div.dir-header__content-container-course__left > div.dir-header__content-container-course__left__progress.margin-top-8.ng-scope > div.dir-header__content-container-course__left__progress__text.ng-binding");
    private By userProfile = By.className("avatarWrapper");
    private By signOutButton = By.xpath("//*[@id=\":rc:\"]/div/div[2]/ul[2]/li/a");

    //Constructor
    public CourseSyllabusPage(WebDriver driver) {
        super(driver);
    }
    // get course name
    public String getCourseNameSyllabus() {
        return driver.findElement(courseNameSyllabus).getText();
    }
    //visibility of course image
    public boolean isCourseImageDisplayedSyllabus() {
        //WebElement courseImageElementSyllabus = driver.findElement(courseImageSyllabus);
        //waitForVisibility(courseImageElementSyllabus);
        return waitForElement(courseImageSyllabus).isDisplayed();
    }
    // visibility of course progress bar
    public boolean isCourseProgressDisplayedSyllabus() {
        //WebElement progressBar = driver.findElement(courseProgress);
        //waitForVisibility(progressBar);
        return waitForElement(courseProgress).isDisplayed();
    }
    // visibility of course status
    public boolean userStatusDisplayedSyllabus() {
        //WebElement userStatus = driver.findElement(courseStatus);
        //waitForVisibility(userStatus);
        return waitForElement(courseStatus).isDisplayed();
    }
    // visibility of course rating
    public boolean courseRatingDisplayedSyllabus() {
        //WebElement ratingElement = driver.findElement(rating);
        //waitForVisibility(ratingElement);
        return waitForElement(rating).isDisplayed();
    }
    // visibility of cancel button
    public boolean isCancelDisplayedSyllabus() {
        //WebElement cancel = driver.findElement(cancelButton);
        //waitForVisibility(cancel);
        return waitForElement(cancelButton).isDisplayed();
    }
    //visibility of course actions
    public boolean isCourseActionsDisplayedSyllabus() {
        //WebElement courseActionsElement = driver.findElement(courseActions);
        //waitForVisibility(courseActionsElement);
        return waitForElement(courseActions).isDisplayed();
    }
    // media1 status
    public String getMedia1Status () {
        //WebElement media1StatusIconElement = driver.findElement(media1StatusIcon);
        //waitForVisibility(media1StatusIconElement);
        return waitForElement(media1StatusIcon).getDomAttribute("aria-label");
    }
    // media2 status
    public String getMedia2Status () {
        //WebElement media2StatusIconElement = driver.findElement(media2StatusIcon);
        //waitForVisibility(media2StatusIconElement);
        return waitForElement(media2StatusIcon).getDomAttribute("aria-label");
    }
    // course progress percentage
    public String getCourseProgressPercentage () {
        //WebElement courseProgressPercentageElement = driver.findElement(courseProgressPercentage);
        //waitForVisibility(courseProgressPercentageElement);
        return waitForElement(courseProgressPercentage).getText();
    }
    // open MoocBild1
    public MoocBild1 openMoocBild1() {
        //WebElement moocBild1Element = driver.findElement(media1);
        //waitForVisibility(moocBild1Element);
        //moocBild1Element.click();
        waitForElementToBeClickable(media1).click();
        return new MoocBild1(driver);
    }
    // open MoocBild2
    public MoocBild2 openMoocBild2() {
        //WebElement moocBild2Element = driver.findElement(media2);
        //waitForVisibility(moocBild2Element);
        //moocBild2Element.click();
        waitForElementToBeClickable(media2).click();
        return new MoocBild2(driver);
    }
    //course status value
    public String getCourseStatus (){
        //driver.findElement(courseStatus);
        return waitForElement(courseStatus).getText();
    }
    public void signOut() {
        //WebElement userProfileElement = driver.findElement(userProfile);
        //click(userProfileElement);
        //WebElement signOut = driver.findElement(signOutButton);
        //click(signOut);
        waitForElementToBeClickable(userProfile).click();
        waitForElementToBeClickable(signOutButton).click();
        driver.manage().deleteAllCookies();

    }

}
