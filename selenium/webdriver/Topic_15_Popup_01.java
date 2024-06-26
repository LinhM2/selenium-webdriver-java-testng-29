package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_In_DOM_01() {
        // TC01_Topic 11/12
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.cssSelector("button.login_")).click();
        sleepINSeconds(2);

        // đặt biến
        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");

        // Kiểm tra login popup đang hiển thị
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys("test1");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys("test123456");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
        sleepINSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(),"Mật khẩu sai!");

        // close popup
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        sleepINSeconds(2);

        // Kiểm tra login popup không hiển thị
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void TC_02_Fixed_Popup_In_DOM_02() {
        // TC02_Topic 11/12
        driver.get("https://skills.kynaenglish.vn/");

        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepINSeconds(2);
        // Kiểm tra hiển thị popup
        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());

        // Login
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepINSeconds(2);

        // vetify
        Assert.assertEquals(driver.findElement(By.cssSelector("div.password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");

        // đóng popup
        driver.findElement(By.cssSelector("button.k-popup-account-close")).click();

        // Kiểm tra hiển k còn thị popup
        Assert.assertFalse(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());


    }

    @Test
    public void TC_03_Fixed_Popup_Not_In_DOM_01() {
        // TC03_Topic 11/12
        driver.get("https://tiki.vn/");

        // Mở popup
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        sleepINSeconds(2);

        // Kiểm tra popup hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Overlay")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepINSeconds(2);

        // không nhập gì -> login
        driver.findElement(By.cssSelector("input[type='email']")).clear();
        driver.findElement(By.cssSelector("input[type='password']")).clear();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepINSeconds(2);

        // verify msg hiển thị
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[2]")).getText(),"Mật khẩu không được để trống");

        // đóng popup
        driver.findElement(By.cssSelector("button.btn-close")).click();
        sleepINSeconds(2);

        // Kiểm tra popup không còn hiển thị
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Overlay")).size(),0);

    }

    @Test
    public void TC_04_Fixed_Popup_Not_In_DOM_02() {
        // TC04_Topic 11/12
        driver.get("https://www.facebook.com/");

        // Mở popup
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepINSeconds(2);

       // Assert.assertTrue(driver.findElement(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepINSeconds(2);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);


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
