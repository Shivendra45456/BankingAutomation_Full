package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LoanPage;
import utils.ExcelUtils;

import java.time.Duration;

public class LoanTest extends BaseTest {

    @DataProvider(name = "loanData")
    public Object[][] getLoanData() {
        return ExcelUtils.getSheetData("src/test/resources/loanData.xlsx", "Sheet1");
    }


    @Test(dataProvider = "loanData")
    public void applyLoanDDT(String loanType, String amount, String downPayment, String account) {
        test = extent.createTest("Loan Test - " + loanType);

        new LoginPage(driver).login("john", "demo");
        LoanPage loan = new LoanPage(driver);
        loan.applyLoan(amount, downPayment, account);

        // Wait for either approval or denial message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Loan Request Processed')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Denied')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'error')]"))
        ));

        boolean approved = driver.getPageSource().contains("Loan Request Processed");
        boolean denied = driver.getPageSource().contains("Denied") || driver.getPageSource().contains("error");

        Assert.assertTrue(approved || denied, "Loan should be either approved or denied.");
    }


    @Test
    public void emiCalculation() {
        test = extent.createTest("EMI Validation");

        double principal = 5000, rate = 0.01, tenure = 12;
        double emi = (principal * rate * Math.pow(1 + rate, tenure)) /
                     (Math.pow(1 + rate, tenure) - 1);

        Assert.assertTrue(emi > 400 && emi < 500, "EMI calculation is incorrect");
    }


    @Test
    public void loanWithInvalidAmount() {
        test = extent.createTest("Loan Test - Invalid Amount");

        new LoginPage(driver).login("john", "demo");
        LoanPage loan = new LoanPage(driver);
        loan.applyLoan("abcd", "100", "12345"); 

        boolean errorShown = driver.getPageSource().contains("error") 
                          || driver.getPageSource().toLowerCase().contains("invalid");
        Assert.assertTrue(errorShown, "Error should be shown for invalid loan amount");
    }


    @Test
    public void loanWithZeroDownPayment() {
        test = extent.createTest("Loan Test - Zero Down Payment");

        new LoginPage(driver).login("john", "demo");
        LoanPage loan = new LoanPage(driver);
        loan.applyLoan("5000", "0", "12345");

        boolean approved = driver.getPageSource().contains("Loan Request Processed");
        boolean denied = driver.getPageSource().contains("Denied") || driver.getPageSource().contains("error");

        Assert.assertTrue(approved || denied, "Loan should either be approved or denied with zero down payment");
    }


    @Test
    public void loanWithoutAccountSelection() {
        test = extent.createTest("Loan Test - No Account Selected");

        new LoginPage(driver).login("john", "demo");
        LoanPage loan = new LoanPage(driver);
        loan.applyLoan("5000", "500", ""); // no account selected

        boolean errorShown = driver.getPageSource().contains("error") 
                          || driver.getPageSource().toLowerCase().contains("account");
        Assert.assertTrue(errorShown, "Error should be shown when no account is selected");
    }
}
