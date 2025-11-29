package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginPage {

    private final WebDriverWait wait;
    public loginPage( WebDriverWait wait) {
        this.wait = wait;
    }

    By usernameFieldLoc = By.cssSelector("[name=\"username\"]");
    By passwordFieldLoc = By.cssSelector("[name=\"password\"]");
    By submitButtonLoc = By.cssSelector("button[type=\"submit\"]");
    By dashboardLoc = By.cssSelector(".oxd-topbar-header-breadcrumb-module");

    public String getDashboard() {
        WebElement dashboardOn = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardLoc));
        return dashboardOn.getText();
    }

    public String login(String name, String password){
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFieldLoc));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldLoc));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitButtonLoc));

        usernameField.clear();
        usernameField.sendKeys(name);
        passwordField.clear();
        passwordField.sendKeys(password);
        submitButton.click();

        return getDashboard();
    }
}
