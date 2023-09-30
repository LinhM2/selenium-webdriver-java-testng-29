package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        // Mở ra 1 page url bất kỳ
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        driver.get("https://www.facebook.com");

        // nếu như c 1 tab/window thì tính năng tương tự quit
        // Nhiều hơn 1 thì nó đóng cái nó đang active
        driver.close();

        //đóng browser
        driver.quit();

        // 2 hàm sẽ bị ảnh hưởng timeout của implicitlyWait: findElement/findElements

        // Nó sẽ đi tìm với loại by nào vả trả về với element nếu như được tìm thấy (webElement)
        // k được tìm thấy: Fail tại step này - thow exception: noSuchElementExcetion
        // Trả về 1 element, nếu có nhiều thì nó xũng chỉ lấy 1
        WebElement emailAddressTextbox = driver.findElement(By.id("pass"));

        // nó sẽ đi tìm với loại By nào trả về 1 danh sách emlement ếu như được tìm thấy (List webelement)
        // k được tìm thấy: không bị fail - trả vè 1 list rỗng (0 element)
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        checkboxes.get(1).click();

        // tại sao lại cần lấy dữ liệu ra để làm cái gì?
        String loginPageUrl = driver.getCurrentUrl();
        driver.getPageSource();
        driver.getTitle();
        driver.getWindowHandle();
        driver.getWindowHandles();

        // Nếu chỉ dùng 1 lần thì không khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com");

        // Dùng lại nhiều lần (ít nhất là 2 lần)
        Assert.assertEquals(loginPageUrl, "https://www.facebook.com");
        driver.get(loginPageUrl);
    }

    @Test
    public void TC_02_() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
