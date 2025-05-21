package com.imc.automation.pageobjectmodels;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CatalogPage extends BasePage {
//Locators
    private By searchField = By.id("searchField");
    private By searchButton = By.id("startSearchBtn");
    private By searchResultsName = By.xpath("//div[contains(@title, 'DevOps Fundamentals')]");
    private By wishlistCourseName = By.xpath("//div[@title='[TD]Course SD New Course Room']");
    private By courseTile = By.className("imc-list-item__tile");



//Constructor
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    //type course name and search it
    public void searchCourse (String course) throws InterruptedException {
        waitForElement(searchField).sendKeys(course);
        Thread.sleep(2000);
        waitForElementToBeClickable(searchButton).click();
        //WebElement searchBox = driver.findElement(searchField);
        //searchBox.sendKeys(course);
        //WebElement initiateSearch = driver.findElement(searchButton);
        //click(initiateSearch);
    }
    // get name of returned course
    public String getCourseName () {
        return waitForElement(searchResultsName).getAttribute("title");
    }
    public String getWishlistCourseName () {
        return waitForElement(wishlistCourseName).getDomAttribute("title");
    }
    public CoursePage openCourse (){
        //WebElement tile = driver.findElement(searchResultsName);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsName));
        //click(tile);
        waitForElement(courseTile).click();
        return new CoursePage(driver);
    }
    public CoursePage openCourseWishlist (){
        //WebElement tile = driver.findElement(wishlistCourseName);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistCourseName));
        //click(tile);
        waitForElement(wishlistCourseName).click();
        return new CoursePage(driver);
    }
}
