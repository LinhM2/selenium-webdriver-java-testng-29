package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_23_Wait_02_Find_Element {
    WebDriver driver;

    WebDriverWait expliciwait;

    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        // implicit wait ver 4x trở lên
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // set implicit = selenium version 3x trở xuống
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_01_FindElement() {
        // case 1 - element được tìm thấy chỉ có 1
        // sẽ k cần chờ hết timeout
        // tìm thấy sẽ trả về 1 webelement
        // Qua step tiếp theo
        driver.findElement(By.cssSelector("input#email"));

        // case 2 - element được tìm thấy nhưng có nhiều hơn 1
        // sẽ k cần chờ hết timeout
        // lấy element đầu tiên dù có cả n node
        // Qua step tiếp theo
        driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("linh@gmail.com");

        // case 3 - element k được tìm thấy
        // chờ hết timeout là 10s
        // trong thời gian 10s này, cứ mỗi nửa s sẽ tìm lại 1 lần:
        // 1. Nếu tìm lại mà thấy thì cũng trả về element rồi qua step tiếp theo
        // 2. Nếu tìm lại mà k thấy thì đánh fail testcase và throw exception: NoSuchElementException
        // các step còn lại k chạy nữa
        driver.findElement(By.cssSelector("input#not-found"));
    }

    @Test
    public void TC_02_FindElements() {
        List<WebElement> elementList;
        // case 1 - element được tìm thấy chỉ có 1
        // k cần chờ hết timeout 10s
        // Trả về list element chứa đúng 1 element
        elementList = driver.findElements(By.cssSelector("input#email"));

        // case 2 - element được tìm thấy  nhưng có nhiều hơn 1
        // k cần chờ hết timeout 10s
        // Trả về list element chứa nhiều  element
        elementList = driver.findElements(By.cssSelector("input[type='text'],[type='password']"));

        // case 3 - element k được tìm thấy
        // chờ hết timeout 10s
        // Mooic nửa s tìm lại 1 lần
        // 1. Nếu trong thời gian tìm lại mà thấy element thì cũng trả về list chứa các element đó
        // 2. Nếu hết thời gian tìm lại mà k thấy thì trả về 1 list rỗng (empty) và không đánh fail testcase
        // qua step tiếp theo
        elementList = driver.findElements(By.cssSelector("input#not-found"));

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
