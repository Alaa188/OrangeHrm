package Tests.Admin;

import Pages.Admin.UsersPage;
import Pages.NavBar;
import Pages.PIM.EmployeesPage;
import Pages.loginPage;
import Pages.logoutPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.base;
import Data.Data;

public class UsersTest extends base{

    loginPage Loginpage;
    NavBar navBar;
    UsersPage usersPage;
    EmployeesPage addEmployeePage;
    logoutPage LogoutPage;
    @BeforeMethod
    public void TestSetup(){
         Loginpage = new loginPage(wait, driver);
         navBar=new NavBar(driver);
        usersPage=new UsersPage(driver,wait);
        addEmployeePage=new EmployeesPage(driver,wait);
        LogoutPage=new logoutPage(driver,wait);
    }

    @Test(priority = 1)
    public void UserCreation() throws InterruptedException {
        //login
       Assert.assertEquals(Loginpage.login(Data.username,Data.password ),"Dashboard");
       navBar.goToSideMenu("Admin");
       navBar.goToTopbar("User Management ","Users");
       //addUser
       usersPage.NavigateToAddPage();
       Assert.assertEquals(usersPage.AddUser(Data.userRoleAdmin,Data.statusE,Data.employeeName,Data.uniqueUsername,Data.uniquePassword), Data.savedSuccessMSG);
       //logout
       Assert.assertEquals(LogoutPage.logout(),"Login");
    }
    @Test(priority = 2)
    public void UpdateUser() throws InterruptedException {
        //login
        Assert.assertEquals(Loginpage.login(Data.username,Data.password ),"Dashboard");
        navBar.goToSideMenu("Admin");
        navBar.goToTopbar("User Management ","Users");
        //search
        Assert.assertEquals(usersPage.SearchForUser(Data.uniqueUsername,Data.userRoleAdmin,Data.employeeName,Data.statusE),Data.uniqueUsername);
        usersPage.NavigateToUpdatePage();
        //update
        Assert.assertEquals(usersPage.EditUser(Data.userRoleAdmin,Data.statusE,Data.employeeName1, Data.uniqueUsername,false,Data.uniquePassword), Data.updatedSuccessMSG);
        //logout
        Assert.assertEquals(LogoutPage.logout(),"Login");
    }

    @Test(priority = 3)
    public void DeleteUser() throws InterruptedException {
        //login
        Assert.assertEquals(Loginpage.login(Data.username,Data.password ),"Dashboard");
        navBar.goToSideMenu("Admin");
        navBar.goToTopbar("User Management ","Users");
        //search
        Assert.assertEquals(usersPage.SearchForUser(Data.uniqueUsername,Data.userRoleAdmin,Data.employeeName1,Data.statusE),Data.uniqueUsername);

        //delete
        Assert.assertEquals(usersPage.DeleteUser(), Data.deletedSuccessMSG);
        //logout
        Assert.assertEquals(LogoutPage.logout(),"Login");

    }


}