package com.imc.automation.tests;

import com.imc.automation.pageobjectmodels.*;
import com.imc.automation.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DenialOfAccessTest extends BaseTest {

    String url = ConfigReader.getProperty("url");

    //Locators - pleas God help


    @Test
    public void DenialOfAccess() throws InterruptedException {

        // #1 Access system
        driver.get(url);

        // #2 1st bad Login
        LoginPage loginPage = new LoginPage(driver);
        FailedLoginPage failedLoginPage = loginPage.loginUnsuccessful("mystaffuser2015", "mystaffuser2016");
        failedLoginPage.getErrorMessage();
        Thread.sleep(1000);
        assertEquals("The login/password combination is not correct!", failedLoginPage.getErrorMessage());
        // #3 2nd bad login
        failedLoginPage.enterUsername("mystaffuser2015");
        failedLoginPage.enterWrongPassword("mystaffuser2016");
        failedLoginPage.clickLoginButton();
        Thread.sleep(1000);
        assertEquals("The login/password combination is not correct!", failedLoginPage.getErrorMessage());
        // 3rd bad login
        failedLoginPage.enterUsername("mystaffuser2015");
        failedLoginPage.enterWrongPassword("mystaffuser2016");
        failedLoginPage.clickLoginButton();
        Thread.sleep(1000);
        assertEquals("The login/password combination is not correct!", failedLoginPage.getErrorMessage());
        // 3rd bad login
        failedLoginPage.enterUsername("mystaffuser2015");
        failedLoginPage.enterWrongPassword("mystaffuser2016");
        failedLoginPage.clickLoginButton();
        Thread.sleep(1000);
        assertEquals("The login/password combination is not correct!", failedLoginPage.getErrorMessage());
        // 3rd bad login
        failedLoginPage.enterUsername("mystaffuser2015");
        failedLoginPage.enterWrongPassword("mystaffuser2016");
        failedLoginPage.clickLoginButton();
        Thread.sleep(1000);
        assertEquals("Either this user name does not exist in our system or it has been blocked. Please contact your system administrator.", failedLoginPage.getErrorMessage());

        //admin part
        // #4 login admin
        driver.get(url);
        LoginPage loginPage1 = new LoginPage(driver);
        PersonalDashboardPage personalDashboardPage = loginPage1.login("aut0mati0nadmin!", "aut0mati0nadmin!");
        // #5 open navi search
        personalDashboardPage.openSearchNavigationFieldAfterClosingNotification();
        // #6 enter term in search navi
        personalDashboardPage.enterSearchTermNavigation("Denial of Access");
        // #7 open denial of access manager
        DenialOfAccessManager denialOfAccessManager = personalDashboardPage.accessDenialOfAccsessManager();
        // #8 unlock user
        denialOfAccessManager.unlockUser("mystaffuser2015");
        // #9 logout admin
        denialOfAccessManager.signOut();
        driver.manage().deleteAllCookies();
        // #10 login user
        driver.get(url);
        PersonalDashboardPage personalDashboardPage1 = loginPage1.login("mystaffuser2015","mystaffuser2015");
        assertTrue(personalDashboardPage1.signOutButtonAvailable(),"Sign Out Button is not available");



    }
}