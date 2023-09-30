package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
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
        // dùng để lấy url của màn hình/ page hiện tại
        driver.getCurrentUrl();

        // Lấy ra source html/ css/ JS của page hiện tại
        // Verify 1 cách tương đối
        driver.getPageSource();
        Assert.assertTrue(driver.getPageSource().contains("test 123"));

        // lấy ra title của page hiện tại
        driver.getTitle();

        //Lấy ra id của tab hiện tại
        driver.getWindowHandle();
        driver.getWindowHandles();

        // Nếu chỉ dùng 1 lần thì không khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com");

        // Dùng lại nhiều lần (ít nhất là 2 lần)
        String loginPageUrl = "https://www.facebook.com";
        driver.get(loginPageUrl);

        // Cookies - Framework
        driver.manage().getCookies();

        // Get ra những log ở dev tool - Famework
        driver.manage().logs().get(LogType.DRIVER);

        // Apply cho việc tìm Element (findElement/findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // CHờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Set trước khi dung với thư viện JavaScripExecution
        // Inject 1 đoạn code JS vào trong Browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Selenium 4 mới có
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        // chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        // Test Pesponsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().window().setSize(new Dimension(1920, 1080));

        driver.manage().window().getSize();

        // set cho browser ở vị trí nào so với độ phân giải nàm hình (run trên màn hình có kích thước bao nhiêu)
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().getPosition();

        driver.navigate();

        driver.switchTo();

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
