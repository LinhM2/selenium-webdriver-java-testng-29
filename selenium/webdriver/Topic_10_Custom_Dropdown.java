package webdriver;

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

public class Topic_10_Custom_Dropdown {
    WebDriver driver;

    // Tường minh: trạng thái cụ thể cho element
    // Visible/ Invisible/ Presence/ Number/ CLickable...
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Ngầm định: k rõ ràng cho 1 trạng thái cụ thể nào của element hết
        // CHo việc tìm element - findElement (s)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        // 1 - Click vào 1 thẻ để cho nó xổ hết các item bên trong dropdown ra
        driver.findElement(By.xpath("//span[@id='number-button']")).click();
        // 2.1 - Nó sẽ xổ ra chứa hết tất cả các item
        // 2.2 - Nó sẽ xổ ra nhưng chỉ cứa 1 phần và đang load thêm
        // Ngàn/Triệu record
        // CHờ cho nó xổ ra tất cả các item trong dropdown
        // Xuất hiện đầy đủ trong HTML
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));

        List<WebElement> allItem = driver.findElements(By.cssSelector("ul#number-menu div"));
        // allItem đang lưu trữ 19 item bên trong
        for (WebElement item: allItem) {
            //String
            if (item.getText().equals("15")) {
                item.click();
                break; // thoát vòng lặp (for/ while/ do-while/ swich-case)
            }



        }


        // 3. - Nếu như item cần chọn nó hiển thị thì click vào
        // 4. - Trước khi click cần kiểm tra nếu như text của item bằng cới item cần chọn thì click vào

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
