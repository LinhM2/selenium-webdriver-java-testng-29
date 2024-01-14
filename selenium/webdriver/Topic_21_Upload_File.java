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

public class Topic_21_Upload_File {
    WebDriver driver;

    // lấy ra đường dẫn tương đối tại thư mục hiện tại
    String projectPath = System.getProperty("user.dir");

    // đặt tên file
    String linh1921Name = "linh1921.jpg";
    String linh1922Name = "linh1922.jpg";
    String linh1923Name = "linh1923.jpg";

    String linh1921FilePath = projectPath + "\\uploadFiles\\" + linh1921Name;
    String linh1922FilePath = projectPath + "\\uploadFiles\\" + linh1922Name;
    String linh1923FilePath = projectPath + "\\uploadFiles\\" + linh1923Name;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_File() {
        // Topic_14 [Exercise]: TC 01
        // đầu tiên phải thêm thư mục: uploadFiles >> add file muốn upload vào

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        // đặt biến
        By uploadBy = By.cssSelector("input[name='files[]']");

        //Loaded lần lượt 3 file lên
        driver.findElement(uploadBy).sendKeys(linh1921FilePath);
        sleepINSeconds(2);

        driver.findElement(uploadBy).sendKeys(linh1922FilePath);
        sleepINSeconds(2);

        driver.findElement(uploadBy).sendKeys(linh1923FilePath);
        sleepINSeconds(2);

        // verify file loaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + linh1921Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + linh1922Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + linh1923Name + "']")).isDisplayed());

        // click button start (có 3 file >> có 3 button)
        List<WebElement> startButton = driver.findElements(By.cssSelector("td>button.start"));

        // Dùng vòng lặp để click 3 button: 2 cách
        // c1: check điều kiện thì dùng classis for
        /*for (int i = 0; i <startButton.size() ; i++) {
            // điều kiện: nếu button star hiển thị thì click
            if (startButton.get(1).isDisplayed()){
                startButton.get(i).click();
                sleepINSeconds(2);
            }

        }*/

        // c2: không check điều kiện thì dùng For-each sẽ gọn hơn
        for (WebElement button: startButton) {
            button.click();
            sleepINSeconds(2);
        }

        // verify file UPloaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + linh1921Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + linh1922Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + linh1923Name + "']")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadBy = By.cssSelector("input[name='files[]']");

        // Loaded cùng lúc 3 file lên: mình xen ký tự xuống dòng (\n) ở giữa
        // cái này sẽ k loaded được quá nhiều file 1 lúc >> vì nó giới hạn ký tự
        driver.findElement(uploadBy).sendKeys(linh1921FilePath + "\n" + linh1922FilePath + "\n" + linh1923FilePath );
        sleepINSeconds(2);

        // verify file loaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + linh1921Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + linh1922Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + linh1923Name + "']")).isDisplayed());

        // click button start (có 3 file >> có 3 button)
        List<WebElement> startButton = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button: startButton) {
            button.click();
            sleepINSeconds(2);
        }

        // verify file UPloaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + linh1921Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + linh1922Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + linh1923Name + "']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {driver.quit();}

    public void sleepINSeconds(long timeINSecond) {
        try {
            Thread.sleep(timeINSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
