package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    WebDriver driver;
    By accountOverview = By.linkText("Accounts Overview");

    public DashboardPage(WebDriver driver) { this.driver = driver; }

    public boolean isDashboardVisible() {
        return driver.findElement(accountOverview).isDisplayed();
    }
}
