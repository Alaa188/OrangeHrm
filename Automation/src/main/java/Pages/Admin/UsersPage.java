package Pages.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UsersPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public UsersPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    private By userRoleDropdown = By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]");
    private By statusDropdown = By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]");
    private By UsernameField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By EmployeeNameField = By.cssSelector("input[placeholder='Type for hints...']");
    private By successMSG = By.className("oxd-text--toast-message");
    private By AdditionBtn = By.cssSelector("div[class='orangehrm-header-container']>button");
    private By UpdateBtn=By.className("bi-pencil-fill");
    private By submitBtn = By.cssSelector("button[type='submit']");
    private By deleteBtnPath=By.className("bi-trash");
    private By confirmDeletionBtn=By.className("oxd-button--label-danger");
    public void NavigateToAddPage() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(AdditionBtn));
        addBtn.click();
    }
    public void NavigateToUpdatePage() {
        WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(UpdateBtn));
        updateBtn.click();
    }

    public String AddUser(String userRole, String status, String EmployeeName,String Username, String Password) {

        // user role
        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown));
        roleDropdown.click();

        By roleOption = By.xpath("//div[@role='option']//span[contains(text(), '" + userRole + "')]");
        WebElement selectRole = wait.until(ExpectedConditions.elementToBeClickable(roleOption));
        selectRole.click();

        // status
        WebElement statusDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
        statusDropdownElement.click();

        By statusOption = By.xpath("//div[@role='option']//span[contains(text(), '" + status + "')]");
        WebElement selectStatus = wait.until(ExpectedConditions.elementToBeClickable(statusOption));
        selectStatus.click();

        // employee name input
        WebElement empName = wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeNameField));
        empName.sendKeys(EmployeeName);

        // dropdown list for employee
        WebElement empDropItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[text()='" + EmployeeName + "']")));
        empDropItem.click();

        // username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(UsernameField));
        usernameField.sendKeys(Username);
        // password fields
        List<WebElement> passwordFields = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@type='password']"))
        );
        for (WebElement pass : passwordFields) {
            pass.sendKeys(Password);
        }


        // submit
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitButton.click();

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement success = shortWait.until(ExpectedConditions.visibilityOfElementLocated(successMSG));
            return success.getText();
        } catch (Exception e) {
            return "Success message not found";
        }
    }

    public String getUsernameInList(String username){
        WebElement UsernameInList= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='row']//div[text()='"+username+"']")));
        return UsernameInList.getText();
    }

    public String SearchForUser(String username,String userRole,String EmployeeName,String status) {
        // username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(UsernameField));
        usernameField.sendKeys(username);

        // user role
        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown));
        roleDropdown.click();

        By roleOption = By.xpath("//div[@role='option']//span[contains(text(), '"+userRole+"')]");
        WebElement selectRole = wait.until(ExpectedConditions.elementToBeClickable(roleOption));
        selectRole.click();

        // employee name input
        WebElement empName = wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeNameField));
        empName.sendKeys(EmployeeName);
        // dropdown list for employee
        WebElement empDropItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[text()='" + EmployeeName + "']")));
        empDropItem.click();

        // status
        WebElement statusDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
        statusDropdownElement.click();

        By statusOption = By.xpath("//div[@role='option']//span[contains(text(), '" + status + "')]");
        WebElement selectStatus = wait.until(ExpectedConditions.elementToBeClickable(statusOption));
        selectStatus.click();

        // submit
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitButton.click();


        return getUsernameInList(username);
    }

    public String EditUser(String userRole, String status, String EmployeeName,
                           String Username,boolean changePass, String Password) throws InterruptedException {
        // user role
        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown));
        roleDropdown.click();

        By roleOption = By.xpath("//div[@role='option']//span[contains(text(), '" + userRole + "')]");
        WebElement selectRole = wait.until(ExpectedConditions.elementToBeClickable(roleOption));
        selectRole.click();

        // status
        WebElement statusDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
        statusDropdownElement.click();

        By statusOption = By.xpath("//div[@role='option']//span[contains(text(), '" + status + "')]");
        WebElement selectStatus = wait.until(ExpectedConditions.elementToBeClickable(statusOption));
        selectStatus.click();

        // employee name input
        WebElement empName = wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeNameField));
        empName.sendKeys(Keys.CONTROL + "a");
        empName.sendKeys(Keys.DELETE);
        empName.sendKeys(EmployeeName);

        // dropdown list for employee
        WebElement empDropItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(   "//div[@role='listbox']//div[normalize-space()='" + EmployeeName + "']")));
        empDropItem.click();

        // username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(UsernameField));
        usernameField.sendKeys(Keys.CONTROL + "a");
        usernameField.sendKeys(Keys.DELETE);
        usernameField.sendKeys(Username);

        if(changePass){
            // password fields
            List<WebElement> passwordFields = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@type='password']"))
            );

            for (WebElement pass : passwordFields) {
                pass.sendKeys(Password);
            }
        }

        // submit
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitButton.click();

        try {
            WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(successMSG));
            return success.getText();
        } catch (Exception e) {
            return "Success message not found";
        }
    }
    public String DeleteUser(){
        WebElement deleteBtn=wait.until(ExpectedConditions.elementToBeClickable(deleteBtnPath));
        deleteBtn.click();
        WebElement confirmDeletion=wait.until(ExpectedConditions.elementToBeClickable(confirmDeletionBtn));
        confirmDeletion.click();
        try {
            WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(successMSG));
            return success.getText();
        } catch (Exception e) {
            return "Success message not found";
        }

    }
}
