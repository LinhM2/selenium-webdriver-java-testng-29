package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.nopcommerce.com/en/register");
    }

    //HTML Element: <tagname attribute_name = 'attribute_value'>
    /*
    * <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
    */

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("test01");
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("navigation-main-logo"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("Email"));
    }

    @Test
    public void TC_04_Tagname() {
        driver.findElements(By.tagName("input"));
    }


    @Test
    public void TC_05_LinkText() {
        // tìm tuyệt đối
        driver.findElement(By.linkText("Get started"));
    }


    @Test
    public void TC_06_Partial_LinkText() {
        // tìm tương đối
        driver.findElement(By.partialLinkText("Case"));
    }


    @Test
    public void TC_07_Css() {
        // Css với ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        // Css với Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        // Css với name
        driver.findElement(By.cssSelector("input[name='FirstName']"));

        // Css với TagName
        driver.findElement(By.cssSelector("input"));

        // Css với link
        driver.findElement(By.cssSelector("a[href='/en/features']"));

        // Css với Partial_Link
        driver.findElement(By.cssSelector("a[href*='features']"));
        //driver.findElement(By.cssSelector("a[href^='features']"));
        //driver.findElement(By.cssSelector("a[href$='features']"));
    }


    @Test
    public void TC_08_XPath() {
        // XPath với ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        // XPath với Class
        driver.findElement(By.xpath("//div[@class='page-title']"));

        // XPath với name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        // XPath với TagName
        driver.findElement(By.xpath("//input"));

        // XPath với link
        driver.findElement(By.xpath("//a[@href='/en/features']"));
        driver.findElement(By.xpath("//a[text()='Features']"));

        // XPath với Partial_Link
        driver.findElement(By.xpath("//a[contains(@href,'features')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Features')]"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
