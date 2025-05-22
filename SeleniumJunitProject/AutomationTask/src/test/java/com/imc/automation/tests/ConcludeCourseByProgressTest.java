package com.imc.automation.tests;

import com.imc.automation.pageobjectmodels.*;
import com.imc.automation.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcludeCourseByProgressTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(DenialOfAccessTest.class);

    @Test
    public void concludeCourseByProgressTest() throws InterruptedException {
        String url = ConfigReader.getProperty("url");
        logger.info("Starting ConcludeCourseByProgress test");

        // #1 Access system
        logger.info("Navigating to URL: {}", url);
        driver.get(url);

        // #2 Login
        logger.info("Loading login page");
        LoginPage loginPage = new LoginPage(driver);
        logger.info("Attempting to log in as mystaffuser2012");
        PersonalDashboardPage personalDashboardPage = loginPage.login("mystaffuser2012", "mystaffuser2012");
        logger.info("Login successful, verifying dashboard URL and catalog entry");
        Assertions.assertEquals(personalDashboardPage.dashBoardUrl, driver.getCurrentUrl());
        Assertions.assertTrue(personalDashboardPage.isCatalogEntryVisible(), "Catalog entry is not visible");

        // #3 Navigate to catalog page
        logger.info("Navigating to catalog page");
        CatalogPage catalogPage = personalDashboardPage.goToCatalog();

        // #4 Search course
        logger.info("Searching for course: DevOps Fundamentals Tools and Techniques");
        catalogPage.searchCourse("DevOps Fundamentals Tools and Techniques");
        Thread.sleep(3000);
        String courseName = catalogPage.getCourseName();
        logger.info("Course found: {}", courseName);
        Assertions.assertEquals("DevOps Fundamentals: Tools and Techniques for Continuous Integration and Deployment", courseName);

        // #5 Open course
        logger.info("Opening course page");
        CoursePage coursePage = catalogPage.openCourse();

        // #6 Course details checks
        logger.info("Verifying course page UI elements");
        Assertions.assertEquals(coursePage.getCourseName(), courseName);
        Assertions.assertTrue(coursePage.isCourseImageDisplayed(),"Course Image is not displayed");
        Assertions.assertTrue(coursePage.isBreadCrumbDisplayed(),"Breadcrumb is not displayed");
        Assertions.assertTrue(coursePage.isCourseTypeDisplayed(),"Course Type is not displayed");

        // #7 Enroll user
        logger.info("Enrolling user to course");
        Assertions.assertTrue(coursePage.isBreadCrumbDisplayed(),"No Enroll button");
        CourseSyllabusPage courseSyllabusPage = coursePage.enrolUser();

        // #8 Syllabus checks
        logger.info("Verifying course syllabus UI elements");
        Assertions.assertEquals(coursePage.getCourseName(), "DevOps Fundamentals: Tools and Techniques for Continuous Integration and Deployment");
        Assertions.assertTrue(courseSyllabusPage.isCourseImageDisplayedSyllabus(),"Course Image is not displayed in syllabus");
        Assertions.assertTrue(courseSyllabusPage.courseRatingDisplayedSyllabus(),"Course Rating is not displayed");
        Assertions.assertTrue(courseSyllabusPage.userStatusDisplayedSyllabus(),"Course Status is not displayed");
        Assertions.assertTrue(courseSyllabusPage.isCancelDisplayedSyllabus(),"Cancel button is not displayed");

        // #10 Open MoocBild1
        logger.info("Opening first media: Mooc Bild 1");
        MoocBild1 moocBild1 = courseSyllabusPage.openMoocBild1();
        Assertions.assertTrue(moocBild1.getMoocBild1Name().equalsIgnoreCase("Mooc Bild 1"), "Name is case insensitive ");
        Assertions.assertTrue(moocBild1.isMediaDescriptionDisplayed(),"Next button not present");
        Assertions.assertTrue(moocBild1.isMoocBild1ImageDisplayed(),"Media image missing");
        Assertions.assertTrue(moocBild1.backToSyllabusDisplayed(),"Back to syllabus not displayed");
        logger.info("First media completed");
        Assertions.assertTrue(moocBild1.media1Passed().equalsIgnoreCase("MOOC Bild 1 is successfully completed"), "Media not passed");

        // #12 Return to syllabus and check progress
        CourseSyllabusPage courseSyllabusPage1 = moocBild1.backToCourseSyllabusPage();
        logger.info("Checking status of first media");
        Assertions.assertTrue(courseSyllabusPage1.getMedia1Status().equalsIgnoreCase("Status Successfully processed,"), "Media not passed");
        Assertions.assertTrue(courseSyllabusPage1.getCourseProgressPercentage().equalsIgnoreCase("50%"), "Progress percentage mismatch");

        // #14 Open MoocBild2
        logger.info("Opening second media: Mooc Bild 2");
        MoocBild2 moocBild2 = courseSyllabusPage.openMoocBild2();
        Assertions.assertTrue(moocBild1.getMoocBild1Name().equalsIgnoreCase("Mooc Bild 2"), "Media name mismatch");
        Assertions.assertTrue(moocBild2.isMoocBild1ImageDisplayed(),"Second media image missing");
        Assertions.assertTrue(moocBild2.media2Passed().equalsIgnoreCase("MOOC Bild 2 is successfully completed"), "Second media not passed");

        // #15 Return to syllabus and verify user status
        CourseSyllabusPage courseSyllabusPage2 = moocBild1.backToCourseSyllabusPage();
        logger.info("Verifying user course status after completing both media");
        Assertions.assertTrue(courseSyllabusPage2.getCourseStatus().equalsIgnoreCase("passed"), "Course status is not 'passed'");

        // #16 Logout
        logger.info("Signing out staff user");
        courseSyllabusPage2.signOut();

        // #17 Login as admin
        logger.info("Re-logging as admin");
        driver.navigate().to(url);
        LoginPage loginPageAgain = new LoginPage(driver);
        PersonalDashboardPage personalDashboardPageAdmin = loginPageAgain.login("aut0mati0nadmin!", "aut0mati0nadmin!");
        Assertions.assertTrue(personalDashboardPageAdmin.isCatalogEntryVisible(), "Catalog entry not visible for admin");

        // #18 Admin view: navigate to course manager
        logger.info("Switching to admin category and accessing course manager");
        personalDashboardPageAdmin.switchToAdminCategory();
        Assertions.assertTrue(personalDashboardPageAdmin.isContentMenuVisible(), "Content menu not visible");
        CoursesManager courseManager = personalDashboardPageAdmin.goToCourses();
        Thread.sleep(6000);
        Assertions.assertTrue(courseManager.createButtonIsDisplayed(), "Create button missing");
        Assertions.assertTrue(courseManager.coursesTableIsDisplayed(), "Courses table missing");

        // #20 Search course as admin
        logger.info("Searching for course as admin");
        courseManager.searchCourses("DevOps Fundamentals Tools and Techniques");
        courseManager.clickSearchButton();
        Thread.sleep(6000);
        Assertions.assertTrue(courseManager.courseToBeFoundDisplayed(), "Course not found in admin panel");

        // #21-22 Participant admin
        logger.info("Opening participant administration for selected course");
        courseManager.selectSearchedCourse();

        ParticipantAdministration participantAdministration = courseManager.openParticipantAdministration();
        Thread.sleep(3000);
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> browserTabs = new ArrayList<>(windowHandles);

        driver.switchTo().window(browserTabs.get(1));
        Assertions.assertEquals("https://14-24-masterdb.imc-ms-deployment.imc-cs.com/ils/navigation/administrator/content_folder/content_course_folder/content_course_manager/participants.3.900507",participantAdministration.currentUrl());

        // #23-24 Cancel and remove user
        logger.info("Searching and cancelling participant: mystaffuser2012");
        participantAdministration.searchParticipant("mystaffuser2012");
        participantAdministration.clickSearchButton();
        Thread.sleep(1000);
        Assertions.assertTrue(participantAdministration.isParticipantFound("mystaffuser2012"), "User not found in participant list");
        participantAdministration.cancelAnyParticipant("mystaffuser2012");

        logger.info("Opening cancellation register and removing participant");
        participantAdministration.selectCancelledTab();
        //Assertions.assertTrue(participantAdministration.isSearchBoxCancellationVisible(), "Cancellation search box missing");
        participantAdministration.findAndRemoveCancelledParticipant("mystaffuser2012");
        //participantAdministration.selectCanceledUser("mystaffuser2012");
        //participantAdministration.removeParticipant();

        logger.info("Saving and closing participant admin window");
        participantAdministration.saveAndCloseParticipantAdministration();
        //By saveAndCloseButton = By.xpath("//a[@data-qtip='Save and close']");
        //String iframeToClose = "iframe_participants.3.900507";
        //participantAdministration.clickAndWaitForIframeToClose(saveAndCloseButton, iframeToClose);

        logger.info("Logout admin");
        Thread.sleep(3000);
        //participantAdministration.saveAndCloseParticipantAdministration();
        driver.switchTo().window(browserTabs.getFirst());
        courseManager.signOut();
    }
}







