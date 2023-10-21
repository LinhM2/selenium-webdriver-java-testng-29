package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class Topic_11_Button_Radio_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registButton = driver.findElement(By.cssSelector("input.egov-button"));

        // Verify button bị disable khi chưa click vào checkbox
        Assert.assertFalse(registButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach"));

        // Verify button đã được enable sau khi click vào checkbox
        Assert.assertTrue(registButton.isEnabled());

        // Lấy ra mã màu nền của button
        String registerBackgroundRGB = registButton.getCssValue("background-color");
        System.out.println("Background color RGB = " + registerBackgroundRGB);

        // Conver từ kiểu String (mã RGB) qua kiểu color


        // Conver qua kiểu Hexa
        String registerBackgroundHexa = registButton.getCssValue("background-color");
        System.out.println("Background color Hexa = " + registerBackgroundHexa);

        Assert.assertEquals(registerBackgroundHexa,"#ef5a00");

        // 1 - viêt 1 hàm để tự conver qua hexa
        // 2 - dùng thư viện (selenium color)




    }

    @Test
    public void TC_02_() {

    }

    @Test
    public void TC_03_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}