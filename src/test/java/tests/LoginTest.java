package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;

public class LoginTest extends BaseTest {
    @Test
    public void validLogin() {
        test = extent.createTest("Valid Login Test");
        new LoginPage(driver).login("john", "demo");
        Assert.assertTrue(new DashboardPage(driver).isDashboardVisible());
    }
    
    
    @Test
    public void invalidLogin() {
        test = extent.createTest("Invalid Login Test");

        new LoginPage(driver).login("wrongUser", "wrongPass");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By errorMsg = By.xpath("//*[contains(text(),'The username and password could not be verified.')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));

        boolean errorDisplayed = driver.findElement(errorMsg).isDisplayed();

        Assert.assertTrue(errorDisplayed,
                "Error message should be displayed after invalid login");
    }
}










//invalid login

//package tests;
//
//import org.openqa.selenium.By;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pages.LoginPage;
//import pages.DashboardPage;
//
//public class LoginTest extends BaseTest {
//
//    @Test
//    public void validLogin() {
//        test = extent.createTest("Valid Login Test");
//
//        new LoginPage(driver).login("john", "demo");
//
//        Assert.assertTrue(new DashboardPage(driver).isDashboardVisible(),
//                "Dashboard should be visible after valid login");
//    }
//
//    @Test
//    public void invalidLogin() {
//        test = extent.createTest("Invalid Login Test");
//
//        new LoginPage(driver).login("wrongUser", "wrongPass");
//
//        boolean errorDisplayed = driver.findElement(By.xpath("//*[contains(text(),'The username and password could not be verified.')]")).isDisplayed();
//
//        Assert.assertTrue(errorDisplayed,
//                "Error message should be displayed after invalid login");
//    }
//}
