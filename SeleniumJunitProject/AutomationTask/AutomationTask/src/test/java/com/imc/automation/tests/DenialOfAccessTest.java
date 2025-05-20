package com.imc.automation.tests;

import com.imc.automation.pageobjectmodels.*;
import com.imc.automation.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DenialOfAccessTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(DenialOfAccessTest.class);
    String url = ConfigReader.getProperty("url");





    @Test
    public void DenialOfAccess() throws InterruptedException {
        logger.info("Starting DenialOfAccess test");

        // #1 Access system
        logger.info("Navigating to system URL: {}", url);
        driver.get(url);

        // #2 First failed login attempt
        logger.info("Performing first failed login attempt for user 'mystaffuser2015'");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookiePolicy();
        FailedLoginPage failedLoginPage = loginPage.loginUnsuccessful("mystaffuser2015", "mystaffuser2016");
        Thread.sleep(1000);
        String error1 = failedLoginPage.getErrorMessage();
        logger.info("Error after 1st failed login: {}", error1);
        assertEquals("The login/password combination is not correct!", error1);

        // #3 Second failed login attempt
        logger.info("Performing second failed login attempt");
        failedLoginPage.enterUsername("mystaffuser2015");
        failedLoginPage.enterWrongPassword("mystaffuser2016");
        failedLoginPage.clickLoginButton();
        Thread.sleep(1000);
        String error2 = failedLoginPage.getErrorMessage();
        logger.info("Error after 2nd failed login: {}", error2);
        assertEquals("The login/password combination is not correct!", error2);

        // #4 Third failed login attempt
        logger.info("Performing third failed login attempt");
        failedLoginPage.enterUsername("mystaffuser2015");
        failedLoginPage.enterWrongPassword("mystaffuser2016");
        failedLoginPage.clickLoginButton();
        Thread.sleep(1000);
        String error3 = failedLoginPage.getErrorMessage();
        logger.info("Error after 3rd failed login: {}", error3);
        assertEquals("The login/password combination is not correct!", error3);

        // #5 Fourth failed login - account should be locked
        logger.info("Performing fourth login attempt which should result in account lock");
        failedLoginPage.enterUsername("mystaffuser2015");
        failedLoginPage.enterWrongPassword("mystaffuser2016");
        failedLoginPage.clickLoginButton();
        Thread.sleep(1000);
        String lock4 = failedLoginPage.getErrorMessage();
        logger.info("Error after account lock: {}", lock4);
        assertEquals("Either this user name does not exist in our system or it has been blocked. Please contact your system administrator.", lock4);
/*
        // #5 Fourth failed login - account should be locked
        logger.info("Performing fourth login attempt which should result in account lock");
        failedLoginPage.enterUsername("mystaffuser2015");
        failedLoginPage.enterWrongPassword("mystaffuser2016");
        failedLoginPage.clickLoginButton();
        Thread.sleep(1000);
        String lockError = failedLoginPage.getErrorMessage();
        logger.info("Error after account lock: {}", lockError);
        assertEquals("Either this user name does not exist in our system or it has been blocked. Please contact your system administrator.", lockError);
*/
        // Admin unlock process
        logger.info("Logging in as admin to unlock the user");
        driver.get(url);
        LoginPage loginPage1 = new LoginPage(driver);
        PersonalDashboardPage personalDashboardPage = loginPage1.login("aut0mati0nadmin!", "aut0mati0nadmin!");

        logger.info("Opening admin search navigation");
        personalDashboardPage.openSearchNavigationFieldAfterClosingNotification();

        logger.info("Searching for 'Denial of Access' module");
        personalDashboardPage.enterSearchTermNavigation("Denial of Access");

        logger.info("Accessing Denial of Access Manager");
        DenialOfAccessManager denialOfAccessManager = personalDashboardPage.accessDenialOfAccsessManager();

        logger.info("Unlocking user 'mystaffuser2015'");
        denialOfAccessManager.unlockUser("mystaffuser2015");

        logger.info("Logging out admin");
        denialOfAccessManager.signOut();
        driver.manage().deleteAllCookies();

        // Final verification
        logger.info("Attempting login with unlocked user 'mystaffuser2015'");
        driver.get(url);
        PersonalDashboardPage personalDashboardPage1 = loginPage1.login("mystaffuser2015","mystaffuser2015");
        boolean signOutVisible = personalDashboardPage1.signOutButtonAvailable();
        logger.info("User login successful: {}", signOutVisible);
        assertTrue(signOutVisible, "Sign Out Button is not available");

        logger.info("DenialOfAccess test completed successfully");
    }

}