package Tests.Admin;

import Data.Data;
import Pages.Admin.UsersPage;
import Pages.NavBar;
import base.base;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UsersTest extends base {

    NavBar navBar;
    UsersPage usersPage;

    @BeforeClass
    public void setup() {
        navBar = new NavBar(driver);
        usersPage = new UsersPage(driver, wait);
    }

    @Epic("OrangeHRM")
    @Feature("Admin - User Management")
    @Story("Create User")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void UserCreation() throws InterruptedException {

        navBar.goToSideMenu("Admin");
        navBar.goToTopbar("User Management ", "Users");

        usersPage.NavigateToAddPage();

        Assert.assertEquals(
                usersPage.AddUser(
                        Data.userRoleAdmin,
                        Data.statusE,
                        Data.employeeName,
                        Data.uniqueUsername,
                        Data.uniquePassword
                ),
                Data.savedSuccessMSG
        );
    }

    @Epic("OrangeHRM")
    @Feature("Admin - User Management")
    @Story("Update User")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void UpdateUser() throws InterruptedException {

        navBar.goToSideMenu("Admin");
        navBar.goToTopbar("User Management ", "Users");

        Assert.assertEquals(
                usersPage.SearchForUser(
                        Data.uniqueUsername,
                        Data.userRoleAdmin
                ),
                Data.uniqueUsername
        );

        usersPage.NavigateToUpdatePage();

        Assert.assertEquals(
                usersPage.EditUser(
                        Data.userRoleAdmin,
                        Data.statusE,
                        Data.employeeName1,
                        Data.uniqueUsername,
                        false,
                        Data.uniquePassword
                ),
                Data.updatedSuccessMSG
        );
    }

    @Epic("OrangeHRM")
    @Feature("Admin - User Management")
    @Story("Delete User")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3)
    public void DeleteUser() throws InterruptedException {

        navBar.goToSideMenu("Admin");
        navBar.goToTopbar("User Management ", "Users");

        Assert.assertEquals(
                usersPage.SearchForUser(
                        Data.uniqueUsername,
                        Data.userRoleAdmin
                ),
                Data.uniqueUsername
        );

        Assert.assertEquals(
                usersPage.DeleteUser(Data.uniqueUsername),
                Data.deletedSuccessMSG
        );
    }
}