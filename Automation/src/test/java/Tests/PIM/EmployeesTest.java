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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Data.ExcelReader;

import java.io.IOException;

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


    }
    @DataProvider(name = "ExcelData")
    public Object [][] UserRegisteration() throws IOException {
        ExcelReader ER = new ExcelReader();
        return ER.getExcelData();
    }
    @Test (dataProvider = "ExcelData")
    public void EmployeeCreation(String firstName,String middleName,String lastName,String uniqueUsernameForPim,String uniquePassword){
        navBar.goToSideMenu("PIM");
        navBar.goToTopsub("Add Employee");
        Assert.assertEquals(addEmployeePage.addEmployee(firstName,middleName,lastName,uniqueUsernameForPim,uniquePassword,uniquePassword),Data.savedSuccessMSG);

    }

}
