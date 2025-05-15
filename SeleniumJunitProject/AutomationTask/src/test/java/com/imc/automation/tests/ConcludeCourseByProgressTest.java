package com.imc.automation.tests;

import com.imc.automation.pageobjectmodels.*;
import com.imc.automation.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ConcludeCourseByProgressTest extends BaseTest {

    @Test
    public void concludeCourseByProgress() {
        // Get system URL and user credentials
        String url = ConfigReader.getProperty("url");


        // #1 Access system
        driver.get(url);

        // #2 Login
        LoginPage loginPage = new LoginPage(driver);
        PersonalDashboardPage personalDashboardPage = loginPage.login("mystaffuser2012", "mystaffuser2012");
        // Verify login success
        Assertions.assertEquals(personalDashboardPage.dashBoardUrl, driver.getCurrentUrl());
        // verify catalog entry availability
        Assertions.assertTrue(personalDashboardPage.isCatalogEntryVisible(), "Catalog entry is not visible");
        // #3 Navigate to catalog page
        CatalogPage catalogPage = personalDashboardPage.goToCatalog();
        // #4 Search course
        catalogPage.searchCourse(ConfigReader.getProperty("searchTerm"));
        // check name of found course
        String courseName = catalogPage.getCourseName();
        Assertions.assertEquals("DevOps Fundamentals: Tools and Techniques for Continuous Integration and Deployment", courseName);
        // #5 open course - initiate CoursePage
        CoursePage coursePage = catalogPage.openCourse();
        // #6.1 check course name
        String courseNameDescription = coursePage.getCourseName();
        Assertions.assertEquals(courseNameDescription, courseName);
        // #6.2 check course image
        Assertions.assertTrue(coursePage.isCourseImageDisplayed(),"Course Image is not displayed");
        // #6.3 check breadcrumb
        Assertions.assertTrue(coursePage.isBreadCrumbDisplayed(),"Breadcrumb is not displayed");
        // #6.4 check course type
        Assertions.assertTrue(coursePage.isCourseTypeDisplayed(),"Course Type is not displayed");
        // #7 Enrol user to course - open syllabus
        assertTrue(coursePage.isBreadCrumbDisplayed(),"No Enroll button");
        CourseSyllabusPage courseSyllabusPage = coursePage.enrolUser();
        // #8 check course room elements
        // #8.1 course name
        String courseNameSyllabus = coursePage.getCourseName();
        Assertions.assertEquals("DevOps Fundamentals: Tools and Techniques for Continuous Integration and Deployment", courseNameSyllabus);
        // #8.2 course image
        Assertions.assertTrue(courseSyllabusPage.isCourseImageDisplayedSyllabus(),"Course Image is not displayed Syllabus");
        // #8.3 progress bar
        Assertions.assertTrue(courseSyllabusPage.courseRatingDisplayedSyllabus(),"Course Rating is not displayed Syllabus");
        // #8.4 course status
        Assertions.assertTrue(courseSyllabusPage.userStatusDisplayedSyllabus(),"Course Status is not displayed Syllabus");
        // #8.5 is cancel available
        Assertions.assertTrue(courseSyllabusPage.isCancelDisplayedSyllabus(),"Course Cancel button is not displayed Syllabus");
        // #9 medias visibility skipped because it will be interaction with the medias
        // #10 open MoocBild1
        MoocBild1 moocBild1 = courseSyllabusPage.openMoocBild1();
        // #11.1 check media name
        String actualMedia1Name = moocBild1.getMoocBild1Name();
        Assertions.assertTrue(actualMedia1Name.equalsIgnoreCase("Mooc Bild 1"), "Name is case insensitive ");
        // #11.2 display of next media button
        Assertions.assertTrue(moocBild1.isMediaDescriptionDisplayed(),"next button not present");
        // #11.3 display of media image
        assertTrue(moocBild1.isMoocBild1ImageDisplayed(),"media image missing");
        // #11.4 back to course button
        assertTrue(moocBild1.backToSyllabusDisplayed(),"Back to syllabus not displayed");
        // #11.5 media is passed
        String media1Status = moocBild1.media1Passed();
        Assertions.assertTrue(media1Status.equalsIgnoreCase("MOOC Bild 1 is successfully completed"), "Media not passed");
        // #12 back to syllabus
        CourseSyllabusPage courseSyllabusPage1 = moocBild1.backToCourseSyllabusPage();
        // 13 check status of 1st media
        String statusOfMedia1 = courseSyllabusPage1.getMedia1Status();
        Assertions.assertTrue(statusOfMedia1.equalsIgnoreCase("Status Successfully processed,"), "Media not passed");
        // #13.2 user progress value
        String progressPercentage = courseSyllabusPage1.getCourseProgressPercentage();
        Assertions.assertTrue(progressPercentage.equalsIgnoreCase("50%"), "Progress percentage is not present or different");
        // #14 opens 2nd media
        MoocBild2 moocBild2 = courseSyllabusPage.openMoocBild2();
        // #14.2 2nd media name
        String actualMedia1Name1 = moocBild1.getMoocBild1Name();
        Assertions.assertTrue(actualMedia1Name1.equalsIgnoreCase("Mooc Bild 2"), "Name is case insensitive ");
        // #14.3 2nd media image
        assertTrue(moocBild2.isMoocBild1ImageDisplayed(),"media image missing");
        // #14.4 2nd media status
        String media2Status = moocBild2.media2Passed();
        Assertions.assertTrue(media2Status.equalsIgnoreCase("MOOC Bild 2 is successfully completed"), "Media not passed");
        // #15 back to course syllabus
        CourseSyllabusPage courseSyllabusPage2 = moocBild1.backToCourseSyllabusPage();
        // #15.1 check user status
        String userStatusAfterCompletingTwoMedias = courseSyllabusPage2.getCourseStatus();
        assertTrue(userStatusAfterCompletingTwoMedias.equalsIgnoreCase("passed"), "Status not passed");
        // #16 logout
        courseSyllabusPage2.signOut();
        // #17Login admin
        driver.navigate().to(url);
        LoginPage loginPageAgain = new LoginPage(driver);
        PersonalDashboardPage personalDashboardPageAdmin = loginPageAgain.login("aut0mati0nadmin!", "aut0mati0nadmin!");
        // verify catalog entry availability

        Assertions.assertTrue(personalDashboardPageAdmin.isCatalogEntryVisible(), "Catalog entry is not visible");
        // #18 Switch to admin category
        personalDashboardPageAdmin.switchToAdminCategory();
        // admin navi displayed - content menu
        assertTrue(personalDashboardPageAdmin.isContentMenuVisible(), "Catalog entry is not visible");
        // #19 open course manager
        CoursesManager courseManager = personalDashboardPageAdmin.goToCourses();
        // #19.1 toolbar, courses area displayed
        assertTrue(courseManager.createButtonIsDisplayed(), "Create button is not visible");
        assertTrue(courseManager.coursesTableIsDisplayed(), "Courses table is not displayed");
        // #20 input search term and search
        courseManager.searchCourses("DevOps Fundamentals Tools and Techniques");
        courseManager.clickSearchButton();
        // check results
        assertTrue(courseManager.courseToBeFoundDisplayed(), "Course not found");
        // #21 select course
        courseManager.selectSearchedCourse();
        // #22 open participant administration
        String originalWindow = driver.getWindowHandle();
        Set<String> existingWindows = driver.getWindowHandles();
        ParticipantAdministration participantAdministration = courseManager.openParticipantAdministration();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> driver1.getWindowHandles().size() > existingWindows.size());
        Set<String> updatedWindows = driver.getWindowHandles();
        updatedWindows.removeAll(existingWindows);
        String newTabHandle = updatedWindows.iterator().next();
        driver.switchTo().window(newTabHandle);

        assertTrue(participantAdministration.isUlrCorrect(), "no");
        //System.out.println(driver.getPageSource());
        // #23 search user
        participantAdministration.searchParticipant("mystaffuser2012");
        participantAdministration.clickSearchButton();
        // #24 check if user is found in the user list
        assertTrue(participantAdministration.isParticipantFound("mystaffuser2012"), "mystaffuser2012 not found");
        // #24 select and cancel user
        participantAdministration.cancelParticipant();
        // #25 move to cancel register
        participantAdministration.openCancellationRegister();
        // #26 search find remove user

        assertTrue(participantAdministration.isSearchBoxCancellationVisible(), "Search box should be visible");
        participantAdministration.searchCanceledParticipant("mystaffuser2012");
        participantAdministration.selectCanceledUser("mystaffuser2012");
        participantAdministration.removeParticipant();
        participantAdministration.switchToToolbarFrame();
        By saveAndCloseButton = By.xpath("//a[@data-qtip='Save and close']");
        String iframeToClose = "iframe_participants.3.900507";
        participantAdministration.clickAndWaitForIframeToClose(saveAndCloseButton, iframeToClose);


    }
}







