package tests;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BaseTest {
    public WebDriver driver;
    public static ExtentReports extent;
    public ExtentTest test;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/BankingReport.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get("http://parabank.parasoft.com/parabank/index.htm");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "reports/screenshots/" + result.getName() + ".png";
            Files.createDirectories(new File("reports/screenshots/").toPath());
            Files.copy(scr.toPath(), new File(path).toPath());
            test.fail("Failed Screenshot").addScreenCaptureFromPath(path);
        }
        if (driver != null) driver.quit();
        extent.flush();
    }
}
