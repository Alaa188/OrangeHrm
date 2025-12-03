package Pages.Time;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimeSheetPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public TimeSheetPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;

    }
    private By EmployeeNameField = By.cssSelector("input[placeholder=\"Type for hints...\"]");
    private By ViewButt = By.className("oxd-button--medium");
    private By validateName = By.className("orangehrm-main-title");
    private By EditTimeSheet = By.className("oxd-button--ghost");
    private By Projectname = By.cssSelector("input[placeholder=\"Type for hints...\"]");
    private By Activity = By.className("oxd-select-text-input");

    public String search(String EmployeeName){
        // employee name input
        WebElement empName = wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeNameField));
        empName.sendKeys(EmployeeName);

        // dropdown list for employee
        WebElement empDropItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[text()='" + EmployeeName + "']")));
        empDropItem.click();

        WebElement ViewTimeSheet = driver.findElement(ViewButt);
        ViewTimeSheet.click();

        WebElement validate = wait.until(ExpectedConditions.visibilityOfElementLocated(validateName));

        return validate.getText();
    }

    public void EditTimeSheet(){
        WebElement EditTime = wait.until(ExpectedConditions.elementToBeClickable(EditTimeSheet));
        EditTime.click();

        WebElement ProjectName = wait.until(ExpectedConditions.visibilityOfElementLocated(Projectname));
        ProjectName.sendKeys("lak - hrm");


    }


}
