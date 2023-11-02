package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    By resultText = By.cssSelector("p#result");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        // TC 07 - Topic 09
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        alert.accept();
        sleepINSeconds(3);

        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC_02_Confirm_Alert() {
        // TC 08 - Topic 09
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.dismiss(); // cancel

        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked: Cancel");

        // CLick ok
        /*driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();;
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.accept(); // ok
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked: Ok");*/

    }

    @Test
    public void TC_03_Prompt_Alert() {
        // TC 09 - Topic 09
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String text = "autoTest";
        alert.sendKeys(text);
        alert.accept();

        Assert.assertEquals(driver.findElement(resultText).getText(),"You entered: " + text);

    }

    @Test
    public void TC_04_Authentication_Alert() {
        // Thư viện Alert ko thử dụng cho Authentication Alert được => vì liên quan đến tính bảo mật
        // Chrome Deptool protocol (CDP)
        // TC 11 - Topic 09

        // cách 1: truyền thằng user/ pass vào url
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        // cách 2: Chạy trên windows (AutoIT) 1h37




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
