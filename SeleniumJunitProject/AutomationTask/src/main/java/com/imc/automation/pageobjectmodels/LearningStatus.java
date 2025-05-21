package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class LearningStatus extends BasePage {
    //Locators
    private By current = By.id("mylearnings-current");
    private By pendingEnrolments = By.id("mylearnings-pending");
    private By recommended = By.id("mylearnings-recommended");
    private By completed = By.id("mylearnings-completed");
    private By cancelled = By.id("mylearnings-cancelled");
    private By all = By.id("mylearnings-all");
    private By wishlist = By.id("mylearnings-wishlist");
    private By courseSearchField = By.id("searchField");
    private By courseSearchButton = By.id("startSearchBtn");
    private By userProfile = By.className("avatarWrapper");
    private By signOutButton = By.xpath("//*[@id=\":rc:\"]/div/div[2]/ul[2]/li/a");

    //Constructor
    public LearningStatus(WebDriver driver) {
        super(driver);
    }
    // check current tab
    public boolean isCurrentDisplayed () {
        return waitForElement(current).isDisplayed();
    }
    // check pending tab
    public boolean isCurrentPendingDisplayed () {
        return waitForElement(pendingEnrolments).isDisplayed();
    }
    // check recommended tab
    public boolean isRecommendedDisplayed () {
        return waitForElement(recommended).isDisplayed();
    }
    // check completed tab
    public boolean isCompletedDisplayed () {
        return waitForElement(completed).isDisplayed();
    }
    // check canceled tab
    public boolean isCancelledDisplayed () {
        return waitForElement(cancelled).isDisplayed();
    }
    // check all tab
    public boolean isAllDisplayed () {
        return waitForElement(all).isDisplayed();
    }
    // check wishlist tab
    public boolean isWishlistDisplayed () {
        return waitForElement(wishlist).isDisplayed();
    }
    // select wishlist
    public void selectWishlist () {
        waitForElement(wishlist).click();
    }
    // search course
    public void searchCourse(String courseName) {
        waitForElement(courseSearchField).sendKeys(courseName);
        waitForElement(courseSearchButton).click();
    }
    public By courseTitleByText(String courseName) {
        return By.xpath("//div[normalize-space()='" + courseName + "']");
    }

    public boolean isCourseVisible(String courseName) {
        try {
            //WebElement course = waitForElement(courseTitleByText(courseName));
            //WebElement course = wait.until(ExpectedConditions.visibilityOfElementLocated(courseTitleByText(courseName)));
            //return course.isDisplayed();
            return waitForElement(courseTitleByText(courseName)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    //open course
    public CourseDescriptionPage openCourse(String courseName) {

        WebElement course = wait.until(ExpectedConditions.elementToBeClickable(courseTitleByText(courseName)));
        course.click();
        return new CourseDescriptionPage(driver);
    }
    //sign out
    public void signOut () {
        waitForElementToBeClickable(userProfile).click();
        waitForElementToBeClickable(signOutButton).click();
    }
}
// switch to iframe that contains