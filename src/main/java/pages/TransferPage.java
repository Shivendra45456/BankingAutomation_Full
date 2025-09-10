package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferPage {
    WebDriver driver;
    By transferLink = By.linkText("Transfer Funds");
    By amount = By.id("amount");
    By fromAcc = By.id("fromAccountId");
    By toAcc = By.id("toAccountId");
    By transferBtn = By.cssSelector("input[value='Transfer']");

    public TransferPage(WebDriver driver) { this.driver = driver; }

    public void transferFunds(String amt, String from, String to) {
        driver.findElement(transferLink).click();
        driver.findElement(amount).sendKeys(amt);
        driver.findElement(fromAcc).sendKeys(from);
        driver.findElement(toAcc).sendKeys(to);
        driver.findElement(transferBtn).click();
    }
}
