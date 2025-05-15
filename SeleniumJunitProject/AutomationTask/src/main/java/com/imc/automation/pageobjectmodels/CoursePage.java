package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CoursePage extends BasePage{
// Locators
    private By courseName = By.className("imc-header__title-wrapper__title");
    private By courseImage = By.xpath("//*[@id=\"descriptionWrapper\"]/section/imc-description/div/imc-tab-page-skeleton/div[1]/imc-header/div/div[2]/ng-component/div/div/div[1]/imc-image/div");
    private By breadCrumb = By.className("header-breadcrumbs__item__link__text");
    private By courseType = By.className("imc-header__label__text");
    private By enrollButton = By.xpath("//button[normalize-space()='Enrol']");
    private By enrollButtonModal = By.id("enrolment_proceed_button");
    private By addToWishlistButton = By.xpath("//button[@title='Add to wish list']");
    private By myLearning = By.xpath("//button[@title='My learning']");
    private By learningStatus = By.xpath("//a[.//span[normalize-space()='Learning status']]");

    //Constructor
    public CoursePage(WebDriver driver) {
        super(driver);
    }
    public String getCourseName() {
        return driver.findElement(courseName).getText();
    }
    public boolean isCourseImageDisplayed () {
        WebElement courseImageElement = driver.findElement(courseImage);
        waitForVisibility(courseImageElement);
        return courseImageElement.isDisplayed();
    }
    public boolean isBreadCrumbDisplayed () {
        WebElement catalogBreadcrumb = driver.findElement(breadCrumb);
        waitForVisibility(catalogBreadcrumb);
        return catalogBreadcrumb.isDisplayed();
    }
    public boolean isCourseTypeDisplayed () {
        WebElement courseTypeElement = driver.findElement(courseType);
        waitForVisibility(courseTypeElement);
        return courseTypeElement.isDisplayed();
    }
    // enroll button displayed
    public boolean isEnrollButtonDisplayed () {
        WebElement enrollButtonElement = driver.findElement(enrollButton);
        waitForVisibility(enrollButtonElement);
        return enrollButtonElement.isDisplayed();
    }
    // enroll user
    public CourseSyllabusPage enrolUser() {
        WebElement enrollButtonElement = driver.findElement(enrollButton);
        click(enrollButtonElement);
        WebElement enrollButtonModalElement = driver.findElement(enrollButtonModal);
        click(enrollButtonModalElement);
        return new CourseSyllabusPage(driver);
    }
    //add to wishlist
    public void addToWishlist() {
        waitForElement(addToWishlistButton).click();
    }
    //open learning status
    public LearningStatus openLearningStatus() {
        waitForElement(myLearning).click();
        waitForElement(learningStatus).click();
        return new LearningStatus(driver);
    }
}
