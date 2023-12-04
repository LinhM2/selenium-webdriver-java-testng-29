package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_14_Actions {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        // actions đang giả lập lại hành vi của Mouse/ keyboard/ Pen nên khi n đang chạy mình k sư dụng các thiết bị này
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Tooltip() {
        // BT1 topic 10
       driver.get("https://automationfc.github.io/jquery-tooltip/");

       WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

       actions.moveToElement(ageTextbox).perform();
       sleepINSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_Hover_Menu_Fahasa() {
        driver.get("https://www.fahasa.com/");

        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepINSeconds(2);

        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
        sleepINSeconds(2);

        String subMenuText = "Thiết Bị Số - Phụ Kiện Số";

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='" + subMenuText + "']")).click();
        sleepINSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"THIẾT BỊ SỐ - PHỤ KIỆN SỐ");
    }

    @Test
    public void TC_03_Click_And_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        // 20 ô
        Assert.assertEquals(allNumbers.size(),20);

        // Chọn theo Block - Ngang/ Dọc
        // CHọn 1 -> 15
        actions.clickAndHold(allNumbers.get(0)) // CLick lên số 1 và giữ chuột
                .pause(2000) // Dừng lại 2s nếu cần
                .moveToElement(allNumbers.get(14)) // Di chuột trái đến số 15
                .release() // nhả chuột trái ra
                .perform(); // Execute tất cả action trên

        List<String> allNumberTextExpected = new ArrayList<String>();
        allNumberTextExpected.add("1");
        allNumberTextExpected.add("2");
        allNumberTextExpected.add("3");
        allNumberTextExpected.add("5");
        allNumberTextExpected.add("6");
        allNumberTextExpected.add("7");
        allNumberTextExpected.add("9");
        allNumberTextExpected.add("10");
        allNumberTextExpected.add("11");
        allNumberTextExpected.add("13");
        allNumberTextExpected.add("14");
        allNumberTextExpected.add("15");

        // Tổng các số đã chọn
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(),12);

        List<String> allNumberTextActual = new ArrayList<String>();

        for (WebElement element: allNumbersSelected) {
            allNumberTextActual.add(element.getText());
            Assert.assertEquals(element.getCssValue("background-color"),"");
        }

        Assert.assertEquals(allNumberTextExpected, allNumberTextActual);

    }

    @Test
    public void TC_04_Click_And_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Tổng số chưa chọn
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(),20);


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
