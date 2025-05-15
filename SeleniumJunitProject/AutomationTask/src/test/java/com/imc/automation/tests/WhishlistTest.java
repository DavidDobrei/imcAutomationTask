package com.imc.automation.tests;

import com.imc.automation.pageobjectmodels.*;
import com.imc.automation.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WhishlistTest extends BaseTest {
    String username = "mystaffuser2012";
    String password = "mystaffuser2012";
    String usernameAdmin = "aut0mati0nadmin!";
    String passwordAdmin = "aut0mati0nadmin!";
    String syllabusUrl = "https://14-24-masterdb.imc-ms-deployment.imc-cs.com/ilp/pages/course.jsf?courseId=900516&pollingMode=on&runningLanguage=en-GB&client=clients#!/courseroom/course";
    String url = ConfigReader.getProperty("url");
    //Locators - pleas God help


    @Test
    public void whishlistTest() {
        // #1 Access system
        driver.get(url);

        // #2 Login
        LoginPage loginPage = new LoginPage(driver);
        PersonalDashboardPage personalDashboardPage = loginPage.login("mystaffuser2016", "mystaffuser2016");
        // Verify login success
        Assertions.assertEquals(personalDashboardPage.dashBoardUrl, driver.getCurrentUrl());
        // verify catalog entry availability
        Assertions.assertTrue(personalDashboardPage.isCatalogEntryVisible(), "Catalog entry is not visible");
        // #3 Navigate to catalog page
        CatalogPage catalogPage = personalDashboardPage.goToCatalog();
        // #4 Search course
        catalogPage.searchCourse("Course SD New Course Room");
        // check name of found course
        String courseName = catalogPage.getWishlistCourseName();
        Assertions.assertEquals("[TD]Course SD New Course Room", courseName);
        // #5 open course - initiate CoursePage
        CoursePage coursePage = catalogPage.openCourse();
        assertTrue(coursePage.isCourseImageDisplayed(),"Course Image is not displayed");
        assertTrue(coursePage.isBreadCrumbDisplayed(),"Course Image is not displayed");
        assertTrue(coursePage.isEnrollButtonDisplayed(),"Course Image is not displayed");
        Assertions.assertEquals("[TD]Course SD New Course Room", coursePage.getCourseName());
        // #6 add to wishlist
        coursePage.addToWishlist();
        // #8 open learning status
        LearningStatus learningStatus= coursePage.openLearningStatus();
        // are registered displayed
        assertTrue(learningStatus.isCurrentDisplayed(),"Current not displayed");
        assertTrue(learningStatus.isCurrentPendingDisplayed(),"Pending not displayed");
        assertTrue(learningStatus.isRecommendedDisplayed(),"Recommended not displayed");
        assertTrue(learningStatus.isCompletedDisplayed(),"Completed not displayed");
        assertTrue(learningStatus.isCancelledDisplayed(),"Cancelled not displayed");
        assertTrue(learningStatus.isAllDisplayed(),"All not displayed");
        assertTrue(learningStatus.isWishlistDisplayed(),"Wishlist not displayed");
        // #9 selesct whishlist
        learningStatus.selectWishlist();
        // #10 open course description page
        // Search course
        learningStatus.searchCourse(courseName);
        assertTrue(learningStatus.isCourseVisible(courseName), "Course is not visible");
        // open course
        CourseDescriptionPage courseDescriptionPage = learningStatus.openCourse(courseName);
        assertEquals(courseDescriptionPage.courseNamePageDescription(courseName),courseName,"Name not the same");
        //remove from wishlist
        courseDescriptionPage.removeCourseFromWishlist();
        // check that course is not on the wishlist
        assertTrue(courseDescriptionPage.addToWishlistButtonVisible(), "Add to wish list displayed");

        // check in learning status also
        LearningStatus learningStatus1 = courseDescriptionPage.openLearningStatus();
        learningStatus1.selectWishlist();
        learningStatus1.searchCourse(courseName);
        assertFalse(learningStatus1.isCourseVisible(courseName), "Course is not visible");
        //sign out
        learningStatus1.signOut();


    }
}