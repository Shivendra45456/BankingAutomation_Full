package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoanPage {
    WebDriver driver;
    By loanLink = By.linkText("Request Loan");
    By amount = By.id("amount");
    By downPayment = By.id("downPayment");
    By fromAccount = By.id("fromAccountId");
    By applyBtn = By.cssSelector("input[value='Apply Now']");

    public LoanPage(WebDriver driver) { this.driver = driver; }

    public void applyLoan(String amt, String down, String accId) {
        driver.findElement(loanLink).click();
        driver.findElement(amount).sendKeys(amt);
        driver.findElement(downPayment).sendKeys(down);
        driver.findElement(fromAccount).sendKeys(accId);
        driver.findElement(applyBtn).click();
    }
}
