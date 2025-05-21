package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MoocBild2 extends BasePage{
    //Locators
    private By moocBild1Name = By.id("course-content-header-text");
    private By moocBild1Image = By.className("image-centered");
    private By backToSyllabus = By.id("back-to-courseroom-link");
    private By nextMedia = By.id("nextForumComponentBtn");
    private By mediaDescription = By.id("content-description");
    private By passedMediaIcon = By.xpath("//*[@id=\"course-content\"]/div[1]/div/div[1]/div/div/div");
    //private By courseProgress = By.className("dir-header__content-container-course__left__progress__bar");
    //private By media1 = By.xpath("//*[@id=\"tileAnchorFor_Syllabus_750725_0\"]/div[1]/div[1]/div[2]/div[1]");
    //private By media2 = By.xpath("//*[@id=\"tileAnchorFor_Syllabus_540024_0\"]/div[1]/div[1]/div[2]/div[1]");

    //Constructor
    public MoocBild2(WebDriver driver) {
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
    public String media2Passed() {
        //WebElement passedMediaIconelement = driver.findElement(passedMediaIcon);
        //waitForVisibility(passedMediaIconelement);
        return waitForElement(passedMediaIcon).getDomAttribute("title");
    }
}

