package Tests.Leave;

import Data.Data;
import Pages.Leave.LeavePage;
import Pages.NavBar;
import base.base;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class LeaveTest extends base {

    NavBar navBar;
    LeavePage leavePage;

    @BeforeClass
    public void start() {
        navBar = new NavBar(driver);
        leavePage = new LeavePage(driver, wait);
    }

    @Test
    public void add_Entitlement_To_Single_Employee() {

        Assert.assertEquals(
                navBar.goToSideMenu("Leave"),
                Data.getLeaveListURL()
        );

        navBar.goToTopbar("Entitlements", "Add Entitlements");

        Assert.assertEquals(
                leavePage.selectEmployee("alaa"),
                "Alaa Saeed"
        );

        leavePage.addDays(2);
        leavePage.selectLeaveType();

        Assert.assertEquals(
                leavePage.clickSaveBtn(),
                "Success"
        );
    }

    @Test(priority = 1)
    public void Add_Multiple() {

        Assert.assertEquals(
                navBar.goToSideMenu("Leave"),
                Data.getLeaveListURL()
        );

        navBar.goToTopbar("Entitlements", "Add Entitlements");

        leavePage.selectMultiple();
        leavePage.selectLeaveTypeMultiple();
        leavePage.addDays(2);

        Assert.assertEquals(
                leavePage.clickSaveBtn(),
                "Success"
        );
    }
}