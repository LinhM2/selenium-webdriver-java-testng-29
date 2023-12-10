package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
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
        //driver.manage().window().maximize();
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

        // CHọn 1->12 theo đủ hàng / cột
        actions.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(11)).perform();

        // CHọn từ 13->15
        actions.keyDown(Keys.CONTROL).perform(); // Nhấn phím ctrl xuống (chưa nhả ra)

        actions.click(allNumbers.get(12))
                .click(allNumbers.get(13))
                .click(allNumbers.get(14))
                .keyUp(Keys.CONTROL).perform();
    }

    @Test
    public void TC_05_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        sleepINSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");

    }

    @Test
    public void TC_06_RightClick() {
        // BT7 topic 10
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        // chưa click chuột phải thì nó đang không hiển thị (invisible)
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        // Click chuột phải
        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        sleepINSeconds(2);

        // Mới click chuột phải - các element được visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        // hover lên element
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepINSeconds(2);

        // -> được cập nhật lại class của element này - kiểm tra sự kiện hover thành công
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());

        // Click lên paste
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepINSeconds(2);

        // accept alert
        driver.switchTo().alert().accept();
        sleepINSeconds(2);

        // Kiểm tra Paste không còn hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
    }

    @Test
    public void TC_07_DragDropHTML4() {
        // BT8 topic 10
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        // đặt 2 biến
        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

        // thực hiện kéo thả
        actions.dragAndDrop(smallCircle, bigCircle).perform();
        sleepINSeconds(2);

        // Verify text
        Assert.assertEquals(bigCircle.getText(),"You did great!");

        // verfy màu nền
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");

    }

    @Test
    public void TC_08_DragDropHTML5_Css() throws IOException {
        // BT9 topic 10
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        // đặt 2 biến
        WebElement columnA = driver.findElement(By.cssSelector("div#column-a"));
        WebElement columnB = driver.findElement(By.cssSelector("div#column-b"));

        // lấy đường dẫn của Folder
        String projectPath = System.getProperty("user.dir");
        String dragAndDropFilePath = projectPath + "/dragAndDrop/drag_and_drop_helper.js";
        String jsContentFile = getContentFile(dragAndDropFilePath);

        // Thực thi đoạn lệnh JS
        //javascriptExecutor.executeScript(jsContentFile); //-> lỗi 1h36 video 33


    }

    @Test
    public void TC_09_DragDropHTML5_Xpath() {
        // BT9 topic 10
        driver.get("https://automationfc.github.io/drag-drop-html5/");

    }

    // Hàm đọc nội dụng cho file drag_and_drop_helper.js
    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
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
