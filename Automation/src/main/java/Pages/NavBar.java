package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NavBar {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration TIMEOUT = Duration.ofSeconds(10);
    private final Duration POLLING = Duration.ofMillis(200);


    private By sideMenuItems = By.cssSelector("span.oxd-main-menu-item--name");
    private By topbarMenuItems = By.cssSelector("nav[role='navigation'] span.oxd-topbar-body-nav-tab-item");

    public NavBar(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
        this.wait.pollingEvery(POLLING);
    }

    private void safeClick(WebElement el) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();
        } catch (StaleElementReferenceException stale) {
            // If element went stale, try JavaScript click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    private WebElement findMatchingElement(List<WebElement> elements, String menuName) {
        for (WebElement e : elements) {
            try {
                String text = e.getText() == null ? "" : e.getText().trim();
                if (text.equalsIgnoreCase(menuName.trim())) return e;
                // fallback: contains (useful if there is extra whitespace or icons)
                if (text.toLowerCase(Locale.ROOT).contains(menuName.trim().toLowerCase(Locale.ROOT)))
                    return e;
            } catch (StaleElementReferenceException ignored) {
                // ignore and continue
            }
        }
        return null;
    }

    public String goToSideMenu(String menuName) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(sideMenuItems));
        List<WebElement> items = driver.findElements(sideMenuItems);
        WebElement match = findMatchingElement(items, menuName);
        if (match != null) {
            safeClick(match);
            return driver.getCurrentUrl();
        }
        return"Side menu not found";
    }

    public boolean isSideMenuPresent(String menuName) {
        List<WebElement> items = driver.findElements(sideMenuItems);
        WebElement match = findMatchingElement(items, menuName);
        return match != null && match.isDisplayed();
    }

    public void goToTopsub(String subMenu) {
        By sub = By.xpath(String.format("//a[normalize-space(text())='%s'] | //span[normalize-space(text())='%s']", subMenu, subMenu));
        WebElement Sub = wait.until(ExpectedConditions.visibilityOfElementLocated(sub));
        Sub.click();
    }

   /* public String goToTopbar(String menuName) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(topbarMenuItems));
        List<WebElement> items = driver.findElements(topbarMenuItems);
        WebElement match = findMatchingElement(items, menuName);
        if (match != null) {
            safeClick(match);
            return driver.getCurrentUrl();
        }
        return "TopBar menu not found";
    }*/

    public void goToTopbar(String topMenu, String subMenu) {
        By submenuXpath = By.xpath("//span[contains(text(),'"+topMenu+"')]");
        WebElement SubmenuEl =wait.until(ExpectedConditions.elementToBeClickable(submenuXpath));
        SubmenuEl.click();
        goToTopsub(subMenu);
    }
}
