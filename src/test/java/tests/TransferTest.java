package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TransferPage;

public class TransferTest extends BaseTest {
    @Test
    public void fundTransfer() {
        test = extent.createTest("Fund Transfer Test");
        new LoginPage(driver).login("john", "demo");
        TransferPage tf = new TransferPage(driver);
        tf.transferFunds("100", "12345", "54321");
        Assert.assertTrue(driver.getPageSource().contains("Transfer Complete!"));
    }
}
