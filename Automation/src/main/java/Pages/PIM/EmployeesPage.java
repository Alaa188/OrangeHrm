package Pages.PIM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public EmployeesPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    private By firstNameField    = By.name("firstName");
    private By middleNameField   = By.name("middleName");
    private By lastNameField     = By.name("lastName");
    private By switchControl     = By.className("oxd-switch-input");
    private By usernameField     = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private By passField         = By.xpath("(//input[@type='password'])[1]");
    private By confirmPassField  = By.xpath("(//input[@type='password'])[2]");
    private By saveBtn           = By.cssSelector("button[type='submit']");
    private By successMSG        = By.className("oxd-text--toast-message");
    private By EmployeeNamePath   = By.xpath("//div[contains(text(),'Shaima a')]");
    private By EmployeeNameField = By.cssSelector("input[placeholder='Type for hints...']");
    private By submitBtn = By.cssSelector("button[type='submit']");

    public String addEmployee(String fName,String mName, String lName,String UserName, String Password,String ConfirmPasswod){
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        firstName.sendKeys(fName);

        WebElement middleName = driver.findElement(middleNameField);
        middleName.sendKeys(mName);

        WebElement lastName = driver.findElement(lastNameField);
        lastName.sendKeys(lName);

        WebElement switchEl = wait.until(ExpectedConditions.elementToBeClickable(switchControl));
        switchEl.click();

        WebElement Username = driver.findElement(usernameField);
        Username.sendKeys(UserName);

        WebElement Pass = driver.findElement(passField);
        Pass.sendKeys(Password);

        WebElement confirmPass = driver.findElement(confirmPassField);
        confirmPass.sendKeys(ConfirmPasswod);

        //submit
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        saveButton.click();

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement success = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(successMSG)
            );
            return success.getText();
        } catch (Exception e) {
            return "Success message not found";
        }

    }
    public String getEmployeeInList(String Employee){
        WebElement UsernameInList= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='row']//div[text()='"+Employee+"']")));
        return UsernameInList.getText();
    }
    public String SearchForEmployee(String EmployeeName) {

        // employee name input
        WebElement empName = wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeNameField));
        empName.sendKeys(EmployeeName);
        // dropdown list for employee
        WebElement empDropItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[text()='" + EmployeeName + "']")));
        empDropItem.click();

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitButton.click();
        String shortName = EmployeeName.substring(0, EmployeeName.lastIndexOf(" "));
        return getEmployeeInList(shortName);
    }
}
