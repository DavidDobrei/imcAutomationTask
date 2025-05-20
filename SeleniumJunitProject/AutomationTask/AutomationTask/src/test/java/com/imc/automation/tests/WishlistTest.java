package com.imc.automation.tests;

import com.imc.automation.pageobjectmodels.*;
import com.imc.automation.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WishlistTest extends BaseTest {
    //Locators - pleas God help
    String username = "mystaffuser2012";
    String password = "mystaffuser2012";
    String usernameAdmin = "aut0mati0nadmin!";
    String passwordAdmin = "aut0mati0nadmin!";
    String syllabusUrl = "https://14-24-masterdb.imc-ms-deployment.imc-cs.com/ilp/pages/course.jsf?courseId=900516&pollingMode=on&runningLanguage=en-GB&client=clients#!/courseroom/course";
    String url = ConfigReader.getProperty("url");


    private static final Logger logger = LogManager.getLogger(WishlistTest.class);



    @Test
    public void wishlistTest() throws InterruptedException {
        logger.info("Starting wishlistTest");

        // #1 Access system
        logger.info("Navigating to system URL: {}", url);
        driver.get(url);

        // #2 Login
        logger.info("Attempting login as 'mystaffuser2016'");
        LoginPage loginPage = new LoginPage(driver);
        PersonalDashboardPage personalDashboardPage = loginPage.login("mystaffuser2016", "mystaffuser2016");
        Assertions.assertEquals(personalDashboardPage.dashBoardUrl, driver.getCurrentUrl());
        logger.info("Login successful, verifying dashboard elements");
        Assertions.assertTrue(personalDashboardPage.isCatalogEntryVisible(), "Catalog entry is not visible");

        // #3 Navigate to catalog
        logger.info("Navigating to catalog page");
        CatalogPage catalogPage = personalDashboardPage.goToCatalog();

        // #4 Search for course
        logger.info("Searching for course: Course SD New Course Room");
        catalogPage.searchCourse("Course SD New Course Room");
        String courseName = catalogPage.getWishlistCourseName();
        logger.info("Course found: {}", courseName);
        Assertions.assertEquals("[TD]Course SD New Course Room", courseName);

        // #5 Open course
        logger.info("Opening course from catalog");
        CoursePage coursePage = catalogPage.openCourseWishlist();
        Assertions.assertTrue(coursePage.isCourseImageDisplayed(), "Course Image is not displayed");
        Assertions.assertTrue(coursePage.isBreadCrumbDisplayed(), "Breadcrumb is not displayed");
        Assertions.assertTrue(coursePage.isEnrollButtonDisplayed(), "Enroll button is not displayed");
        Assertions.assertEquals("[TD]Course SD New Course Room", coursePage.getCourseName());

        // #6 Add course to wishlist
        logger.info("Adding course to wishlist");
        coursePage.addToWishlist();

        // #8 Open learning status
        logger.info("Opening learning status from course page");
        LearningStatus learningStatus = coursePage.openLearningStatus();
        Assertions.assertTrue(learningStatus.isCurrentDisplayed(), "Current not displayed");
        Assertions.assertTrue(learningStatus.isCurrentPendingDisplayed(), "Pending not displayed");
        Assertions.assertTrue(learningStatus.isRecommendedDisplayed(), "Recommended not displayed");
        Assertions.assertTrue(learningStatus.isCompletedDisplayed(), "Completed not displayed");
        Assertions.assertTrue(learningStatus.isCancelledDisplayed(), "Cancelled not displayed");
        Assertions.assertTrue(learningStatus.isAllDisplayed(), "All not displayed");
        Assertions.assertTrue(learningStatus.isWishlistDisplayed(), "Wishlist not displayed");

        // #9 Select wishlist tab
        logger.info("Selecting 'Wishlist' tab in learning status");
        learningStatus.selectWishlist();

        // #10 Search and open course from wishlist
        logger.info("Searching for course '{}' in wishlist", courseName);
        learningStatus.searchCourse(courseName);
        Assertions.assertTrue(learningStatus.isCourseVisible(courseName), "Course is not visible in wishlist");

        logger.info("Opening course description page from wishlist");
        CourseDescriptionPage courseDescriptionPage = learningStatus.openCourse(courseName);
        Assertions.assertEquals(courseDescriptionPage.courseNamePageDescription(courseName), courseName, "Course name mismatch");

        // Remove from wishlist
        logger.info("Removing course from wishlist");
        courseDescriptionPage.removeCourseFromWishlist();
        Assertions.assertTrue(courseDescriptionPage.addToWishlistButtonVisible(), "Add to wishlist button not visible after removal");

        // Verify removal in learning status
        logger.info("Verifying course removal from wishlist via learning status");
        LearningStatus learningStatus1 = courseDescriptionPage.openLearningStatus();
        learningStatus1.selectWishlist();
        learningStatus1.searchCourse(courseName);
        Assertions.assertFalse(learningStatus1.isCourseVisible(courseName), "Course still visible in wishlist");

        // Sign out
        logger.info("Signing out");
        learningStatus1.signOut();

        logger.info("wishlistTest completed successfully");
    }

}