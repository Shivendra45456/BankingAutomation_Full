package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TransactionPage;

public class TransactionTest extends BaseTest {

    @Test
    public void checkTransactionHistory() {
        test = extent.createTest("Transaction History Test");

       
        new LoginPage(driver).login("john", "demo");

        
        TransactionPage tp = new TransactionPage(driver);
        tp.openTransactions();

     
         Assert.assertTrue(tp.isTransactionVisible("Activity"),
                 "Funds Transfer should appear in transaction history");
    }
}
