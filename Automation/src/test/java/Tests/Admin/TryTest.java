package Tests.Admin;
import Pages.loginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.base;
import Data.Data;

import java.time.Duration;

public class TryTest extends base{
    loginPage Loginpage;
    Data data;
    @BeforeClass
    public void setup(){
         Loginpage = new loginPage(driver,wait);
         data=new Data();
    }


    @Test
    public void testLogin() throws InterruptedException {
       Assert.assertEquals(Loginpage.login(data.username,data.password ),"Dashboard");

    }
}