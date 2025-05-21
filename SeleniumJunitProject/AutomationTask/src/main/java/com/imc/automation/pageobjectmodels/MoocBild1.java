package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MoocBild1 extends BasePage{
    //Locators
    private By moocBild1Name = By.id("course-content-header-text");
    private By moocBild1Image = By.className("image-centered");
    private By backToSyllabus = By.id("back-to-courseroom-link");
    private By nextMedia = By.id("nextForumComponentBtn");
    private By mediaDescription = By.id("content-description");
    private By passedMedia1Icon = By.xpath("//*[@id=\"course-content\"]/div[1]/div/div[1]/div/div/div");


    //Constructor
    public MoocBild1(WebDriver driver) {
        super(driver);
    }
    //get Name is displayed
    public String getMoocBild1Name() {
        return driver.findElement(moocBild1Name).getText();
    }
    // image is displayed
    public boolean isMoocBild1ImageDisplayed() {
        return driver.findElement(moocBild1Image).isDisplayed();
    }
    //media description is displayed
    public boolean isMediaDescriptionDisplayed() {
        return driver.findElement(mediaDescription).isDisplayed();
    }
    //next button displayed
    public boolean isNextMediaDisplayed() {
        return driver.findElement(nextMedia).isDisplayed();
    }
    //back to syllabus button displayed
    public boolean backToSyllabusDisplayed() {
        return driver.findElement(backToSyllabus).isDisplayed();
    }
    //MoocBild1 is concluded
    public String media1Passed() {
        //WebElement passedMedia1IconElement = driver.findElement(passedMedia1Icon);
        //waitForVisibility(passedMedia1IconElement);
        return waitForElement(passedMedia1Icon).getDomAttribute("title");
    }
    public CourseSyllabusPage backToCourseSyllabusPage() {
        waitForElement(backToSyllabus).click();
        return new CourseSyllabusPage(driver);
    }

}
