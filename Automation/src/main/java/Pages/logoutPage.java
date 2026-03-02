package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class logoutPage {
    WebDriver driver;
    WebDriverWait wait;
    public logoutPage(WebDriver driver,WebDriverWait wait){
        this.wait=wait;
        this.driver=driver;
    }
    private By userDropDownIconPath=By.className("oxd-userdropdown-icon");
    private By logoutPath=By.cssSelector("a[href='/web/index.php/auth/logout']");
    private By loginHeaderPath = By.tagName("h5");
    public String logout(){
        WebElement userDropDownIcon=wait.until(ExpectedConditions.elementToBeClickable(userDropDownIconPath));
        userDropDownIcon.click();

        WebElement logoutBtn=driver.findElement(logoutPath);
        logoutBtn.click();

        WebElement loginHeader = wait.until(ExpectedConditions.elementToBeClickable(loginHeaderPath));

        return loginHeader.getText();
    }
}
