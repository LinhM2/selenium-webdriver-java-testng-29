package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Topic_06_WebBrowser_Commands_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        // muốn dùng được th phải khởi tạo
        // nếu k khởi tạo sẽ gặp lỗi
        driver = new FirefoxDriver(); //**
        /*driver = new ChromeDriver();
        driver = new EdgeDriver();*/


        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //**
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Browser() throws MalformedURLException {
        // Mở ra 1 page url bất kỳ
        driver.get("https://www.facebook.com"); //**

        // nếu như c 1 tab/window thì tính năng tương tự quit
        // Nhiều hơn 1 thì nó đóng cái nó đang active
        driver.close(); //*

        //đóng browser
        driver.quit(); //**

        // 2 hàm sẽ bị ảnh hưởng timeout của implicitlyWait: findElement/findElements

        // Nó sẽ đi tìm với loại by nào vả trả về với element nếu như được tìm thấy (webElement)
        // k được tìm thấy: Fail tại step này - thow exception: noSuchElementExcetion
        // Trả về 1 element, nếu có nhiều thì nó xũng chỉ lấy 1
        WebElement emailAddressTextbox = driver.findElement(By.id("pass")); //**

        // nó sẽ đi tìm với loại By nào trả về 1 danh sách emlement ếu như được tìm thấy (List webelement)
        // k được tìm thấy: không bị fail - trả vè 1 list rỗng (0 element)
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']")); //**
        checkboxes.get(1).click();

        // tại sao lại cần lấy dữ liệu ra để làm cái gì?
        // dùng để lấy url của màn hình/ page hiện tại
        driver.getCurrentUrl(); //*

        // Lấy ra source html/ css/ JS của page hiện tại
        // Verify 1 cách tương đối
        driver.getPageSource();
        Assert.assertTrue(driver.getPageSource().contains("test 123"));

        // lấy ra title của page hiện tại
        driver.getTitle();

        //Lấy ra id của tab hiện tại
        driver.getWindowHandle(); //*
        driver.getWindowHandles(); //*

        // Nếu chỉ dùng 1 lần thì không khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com");

        // Dùng lại nhiều lần (ít nhất là 2 lần)
        String loginPageUrl = "https://www.facebook.com";
        driver.get(loginPageUrl);

        // Cookies - Framework
        driver.manage().getCookies(); //*

        // Get ra những log ở dev tool - Famework
        driver.manage().logs().get(LogType.DRIVER); //*

        // Apply cho việc tìm Element (findElement/findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //**

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
        driver.manage().window().maximize(); //**
        driver.manage().window().minimize();

        // Test Pesponsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().window().setSize(new Dimension(1920, 1080));

        driver.manage().window().getSize();

        // set cho browser ở vị trí nào so với độ phân giải nàm hình (run trên màn hình có kích thước bao nhiêu)
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().getPosition();

        // ĐIều huớng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        // Thao tác với history của web page (back/forward)
        driver.navigate().to("https://www.facebook.com");
        driver.navigate().to(new URL("https://www.facebook.com"));

        driver.get("https://www.facebook.com");

        // Alert/ Window (tab)/ Frame (iFrame) //*
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("");

        // lấy ra ID của cửa sổ/tab hiện tại //*
        // Handle window/ tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        // Switch/ handle frame/ (iframe) //*
        // Index/ ID (name)/ Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("636363636");
        driver.switchTo().frame(driver.findElement(By.id("")));

        // Switch về html chứa frame trước đó
        driver.switchTo().defaultContent();

        // Từ frame trong đi ra frame ngoài chứa nó
        driver.switchTo().parentFrame();


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
