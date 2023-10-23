package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
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
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registButton = driver.findElement(By.cssSelector("input.egov-button"));

        // Verify button bị disable khi chưa click vào checkbox
        Assert.assertFalse(registButton.isEnabled());

        // CLick vào checkbox
        driver.findElement(By.cssSelector("input#chinhSach")).click();

        driver.findElement(By.cssSelector("input#chinhSach"));


        // Verify button đã được enable sau khi click vào checkbox
        Assert.assertTrue(registButton.isEnabled());


        /*// Lấy ra mã màu nền của button

        // Lấy ra mã màu nền của button
        String registerBackgroundRGB = registButton.getCssValue("background-color");
        System.out.println("Background color RGB = " + registerBackgroundRGB);

        // Conver từ kiểu String (mã RGB) qua kiểu color
        Color registerBackgroundColor = Color.fromString(registerBackgroundRGB);

        // Conver qua kiểu Hexa
        String registerBackgroundHexa = registerBackgroundColor.asHex();
        System.out.println("Background color Hexa = " + registerBackgroundHexa);

        Assert.assertEquals(registerBackgroundHexa.toLowerCase(),"#ef5a00"); // toLowerCase - chọn qua VIẾT THƯỜNG
        */

       // CHỈ CẦN DÙNG 1 DÒNG ĐỂ VERIFY MÀU THAY THẾ ĐOẠN CONVER TRÊN  **
        Assert.assertEquals(Color.fromString(registButton.getCssValue("background-color")).asHex().toLowerCase(),"#ef5a00");
    }

    @Test
    public void TC_02_Fahasa_Button() {
        // Bài tập 1 > Topic 09
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        // Verify login button khi Disable
        Assert.assertFalse(loginButton.isEnabled());

        // Verify login button = background Color khi disable
            // System.out.println(loginButton.getCssValue("background-color")); => in để xem mã màu
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#000000");
        // Nhập email/ Pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("linh@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepINSeconds(3);

        // Verify login buttn khi Enable
        Assert.assertTrue(loginButton.isEnabled());

        // Verify login button = background Color khi Enable
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#c92127");



    }

    @Test
    public void TC_03_Demos_Checkbox_and_Radio() {

    }

    @AfterClass
    public void afterClass() {driver.quit();}

    public void sleepINSeconds(long timeINSecond) {
        try {
            Thread.sleep(timeINSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

// làm Bt ở Topic 09 [Exercise] Button/Radiobutton/Checkbox/ Alert


