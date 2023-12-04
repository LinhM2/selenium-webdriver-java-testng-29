package Exericise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Event {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Create_Events() {
        driver.get("https://rise.fairsketch.com/signin");

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("admin@demo.com");
        driver.findElement(By.cssSelector("input#password")).clear();
        driver.findElement(By.cssSelector("input#password")).sendKeys("riseDemo");
        driver.findElement(By.cssSelector("button.btn-primary")).click();

        driver.findElement(By.xpath("//span[text()='Events']")).click();
        sleepINSeconds(2);
        driver.findElement(By.cssSelector("a.btn.btn-default.add-btn")).click();
        sleepINSeconds(2);

        driver.findElement(By.id("title")).sendKeys("Linhm1");
        driver.findElement(By.id("description")).sendKeys("Description_m1");
        driver.findElement(By.id("start_date")).sendKeys("13-11-2023");
        driver.findElement(By.id("start_time")).sendKeys("1:00 AM");
        driver.findElement(By.id("end_date")).sendKeys("15-11-2023");
        driver.findElement(By.id("end_time")).sendKeys("1:00 AM");
        driver.findElement(By.id("location")).sendKeys("Locationm1");

        // dropdown client
        selectItemInEditableDropdown("div#s2id_clients_dropdown","div#select2-drop input","ul.select2-results span", "Hauck Ltd");
        sleepINSeconds(3);

        checkToElement(By.xpath("//label[text()='All team members']/preceding-sibling::input"));
        checkToElement(By.xpath("//label[text()='Repeat']/parent::div//input"));
        driver.findElement(By.cssSelector("input#repeat_every")).sendKeys("1");
        driver.findElement(By.cssSelector("input#no_of_cycles")).sendKeys("1");
        driver.findElement(By.cssSelector("div.color-palet span[data-color=\"#2d9cdb\"]")).click();

        driver.findElement(By.cssSelector("button.btn-primary")).click();


    }

    @Test
    public void TC_02_() {

    }


    @AfterClass
    public void afterClass() {driver.quit();
    }

    public void sleepINSeconds(long timeINSecond) {
        try {
            Thread.sleep(timeINSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectItemInEditableDropdown(String parentCss, String senkeyCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();
        driver.findElement(By.cssSelector(senkeyCss)).sendKeys(itemTextExpected);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item: allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }

    public void checkToElement (By byXpath){
        // Nếu element chưa được chọn thì mới click
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepINSeconds(2);
        }
    }

}