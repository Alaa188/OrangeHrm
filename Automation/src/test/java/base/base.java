package base;

import Data.Data;
import Pages.loginPage;
import Pages.logoutPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class base {
    public static WebDriver driver;
    protected WebDriverWait wait;
    loginPage Loginpage;
    logoutPage LogoutPage;

    @BeforeClass
    public void setUp() {

        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost/orangehrm-5.7/web/index.php/auth/login");
        LogoutPage = new logoutPage(driver,wait);
        Loginpage = new loginPage(wait, driver);
        Loginpage.login(Data.username,Data.password );

        //driver.get("http://localhost/orangehrm-5.7/web/index.php/auth/login");
    }

    @AfterClass
    public void tearDown() {

        LogoutPage=new logoutPage(driver,wait);
        LogoutPage.logout();
        if (driver != null) {
            driver.quit();
        }
    }
}
