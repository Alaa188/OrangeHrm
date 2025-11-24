package Tests.Admin;

import Pages.Admin.UsersPage;
import Pages.NavBar;
import Pages.loginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.base;
import Data.Data;

public class UsersTest extends base{
    loginPage Loginpage;
    NavBar navBar;
    UsersPage usersPage;
    @BeforeClass
    public void setup(){
         Loginpage = new loginPage(driver,wait);
         navBar=new NavBar(driver);
        usersPage=new UsersPage(driver,wait);
    }


    @Test
    public void UserCreationAndAccessVerification() throws InterruptedException {
       Assert.assertEquals(Loginpage.login(Data.username,Data.password ),"Dashboard");
       Assert.assertEquals(navBar.goToSideMenu("Admin"),Data.getUsersURL());
       Assert.assertEquals(navBar.goToTopbar("User Management ","Users"),Data.getUsersURL());
       Assert.assertEquals(usersPage.NavigateToAdditionPage(),Data.getAdditionPageURL());
       Assert.assertEquals(usersPage.AddUser(Data.userRoleAdmin,Data.statusE,"J", Data.uniqueUsername,Data.uniquePassword), Data.savedSuccessMSG);
    }
}