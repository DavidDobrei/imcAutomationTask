package com.imc.automation.pageobjectmodels;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalDashboardPage extends BasePage {


    //Locators
    private By catalogButton = By.id("1108");
    private By contentButton = By.xpath("//*[@id=\"navigationRoot\"]/div/div[1]/nav/div/div[2]/div[1]/ul/li[4]/div/button");
    private By courseManager = By.xpath("//a[@href='/ilp/pages/ils-redirect.jsf?menuId=1156&locale=en-GB']");
    private By welcomeMessage = By.xpath("//div[contains(@class, 'ui-notification') and contains(@class, 'info') and @role='alert']");
    private By closeWelcomeMessageButton = By.xpath("//a[@title='Close notification' and contains(@class, 'close-icon')]");
    private By categoriesSwitch = By.xpath("//*[@id=\"navigationRoot\"]/div/div[1]/nav/div/div[2]/div[2]/ul/li[5]/div/button");
    private By categoryAdmin = By.xpath("//*[@id=\":ro:\"]/div/ul/li[5]/button");
    private By categoryLearner = By.xpath("//*[@id=\":ro:\"]/div/ul/li[1]/button");
    private By image = By.xpath("//*[@id=\"slide-show-section-820805\"]/ul/li[2]/figure/img");
    private By navigationSearch = By.xpath("//button[@title='Search']");
    private By navigationSearchField = By.xpath("//input[@placeholder='Search']");
    private By denialOfAccessSearchResult = By.xpath("//a[.//span[text()='Denial of access']]");
    private By userProfile = By.className("avatarWrapper");
    private By signOutButton = By.xpath("//*[@id=\":rc:\"]/div/div[2]/ul[2]/li/a");



    public PersonalDashboardPage(WebDriver driver) {
        super(driver);
    }

//Navigate to catalog - return catalog page
    public String dashBoardUrl = driver.getCurrentUrl();
    public CatalogPage goToCatalog() {
        //WebElement catalog = driver.findElement(catalogButton);
        //click(catalog);
        waitForElement(catalogButton).click();
        return new CatalogPage(driver);
    }
    public boolean isCatalogEntryVisible() {
        //WebElement catalog = driver.findElement(catalogButton);
        //waitForVisibility(catalog);
        return waitForElement(catalogButton).isDisplayed();
    }

    public boolean isAdminCategoryVisible() {
        //WebElement categoryAdminElement = driver.findElement(categoryAdmin);
        //waitForVisibility(categoryAdminElement);
        return waitForElement(categoryAdmin).isDisplayed();
    }
    public boolean isLearnerCategoryVisible() {
        //WebElement categoryLearnerElement = driver.findElement(categoryLearner);
        //waitForVisibility(categoryLearnerElement);
        return waitForElement(categoryLearner).isDisplayed();
    }
    // open category switch
    public void clickCategoriesSwitch() {
        //wait.until(ExpectedConditions.elementToBeClickable(categoriesSwitch));
        //driver.findElement(categoriesSwitch).click();
        waitForElementToBeClickable(categoriesSwitch).click();
    }
    // switch from learner to admin
    public void switchToAdminCategory() {
        clickCategoriesSwitch();
        wait.until(ExpectedConditions.elementToBeClickable(categoryAdmin));
        WebElement categoryAdminElement = driver.findElement(categoryAdmin);
        categoryAdminElement.click();
    }
    // Content navigation entry
    public boolean isContentMenuVisible() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(contentButton));
        return waitForElement(contentButton).isDisplayed();
    }
    // navigation entry course manager
    public CoursesManager goToCourses() {
        WebElement contentButtonElement = driver.findElement(contentButton);
        contentButtonElement.click();
        WebElement courseManagerElement = wait.until(ExpectedConditions.elementToBeClickable(courseManager));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", courseManagerElement);
        courseManagerElement.click();
        return new CoursesManager(driver);
    }
    // open navigation search
    public void openSearchNavigationFieldAfterClosingNotification() {
        if (isDisplayed(closeWelcomeMessageButton)) {
            waitForElementToBeClickable(closeWelcomeMessageButton).click();
            openSearchNavigationField();
        } else {
            openSearchNavigationField();
        }
    }

    // open navigation search
    public void openSearchNavigationField() {
        waitForElementToBeClickable(navigationSearch).click();
    }
    // input term in navigation search
    public void enterSearchTermNavigation (String searchTerm) {
        waitForElement(navigationSearchField).sendKeys(searchTerm);
    }
    // open denial of access manager
    public DenialOfAccessManager accessDenialOfAccsessManager () {
        waitForElementToBeClickable(denialOfAccessSearchResult).click();
        return new DenialOfAccessManager(driver);
    }
    // sign out button is visible
    public boolean signOutButtonAvailable () {
        waitForElementToBeClickable(userProfile).click();
        return driver.findElement(userProfile).isDisplayed();
    }
}
