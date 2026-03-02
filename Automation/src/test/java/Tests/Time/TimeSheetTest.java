package Tests.Time;

import Data.Data;
import Pages.NavBar;
import Pages.Time.TimeSheetPage;
import Pages.loginPage;

import Pages.logoutPage;
import base.base;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TimeSheetTest extends base {
    loginPage Loginpage;
    NavBar navBar;
    TimeSheetPage timeSheetPage;
    logoutPage LogoutPage;

    @BeforeClass
    public void setup(){
        LogoutPage = new logoutPage(driver,wait);
        Loginpage = new loginPage(wait, driver);
        navBar=new NavBar(driver);
        timeSheetPage=new TimeSheetPage(driver,wait);


    }
    @Test
    public void Employee_TimeSheet(){
        //login
        Assert.assertEquals(Loginpage.login(Data.username,Data.password ),"Dashboard");
        navBar.goToSideMenu("Time");
        Assert.assertEquals(timeSheetPage.search(Data.employeeName1),"Timesheet for Shaima Saeed");
        timeSheetPage.EditTimeSheet();
        //logout
        Assert.assertEquals(LogoutPage.logout(),"Login");
    }


}


