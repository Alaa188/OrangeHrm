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


//    @Test
//    public void Login_go_to_Leave() {
//        Assert.assertEquals(Loginpage.login(Data.myusername,Data.mypassword ),"Dashboard");
//        Assert.assertEquals(navBar.goToSideMenu("Leave"),Data.getLeaveListURL());
//        navBar.goToTopbar("Entitlements","Add Entitlements");
//    }

    @Test
    public void add_Entitlement_To_Single_Employee()
    {
        Assert.assertEquals(Loginpage.login(Data.username,Data.password ),"Dashboard");
        Assert.assertEquals(navBar.goToSideMenu("Leave"),Data.getLeaveListURL());
        navBar.goToTopbar("Entitlements","Add Entitlements");
        Assert.assertEquals(leavePage.selectEmployee("alaa"),"alaa saed");
        leavePage.addDays(2);
        leavePage.selectLeaveType();
        Assert.assertEquals(leavePage.clickSaveBtn(),"Success");
        logoutPage.logout();

    }

    @Test(priority = 1)
    public void Add_Multiple()
    {
        Assert.assertEquals(Loginpage.login(Data.username,Data.password ),"Dashboard");
        Assert.assertEquals(navBar.goToSideMenu("Leave"),Data.getLeaveListURL());
        navBar.goToTopbar("Entitlements","Add Entitlements");
        leavePage.selectMultiple();
        leavePage.selectLeaveTypeMultiple();
        leavePage.addDays(2);
        Assert.assertEquals(leavePage.clickSaveBtn(),"Success");
        logoutPage.logout();
    }

//    @Test
//    public void LoginandGoToLeaveEmployee()
//    {
//        Assert.assertEquals(Loginpage.login("AlaaAuto1","AbCdefgh@123455" ),"Dashboard");
//        Assert.assertEquals(navBar.goToSideMenu("Leave"),Data.EssGetLeaveListURL());
//        navBar.goToTopsub("Apply");
//        leavePage.selectLeaveType();
//        //leavePage.selectDate(0,"2");
//        //leavePage.clickSaveBtn();
//    }


}