package com.imc.automation.tests;

import com.imc.automation.pageobjectmodels.CoursesManager;
import com.imc.automation.pageobjectmodels.LoginPage;
import com.imc.automation.pageobjectmodels.ParticipantAdministration;
import com.imc.automation.pageobjectmodels.PersonalDashboardPage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class TabHandlingTest extends BaseTest{


    @Test
    public void BrowserTabHandlingTest() throws InterruptedException {
        driver.get("https://14-24-masterdb.imc-ms-deployment.imc-cs.com/ilp/pages/external-dashboard.jsf?menuId=1104&locale=en-GB&client=anonymous#/?dashboardId=6");
        LoginPage loginPageAgain = new LoginPage(driver);
        PersonalDashboardPage personalDashboardPageAdmin = loginPageAgain.login("aut0mati0nadmin!", "aut0mati0nadmin!");
        personalDashboardPageAdmin.switchToAdminCategory();
        CoursesManager courseManager = personalDashboardPageAdmin.goToCourses();
        Thread.sleep(6000);
        courseManager.searchCourses("DevOps Fundamentals Tools and Techniques");
        courseManager.clickSearchButton();
        courseManager.selectSearchedCourse();
        ParticipantAdministration participantAdministration = courseManager.openParticipantAdministration();
        Thread.sleep(3000);

        Set<String> windowHandles = driver.getWindowHandles();
        //System.out.println(windowHandles);
        List<String> browserTabs = new ArrayList<>(windowHandles);
        driver.switchTo().window(browserTabs.get(1));
        //System.out.println(driver.getCurrentUrl());
        participantAdministration.searchParticipant("didClientStaff12");
        participantAdministration.clickSearchButton();
        participantAdministration.cancelAnyParticipant("didClientStaff12");
        // go to cancelled tab
        participantAdministration.selectCancelledTab();
        participantAdministration.findAndRemoveCancelledParticipant("didClientStaff12");
        Thread.sleep(3000);
        participantAdministration.saveAndCloseParticipantAdministration();
        driver.switchTo().window(browserTabs.getFirst());
        courseManager.signOut();

    }



}
