package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_WebElement_Commands {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com");

    }

    @Test
    public void TC_01_Element() {
        // html element: Textbox/ testarea/ link/ button/...
        // Tìm và trả về 1 element
        driver.findElement(By.id(""));

        // Tìm và tương tác
        driver.findElement(By.id("")).clear();
        driver.findElement(By.id("")).sendKeys("");

        // tìm và lưu nó vào 1 biến WebElement (chưa tương tác)
        // khi dùng 2 lần trở lên mới khai báo biến
        WebElement fullNameTextbox = driver.findElement(By.id(""));
        fullNameTextbox.clear();
        fullNameTextbox.sendKeys("test abc");

        //


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
