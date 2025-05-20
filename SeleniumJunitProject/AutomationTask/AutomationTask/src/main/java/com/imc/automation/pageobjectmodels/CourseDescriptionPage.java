package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class CourseDescriptionPage extends BasePage {
    //Locators
    private By removeFromWishlistButton = By.xpath("//button[@title='Remove from wish list']");
    private By addToWishlistButton = By.xpath("//button[@title='Add to wish list']");
    private By userProfile = By.className("avatarWrapper");
    private By signOutButton = By.xpath("//*[@id=\":rc:\"]/div/div[2]/ul[2]/li/a");
    private By myLearning = By.xpath("//button[@title='My learning']");
    private By learningStatus = By.xpath("//a[.//span[normalize-space()='Learning status']]");

    public CourseDescriptionPage (WebDriver driver) {
        super(driver);
    }
    // method to get the course name
    public By courseTitleByText(String courseName) {
        return By.xpath("//div[normalize-space()='" + courseName + "']");
    }// get course name if visible
    public boolean isCourseVisible(String courseName) {
        try {
            //WebElement course = waitForElement(courseTitleByText(courseName));
            //WebElement course = wait.until(ExpectedConditions.visibilityOfElementLocated(courseTitleByText(courseName)));
            //return course.isDisplayed();
            return waitForElement(courseTitleByText(courseName)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }// get course name in a string for verification
    public String courseNamePageDescription (String courseName) {
        return waitForElement(courseTitleByText(courseName)).getText();
    }
    // remove course from wishlist
    public void removeCourseFromWishlist() {
        waitForElementToBeClickable(removeFromWishlistButton).click();
    }
    //add to wishlist button visible
    public boolean addToWishlistButtonVisible() {
        waitForElementToBeClickable(addToWishlistButton);
        return waitForElementToBeClickable(addToWishlistButton).isDisplayed();
    }
    //remove from to wishlist button visible
    public boolean removeFromWishlistButtonVisible() {
        waitForElementToBeClickable(removeFromWishlistButton);
        return waitForElementToBeClickable(removeFromWishlistButton).isDisplayed();
    }
    // sign out
    public void signOut () {
        waitForElementToBeClickable(userProfile).click();
        waitForElementToBeClickable(signOutButton).click();
    }
    //open learning status
    public LearningStatus openLearningStatus() {
        waitForElement(myLearning).click();
        waitForElement(learningStatus).click();
        return new LearningStatus(driver);
    }
    }

    // Add more methods for interacting with the course page

