package Tests.Time;

import Pages.NavBar;
import Pages.Time.TimeSheetPage;
import base.base;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Data.Data;
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TimeSheetTest extends base {

    NavBar navBar;
    TimeSheetPage timeSheetPage;

    @BeforeClass
    public void setup() {
        navBar = new NavBar(driver);
        timeSheetPage = new TimeSheetPage(driver, wait);
    }

    @Test
    public void Employee_TimeSheet() {
        navBar.goToSideMenu("Time");

        Assert.assertEquals(
                timeSheetPage.search(Data.employeeName1),
                "Timesheet for Shaima Taha"
        );

        timeSheetPage.EditTimeSheet();
    }
}