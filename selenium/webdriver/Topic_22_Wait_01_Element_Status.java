package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepINSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("linh@gmail.com");
        sleepINSeconds(3);

        // điều kiện 1: element có xuất hiện ở trên UI và có trong cây html
        // Tại đúng thời điểm này thì confirm email textbox đang visibe/displayed (hiển thị)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

        // Kiểm tra 1 element đang hiển thị
        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_IN_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepINSeconds(2);

        // điều kiện 2: element k xuất hiện trên UI và vẫn có trong cây html
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepINSeconds(3);

        // Tại đúng thời điểm này/step này thì confirm email textbox đang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        // Kiểm tra 1 element không hiển thị
        // chạy nhanh -> kết quả step này passed
        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }
    @Test
    public void TC_02_Invisible_NOT_IN_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepINSeconds(2);

        // Click vào icon close để đóng popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepINSeconds(3);

        // điều kiện 3: element k xuất hiện trên UI và cũng k có trong cây html
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepINSeconds(3);

        // Tại đúng thời điểm này/step này thì confirm email textbox đang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        // Kiểm tra 1 element không hiển thị
        // chạy lâu -> kết quả step này failed
        // Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());
        // -> vậy verify ntn???
    }

    @Test
    public void TC_03_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepINSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("linh@gmail.com");
        sleepINSeconds(3);

        // điều kiện 1: element có xuất hiện ở trên UI và có trong cây html
        // Tại đúng thời điểm này thì confirm email textbox presence (có trong html)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepINSeconds(3);

        // điều kiện 2: element k xuất hiện trên UI và vẫn có trong cây html
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

    }

    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepINSeconds(2);

        // Tại thời điểm này element có xuất hiện và mình sẽ findelement
        WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);

        // Click vào icon close để đóng popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepINSeconds(3);

        // điều kiện 3: element k xuất hiện trên UI và cũng k có trong cây html
        //
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepINSeconds(long timeINSecond) {
        try {
            Thread.sleep(timeINSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}