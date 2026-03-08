package Tests.Leave;

import Pages.Leave.LeavePage;
import Pages.NavBar;
import Pages.loginPage;
import Pages.logoutPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.base;
import Data.Data;

import static java.lang.Thread.sleep;

public class leaveTest extends base{
    loginPage Loginpage;
    NavBar navBar;
    LeavePage leavePage;
    logoutPage logoutPage;
    @BeforeClass
    public void start()
    {
        Loginpage=new loginPage(wait,driver);
        navBar=new NavBar(driver);
        leavePage= new LeavePage(driver,wait);
        logoutPage=new logoutPage(driver,wait);
    }



    @Test
    public void add_Entitlement_To_Single_Employee()
    {

        Assert.assertEquals(navBar.goToSideMenu("Leave"),Data.getLeaveListURL());
        navBar.goToTopbar("Entitlements","Add Entitlements");
        leavePage.selectEmployee(Data.employeeName);
        leavePage.addDays(2);
        leavePage.selectLeaveType();
        Assert.assertEquals(leavePage.clickSaveBtn(),"Success");


    }

    @Test(priority = 1)
    public void Add_Multiple()
    {

        Assert.assertEquals(navBar.goToSideMenu("Leave"),Data.getLeaveListURL());
        navBar.goToTopbar("Entitlements","Add Entitlements");
        leavePage.selectMultiple();
        leavePage.selectLeaveTypeMultiple();
        leavePage.addDays(2);
        Assert.assertEquals(leavePage.clickSaveBtn(),"Success");
    }



}