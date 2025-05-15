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
    private By searchResultsName = By.xpath("//*[@id=\"cs_result_item_900516\"]/one-column-list-item/div/div/div[1]/div[2]/div/div[1]");
    private By wishlistCourseName = By.xpath("//*[@id=\"cs_result_item_762283\"]/one-column-list-item/div/div/div[1]/div[2]/div/div[1]");
    private By courseTile = By.className("imc-list-item__tile");



//Constructor
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    //type course name and search it
    public void searchCourse (String course) {
        WebElement searchBox = driver.findElement(searchField);
        searchBox.sendKeys(course);
        WebElement initiateSearch = driver.findElement(searchButton);
        click(initiateSearch);
    }
    // get name of returned course
    public String getCourseName () {
        return driver.findElement(searchResultsName).getText();
    }
    public String getWishlistCourseName () {
        return driver.findElement(wishlistCourseName).getText();
    }
    public CoursePage openCourse (){
        WebElement tile = driver.findElement(courseTile);
        wait.until(ExpectedConditions.presenceOfElementLocated(courseTile));
        click(tile);
        return new CoursePage(driver);
    }
}
