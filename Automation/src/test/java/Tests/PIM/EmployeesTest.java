package Tests.PIM;

import Data.Data;
import Data.ExcelReader;
import Pages.NavBar;
import Pages.PIM.EmployeesPage;
import base.base;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class EmployeesTest extends base {

    NavBar navBar;
    EmployeesPage addEmployeePage;

    @BeforeClass
    public void setup() {
        navBar = new NavBar(driver);
        addEmployeePage = new EmployeesPage(driver, wait);
    }

    @DataProvider(name = "ExcelData")
    public Object[][] UserRegistration() throws IOException {
        ExcelReader ER = new ExcelReader();
        return ER.getExcelData();
    }

    @Test(dataProvider = "ExcelData")
    public void EmployeeCreation(
            String firstName,
            String middleName,
            String lastName,
            String uniqueUsernameForPim,
            String uniquePassword) {

        navBar.goToSideMenu("PIM");
        navBar.goToTopsub("Add Employee");

        Assert.assertEquals(
                addEmployeePage.addEmployee(
                        firstName,
                        middleName,
                        lastName,
                        uniqueUsernameForPim,
                        uniquePassword,
                        uniquePassword
                ),
                Data.savedSuccessMSG
        );
    }
}