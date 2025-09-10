package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TransactionPage {
    WebDriver driver;
    By accountLink = By.linkText("Accounts Overview");
    By firstAccountLink = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");

    public TransactionPage(WebDriver driver) { this.driver = driver; }

    public void openTransactions() {
        driver.findElement(accountLink).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(firstAccountLink));

    
        driver.findElement(firstAccountLink).click();
    }

    public boolean isTransactionVisible(String keyword) {
        return driver.getPageSource().contains(keyword);
    }
}
