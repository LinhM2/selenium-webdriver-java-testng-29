package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_29_Wait_08_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {

       // driver = new FirefoxDriver();
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01_Only_Implicit_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");

        // khi vào tìm element thì nó tìm thấy ngay
        // k cần chờ hết timeout
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_Only_Implicit_Not_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");

        // khi vào tìm element không tìm thấy
        // polling mỗi nửa s tìm lại 1 lần
        // khi hết timeout sẽ đánh fail testcase và throw exception: NoSuchElementException
        driver.findElement(By.cssSelector("input#email111"));
    }

    @Test
    public void TC_03_Only_Explicit_Found() {
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }
    @Test
    public void TC_04_Only_Explicit_Not_Found_Param_By() {
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // khi vào tìm element không tìm thấy
        // polling mỗi nửa s tìm lại 1 lần
        // khi hết timeout sẽ đánh fail testcase và throw exception: TimeoutException
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email111")));
    }
    @Test
    public void TC_05_Only_Explicit_Not_Found_Param_WebElement() {
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // khi vào tìm element không tìm thấy
        // polling mỗi nửa s tìm lại 1 lần
        // khi hết timeout sẽ đánh fail testcase và throw exception: NoSuchElementException
        // -> k dùng implicit nên k find được element
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#email1111"))));
    }
    @Test
    public void TC_06_Mix_Implicit_Explicit() {
        driver.get("https://www.facebook.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Check case k tìm thấy element > thời gian testcase fail <= 8
        // implicit sẽ ưu tiên chạy trước, rồi Explicit sẽ chạy mà k cần chờ implicit chạy xong (Explicit sẽ chạy sau implicit nửa đến 1s)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email111")));
    }

    @AfterClass
    public void afterClass() {driver.quit();}

}
