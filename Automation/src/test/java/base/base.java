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

    @BeforeSuite
    public void setUp() {

        System.out.println("1 - Starting setup");

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");

        System.out.println("2 - Creating ChromeDriver");
        driver = new ChromeDriver(options);

        System.out.println("3 - ChromeDriver created");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("4 - Opening URL");
        driver.get("http://localhost/orangehrm-5.7/web/index.php/auth/login");

        System.out.println("5 - URL opened");

        LogoutPage = new logoutPage(driver, wait);
        Loginpage = new loginPage(driver, wait);

        System.out.println("6 - Page objects created");

        Loginpage.login(Data.username, Data.password);

        System.out.println("7 - Login completed");
    }

    @AfterSuite
    public void tearDown() {

        LogoutPage=new logoutPage(driver,wait);
        LogoutPage.logout();
        if (driver != null) {
            driver.quit();
        }
    }
}
