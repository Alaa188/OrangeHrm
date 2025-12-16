package Tests.PIM;

import Data.Data;
import Pages.Admin.UsersPage;
import Pages.NavBar;
import Pages.PIM.EmployeesPage;
import Pages.loginPage;
import Pages.logoutPage;
import base.base;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmployeesTest extends base {

    loginPage Loginpage;
    NavBar navBar;
    UsersPage usersPage;
    EmployeesPage addEmployeePage;
    logoutPage LogoutPage;

    @BeforeClass
    public void setup(){
        Loginpage = new loginPage(wait, driver);
        navBar=new NavBar(driver);
        usersPage=new UsersPage(driver,wait);
        addEmployeePage=new EmployeesPage(driver,wait);
        LogoutPage = new logoutPage(driver,wait);

    }
    @Test(groups = {"smoke", "pim"})
    public void EmployeeCreation(){

        Assert.assertEquals(Loginpage.login(Data.username,Data.password ),"Dashboard");

        navBar.goToSideMenu("PIM");
        navBar.goToTopsub("Add Employee");

        Assert.assertEquals(addEmployeePage.addEmployee(Data.firstName,Data.middleName,Data.lastName, Data.uniqueUsernameForPim, Data.uniquePassword, Data.uniquePassword),Data.savedSuccessMSG);
        Assert.assertEquals(LogoutPage.logout(),"Login");

    }

}
