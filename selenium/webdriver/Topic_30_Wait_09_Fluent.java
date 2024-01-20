package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class Topic_30_Wait_09_Fluent {
    WebDriver driver;
    FluentWait<WebDriver> fluentDriver; // khai báo cho testcase 01
    FluentWait<WebElement> fluentElement; // khai báo cho testcase 02


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        fluentDriver = new FluentWait<WebDriver>(driver); // Khởi tạo cho fluentDriver (TC 01)

    }

    @Test
    public void TC_01_() {
        // Topic 15/16 [Exercise]: TC 09
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        // chờ cho helloWorld text hiển thị trong vòng 10s
        // Setting
        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        // Condition
        fluentDriver.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
            }
        });

    }

    @Test
    public void TC_02_() {
        // Topic 15/16 [Exercise]: TC 08 fluent Wait
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

        // Khởi tạo fluentElement cho testcase 02
        fluentElement = new FluentWait<WebElement>(countDownTime);

        // setting
        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        // Điều kiện
        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                return webElement.getText().endsWith("00");
            }
        });

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
