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

public class Topic_12_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Defaut_Telarik_Checkbox() {
        // Topic 09: TC3
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By RearsideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
        By DualzoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");

        // Click vào chọn 2 checkbox: 2 case

        // Case 1: checkbox mở ra được chọn rồi
        checkToElement(RearsideCheckbox);
        /*if (!driver.findElement(RearsideCheckbox).isSelected()) { //true -> thì mới vào thâm hàm if (dấu !-> phủ địch isSelect: tức chưa được chọn)
            driver.findElement(RearsideCheckbox).click();
            sleepINSeconds(2);
        }*/

        // case 2: check box mở ra chưa được chọn
        checkToElement(DualzoneCheckbox);
        /*if (!driver.findElement(DualzoneCheckbox).isSelected()) { //true -> thì mới vào thâm hàm if
            driver.findElement(DualzoneCheckbox).click();
            sleepINSeconds(2);
        }*/

        // Verify checkbox đã được chọn thành công
        Assert.assertTrue(driver.findElement(RearsideCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(DualzoneCheckbox).isSelected());

        // Bỏ chọn 2 checkbox
        uncheckToElement(RearsideCheckbox);
        uncheckToElement(DualzoneCheckbox);

        // Verify checkbox bỏ chọn thành công
        Assert.assertFalse(driver.findElement(RearsideCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(DualzoneCheckbox).isSelected());

    }

    @Test
    public void TC_02_Defaut_Telarik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        By twoDieselRadio = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input");

        // Click chọn 1 trong 2
        checkToElement(twoPetrolRadio);

        // Verify
        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

        checkToElement(twoDieselRadio);

        // Verify
        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());

    }

    @Test
    public void TC_03_Select_ALL_Checkbox() {
        // Topic 09: TC 04
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        // Chọn hết tất cả các checkbox trong màn hình
        for (WebElement checkbox: allCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                //sleepINSeconds(1);
            }
        }
        // Verify hết tất cả các checkbox
        for (WebElement checkbox: allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        // mở lại trang với trạng thái ban đầu
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        // Chọn 1 checkbox/ radio nào đó trong tất cả các checkbox/ radio
        for (WebElement checkbox: allCheckboxes) {
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                checkbox.click();
                sleepINSeconds(1);
            }
        }

        // Verify hết tất cả các checkbox
        for (WebElement checkbox: allCheckboxes) {
            if (checkbox.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_04_Custom_Radio() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        // 1h

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

    public void checkToElement (By byXpath){
        // Nếu element chưa được chọn thì mới click
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepINSeconds(2);
        }
    }

    public void uncheckToElement (By byXpath){
        // Nếu element được chọn rồi thì click để bỏ chọn
        if (driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepINSeconds(2);
        }
    }

}
