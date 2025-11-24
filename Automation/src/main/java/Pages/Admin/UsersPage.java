package Pages.Admin;

import org.openqa.selenium.By;
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

    By AdditionButton = By.cssSelector("div[class='orangehrm-header-container']>button");
    By userRoleDropdown = By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]");
    By statusDropdown = By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]");
    By EmployeeNameField = By.cssSelector("div[class='oxd-autocomplete-wrapper']>div>input");
    By UsernameField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    By submitBtn = By.cssSelector("button[type='submit']");
    By EmployeeDropDown = By.xpath("//div[@role='option']//span[contains(text(), 'J')]");
    By successMSG = By.className("oxd-text--toast-message");

    public String NavigateToAdditionPage() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(AdditionButton));
        addBtn.click();
        return driver.getCurrentUrl();
    }

    public String AddUser(String userRole, String status, String EmployeeName,
                           String Username, String Password) {

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
        WebElement empDropItem = wait.until(ExpectedConditions.elementToBeClickable(EmployeeDropDown));
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

        // success message
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement success = shortWait.until(ExpectedConditions.visibilityOfElementLocated(successMSG));
            return success.getText();
        } catch (Exception e) {
            return "Success message not found";
        }
    }
}
