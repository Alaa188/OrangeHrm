package Pages.Admin;

import Pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UsersPage extends BasePage {

    public UsersPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By userRoleDropdown = By.xpath(
            "(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]"
    );

    private By statusDropdown = By.xpath(
            "(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]"
    );

    private By UsernameField = By.xpath(
            "(//input[@class='oxd-input oxd-input--active'])[2]"
    );

    private By EmployeeNameField = By.cssSelector(
            "input[placeholder='Type for hints...']"
    );

    private By successMSG = By.className("oxd-text--toast-message");

    private By AdditionBtn = By.cssSelector(
            "div[class='orangehrm-header-container']>button"
    );

    private By UpdateBtn = By.className("bi-pencil-fill");

    private By submitBtn = By.cssSelector(
            "button[type='submit']"
    );

    private By deleteBtnPath = By.className("bi-trash");

    private By confirmDeletionBtn = By.className(
            "oxd-button--label-danger"
    );


    @Step("Navigate to Add User page")
    public void NavigateToAddPage() {

        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(AdditionBtn)
        );

        addBtn.click();
    }


    @Step("Navigate to Update User page")
    public void NavigateToUpdatePage() {

        WebElement updateBtn = wait.until(
                ExpectedConditions.elementToBeClickable(UpdateBtn)
        );

        updateBtn.click();
    }


    @Step("Click Submit button")
    public void clickSubmit() {

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(submitBtn)
        );

        submitButton.click();
    }


    @Step("Select User Role: {userRole}")
    public void SelectUserRole(String userRole) {

        WebElement roleDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(userRoleDropdown)
        );

        roleDropdown.click();

        By roleOption = By.xpath(
                "//div[@role='option']//span[contains(text(), '" + userRole + "')]"
        );

        WebElement selectRole = wait.until(
                ExpectedConditions.elementToBeClickable(roleOption)
        );

        selectRole.click();
    }


    @Step("Enter Username: {Username}")
    public void AddUserName(String Username) {

        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(UsernameField)
        );

        usernameField.sendKeys(Keys.CONTROL + "a");
        usernameField.sendKeys(Keys.DELETE);
        usernameField.sendKeys(Username);
    }


    @Step("Select Employee: {EmployeeName}")
    public void AddEmployee(String EmployeeName) {

        WebElement empName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(EmployeeNameField)
        );

        empName.sendKeys(Keys.CONTROL + "a");
        empName.sendKeys(Keys.DELETE);
        empName.sendKeys(EmployeeName);

        WebElement empDropItem = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//div[@role='listbox']//span[text()='"
                                        + EmployeeName + "']"
                        )
                )
        );

        empDropItem.click();
    }


    @Step("Enter Password")
    public void AddPass(String Password) {

        List<WebElement> passwordFields = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//input[@type='password']")
                )
        );

        for (WebElement pass : passwordFields) {
            pass.sendKeys(Password);
        }
    }


    @Step("Select Status: {status}")
    public void SelectStatus(String status) {

        WebElement statusDropdownElement = wait.until(
                ExpectedConditions.elementToBeClickable(statusDropdown)
        );

        statusDropdownElement.click();

        By statusOption = By.xpath(
                "//div[@role='option']//span[contains(text(), '" + status + "')]"
        );

        WebElement selectStatus = wait.until(
                ExpectedConditions.elementToBeClickable(statusOption)
        );

        selectStatus.click();
    }


    @Step("Verify Username '{Username}' appears in Users list")
    public String getUsernameInList(String Username) {

        WebElement UsernameInList = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//div[@role='row']//div[text()='"
                                        + Username + "']"
                        )
                )
        );

        return UsernameInList.getText();
    }


    @Step("Delete selected user and confirm deletion")
    public void DeleteAndConfirm() {

        WebElement deleteBtn = wait.until(
                ExpectedConditions.elementToBeClickable(deleteBtnPath)
        );

        deleteBtn.click();

        WebElement confirmDeletion = wait.until(
                ExpectedConditions.elementToBeClickable(confirmDeletionBtn)
        );

        confirmDeletion.click();
    }


    @Step("Verify success message")
    public String checkSuccessMsg() {

        try {

            WebDriverWait shortWait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(5)
            );

            WebElement success = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(successMSG)
            );

            return success.getText();

        } catch (Exception e) {

            return "Success message not found";
        }
    }


    @Step("Add new user")
    public String AddUser(
            String userRole,
            String status,
            String EmployeeName,
            String Username,
            String Password
    ) {

        SelectUserRole(userRole);

        SelectStatus(status);

        AddEmployee(EmployeeName);

        AddUserName(Username);

        AddPass(Password);

        clickSubmit();

        return checkSuccessMsg();
    }


    @Step("Search for user: {Username}")
    public String SearchForUser(
            String Username,
            String userRole
    ) {

        AddUserName(Username);

        SelectUserRole(userRole);

        clickSubmit();

        return getUsernameInList(Username);
    }


    @Step("Edit user: {Username}")
    public String EditUser(
            String userRole,
            String status,
            String EmployeeName,
            String Username,
            boolean changePass,
            String Password
    ) throws InterruptedException {

        SelectUserRole(userRole);

        SelectStatus(status);

        AddEmployee(EmployeeName);

        AddUserName(Username);

        if (changePass) {
            AddPass(Password);
        }

        clickSubmit();

        return checkSuccessMsg();
    }


    @Step("Delete user: {Username}")
    public String DeleteUser(String Username) {

        DeleteAndConfirm();

        return checkSuccessMsg();
    }
}