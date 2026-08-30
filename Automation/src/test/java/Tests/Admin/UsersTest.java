package Tests.Admin;

import Data.Data;
import Pages.Admin.UsersPage;
import Pages.NavBar;
import base.base;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Data.Data.userRoleAdmin;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class UsersTest extends base {

    NavBar navBar;
    UsersPage usersPage;

    @BeforeClass
    public void setup() {
        navBar = new NavBar(driver);
        usersPage = new UsersPage(driver, wait);
    }

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
                usersPage.DeleteUser(),
                Data.deletedSuccessMSG
        );
    }
}