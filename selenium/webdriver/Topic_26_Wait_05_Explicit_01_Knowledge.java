package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class Topic_26_Wait_05_Explicit_01_Knowledge {
    WebDriver driver;

    WebDriverWait explicitWait; // Khai báo chưa khởi tạo

    @BeforeClass // Precondition - khởi tạo dữ liệu/ data test/ page class/ variable
    public void beforeClass() {
        driver = new FirefoxDriver();

        // Khởi tạo 1 explicit wait có tổng thời gian là 10s - polling là 0.5s mặc định
        // 500 miliseconds = 0.5 second
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        /*// Khởi tạo 1 explicit wait có tổng thời gian là 10s - polling là 0.3s tự set
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));*/

    }

    @Test
    public void TC_01_Method() {
        // Chờ cho 1 Alert presence trong HTML/ DOM trước khi thao tác lên
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // Chờ cho element k còn ở trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        // Chờ trong element có trong DOM (k quan tâm có trong UI)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        // CHờ cho 1 list element có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        // CHờ cho 1-n element được hiển thị trên UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));

        // Chờ cho element được phép click: link/ button/ checkbox/ raido/...
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // Chờ page hiện tại có title như mong đợi
        explicitWait.until(ExpectedConditions.titleIs(""));
        driver.getTitle();

        // Kết hợp nhiều điều kiện - 2 điều kiện đều đúng
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        // kết hợp nhiều điều kiện - 1 trong 2 điều kiện đúng
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        // Chờ cho 1 element có attribute chứa giá trị mong đợi (tương đối)
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""), "", ""));

        // Chờ cho 1 element có attribute có giá trị mong đợi (tuyệt đối)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "", ""));

        // Chờ cho 1 element có attribute khác null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")), ""));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")), "", ""));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")), "", ""));

        // Chờ cho 1 element được selected thành công
        // Checkbox/ Radio/ dropdown item (Default)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        // Chờ cho 1 element được selected rồi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),true));

        // Chờ cho 1 element chưa được selected
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),false));

        // Chờ cho 1 frame/ iframe được avaiable và switch qua
        // Name or ID
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));

        // Index
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));

        // By or element
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));

        // Chờ cho 1 element biến mất (k hiển thị trên UI)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        // Chờ cho 1 đoạn code js cần trả về giá trị kiểu String
        explicitWait.until(ExpectedConditions.jsReturnsValue("document.documentElement.innerText;"));

        // Chờ cho 1 đoạn code js được thực thi k ném ra ngoại lệ nào hết
        // k ném ra: true
        // có ngoại lệ: false
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement.innerText;"));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement.innerText;")));

        // Chờ số lượng element bằng 1 con số cố định (=10)
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 10));
        // Chờ số lượng element < 10
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 10));
        // Ch số lượng element > 10
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 10));

        // Chờ window/ Tab là bao nhiêu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), ""));

        Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), pattern));

        // Bắt buộc text này có trong DOM/ HTML
        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("")),""));

        explicitWait.until(ExpectedConditions.urlToBe("http://live.techpanda.org/index.php/"));
        explicitWait.until(ExpectedConditions.urlContains("/index.php/"));
        explicitWait.until(ExpectedConditions.urlMatches("[^abc ]"));

        // Chờ cho 1 điều kiện mà element này nó có bị update trạng thái - load lại HTML
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

    }


    @AfterClass
    public void afterClass() {driver.quit();}

}
