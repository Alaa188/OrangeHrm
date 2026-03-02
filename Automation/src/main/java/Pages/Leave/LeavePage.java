package Pages.Leave;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class LeavePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By radioButtons = By.cssSelector(".oxd-radio-input");
    private By comboButtons = By.className("oxd-select-text--arrow");
    private By empTextBoxPath = By.cssSelector("input[placeholder='Type for hints...']");
    private By successMsg = By.cssSelector(".oxd-text--toast-message");
    private By entitlementAmount = By.xpath("//label[text()='Entitlement']/parent::div/following-sibling::div//input");
    private By saveBtnPath=By.className("-button--secondary");
    private By datePickerPath=By.className("bi-calendar");

    public LeavePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public String selectEmployee(String username)
    {
        List<WebElement> radioBtns = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(radioButtons)
        );
        radioBtns.get(0).click();
        WebElement empTextbox = wait.until(ExpectedConditions.elementToBeClickable(empTextBoxPath));
        empTextbox.clear();
        empTextbox.sendKeys(username);
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-autocomplete-dropdown"))
        );

        WebElement firstOption = wait.until(driver -> {
            List<WebElement> options = dropdown.findElements(By.cssSelector("div[role='option']"));
            for (WebElement option : options) {
                String text = option.getText().trim();
                if (!text.equalsIgnoreCase("Searching....") && !text.isEmpty()) {
                    return option;
                }
            }
            return null; // keep waiting
        });

        String fullName = firstOption.getText();
        firstOption.click();

        return fullName;
    }
    public void addDays(double Days)
    {
        WebElement amountTxtBox = wait.until(ExpectedConditions.elementToBeClickable(entitlementAmount));
        amountTxtBox.sendKeys(String.valueOf(Days));
    }
    public void selectLeaveType()
    {
        WebElement combo = wait.until(ExpectedConditions.elementToBeClickable(comboButtons));
        combo.click();

        WebElement firstOption = wait.until(driver -> {
            List<WebElement> options = driver.findElements(By.cssSelector("div.oxd-select-dropdown[role='listbox'] div[role='option']"));
            for (WebElement option : options) {
                String text = option.getText().trim();
                if (!text.isEmpty() && !text.equalsIgnoreCase("-- Select --")) {
                    return option;
                }
            }
            return null;
        });
        firstOption.click();
    }

    public String clickSaveBtn() {
        // Click the main Save button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'oxd-button--secondary')]")
        ));
        saveButton.click();

        // Handle possible confirmation modal
        try {
            WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Confirm']")
            ));
            confirmBtn.click();
        } catch (Exception e) {
            // No modal appeared; continue
        }

        // Wait for toast message
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-toast-content')]/p")
        ));
        return toast.getText();
    }


    public void selectMultiple()
    {
        List<WebElement> radioBtns = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(radioButtons)
        );
        radioBtns.get(1).click();

    }
    public void selectLeaveTypeMultiple()
    {
        WebElement firstOption;
        List<WebElement> comboBtns =  wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(comboButtons)
        );
        for(int i =0;i<3;i++)
        {
            comboBtns.get(i).click();
            firstOption = wait.until(driver -> {
                List<WebElement> options = driver.findElements(By.cssSelector("div.oxd-select-dropdown[role='listbox'] div[role='option']"));
                for (WebElement option : options) {
                    String text = option.getText().trim();
                    if (!text.isEmpty() && !text.equalsIgnoreCase("-- Select --")) {
                        return option;
                    }
                }
                return null;
            });
            firstOption.click();
        }
    }

    public void selectDate(int pickerIndex, String day) {

        // get all calendar icons (two: From Date + To Date)
        List<WebElement> icons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(datePickerPath)
        );

        icons.get(pickerIndex).click();

        // wait for calendar container
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.oxd-calendar-wrapper")
        ));

        // select a day
        WebElement dayBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'oxd-calendar-date')]//button[normalize-space()='" + day + "']")
        ));

        dayBtn.click();
    }


    public void waitForLoader() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-form-loader")
        ));
    }



}
