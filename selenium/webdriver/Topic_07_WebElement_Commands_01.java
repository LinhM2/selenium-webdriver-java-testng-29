package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_07_WebElement_Commands_01 {
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
        /*// (1)
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
        fullNameTextbox.getAttribute("value");*/

        //(2) các hàm
        // Dùng để xóa dữ liệu trong 1 field cho phép nhập (editable)
        // Textbox/ testarea/ dropdown (editable)
        // thường được sủ dụng trước hàm sendkeys
        driver.findElements(By.id("")).clear(); //*


        // Dùng để nhập liệu vào các field bên trên
        driver.findElement(By.id("")).sendKeys(""); //**

        // Dùng để click lên Element
        driver.findElement(By.id("")).click(); //**


        // Tìm từ nốt cha vào nốt con
        driver.findElement(By.id("form-validate")).findElement(By.id("firsname"));
        driver.findElement(By.cssSelector("form#form-validate input#firsname"));

        // Trả về 1 element khớp với điều kiện
        WebElement fullNameTextbox = driver.findElement(By.id(""));

        // Tra về nhiều element khớp với điểu kiện
        List<WebElement> testboxes = driver.findElements(By.cssSelector(""));

        // DÙng để verify 1 checkbox/ radio/ dropdown đã được chọn hay chưa
        Assert.assertTrue(driver.findElement(By.id("")).isSelected()); //*
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        // Dropdown (default/ custom)
        Select select = new Select(driver.findElement(By.id("")));

        // Dùng để verify 1 element bất kỳ có hiển thị hay không
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed()); //**
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

        // Dùng để verify 1 element có được thao tác lên hay không (không phải read-only)
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled()); //*

        // HTML Element
        // <input type="text" id="firstname" name="firstname" value=""
        // title="First Name" maxlength="255" class="input-text required-entry">
        driver.findElement(By.id("")).getAttribute("title"); //*
        driver.findElement(By.id("")).getAttribute("name");

        // Tab Accesibility/ Properties trong element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("baseURI");

        // Tab Styles trong Elements
        // Front/ SIze/ Color/ Background/...
        driver.findElement(By.id("")).getCssValue("background-color"); //*
        // rgb (46, 138,184)
        driver.findElement(By.id("")).getCssValue("font-size");

        // Vị tri của màn hình so với độ phân giải màn hình
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();

        // Location + Size
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect();

        // Location
        Point namePoint = nameTextboxRect.getPoint();
        // SIze
        Dimension nameSize = nameTextboxRect.getDimension();
        nameSize.getHeight();
        nameSize.getWidth();

        //Shadow Element (JavaScripExecutor)
        driver.findElement(By.id("")).getShadowRoot();

        // Từ id/ class/ name/ css/ xpath có thể truy ra ngược lại tagname HTML
        driver.findElement(By.cssSelector("#firstname")).getTagName(); //input
        driver.findElement(By.id("firstname")).getTagName();  //input
        driver.findElement(By.className("form-instructions")).getTagName(); //P

        // Element A -> đầu ra của nó là tagname của element A
        String formListtag = driver.findElement(By.xpath("//*[@class='form-link']")).getTagName();
        // ul

        // Đầu vào của element B sẽ nhận tagname của element A là tham số
        driver.findElement(By.xpath("//*[@class='form-link']" + formListtag));

        driver.findElement(By.xpath("//*[@class='form-link']")).getText(); //**
        // 2015 magento demo store, All right Reserved.

        // Chụp hình bị lỗi và lưu dưới dạng nào
        // BYTE
        // FILE (lưu thành 1 hình có kích thước trong ổ cứng: jpg, png, ...)
        // BASE64 (hình dạng text)
        driver.findElement(By.xpath("//*[@class='form-link']")).getScreenshotAs(OutputType.BASE64); //*
        driver.findElement(By.xpath("//*[@class='form-link']")).getScreenshotAs(OutputType.BYTES);
        driver.findElement(By.xpath("//*[@class='form-link']")).getScreenshotAs(OutputType.FILE);

        // Form (element nào là thẻ form hoặc nằm trong thẻ form)
        // Login/ Register/ Search
        driver.findElement(By.id("")).submit();

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
