package webdriver;

import org.bouncycastle.util.encoders.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectLocation = System.getProperty("user.dir");
    String username = "admin";
    String password = "admin";
    By resultText = By.cssSelector("p#result");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        // TC 07 - Topic 09
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        
        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        alert.accept();
        sleepINSeconds(3);

        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC_02_Confirm_Alert() {
        // TC 08 - Topic 09
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.dismiss(); // cancel

        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked: Cancel");

        // CLick ok
        /*driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();;
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.accept(); // ok
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked: Ok");*/

    }

    @Test
    public void TC_03_Prompt_Alert() {
        // TC 09 - Topic 09
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String text = "autoTest";
        alert.sendKeys(text);
        alert.accept();

        Assert.assertEquals(driver.findElement(resultText).getText(),"You entered: " + text);

    }

    @Test
    public void TC_04_Authentication_Pass_To_URL() {
        // TC 11 - Topic 09

        // ** cách 1: truyền thằng user/ pass vào url
        // trick - ByPass
        driver.get("https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(
                By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        // Cách 2: Từ page A thao tác lên 1 element nó sẽ qua page B (cần phải thao tác Authen Alert trước)
        driver.get("https://the-internet.herokuapp.com/");

        String authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        driver.get(authenLinkUrl); // 25p


    }

    @Test
    public void TC_05_Authentication_AutoIT() throws IOException {
        // TC 11 - Topic 09

        // cách 2: Chạy trên windows (AutoIT)
        // nhược điểm: (1)k chạy trên mac/ linux, (2) mỗi 1 browser sẽ cần 1 đoạn scrip khác nhau
        // Thực thi đoạn code autoIT để chờ Alert xuất hiện
        Runtime.getRuntime().exec(new String[] { projectLocation + "\\autoIT\\authen_firefox.exe", "admin", "admin" });

        driver.get("https://the-internet.herokuapp.com/basic_auth");
        sleepINSeconds(5);

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }

    @Test
    public void TC_06_Authentication_Selenium_4x() {
        // TC 11 - Topic 09
        // Cách 3:
        // Thư viện Alert ko thử dụng cho Authentication Alert được => vì liên quan đến tính bảo mật
        // Chrome Deptool protocol (CDP) - chome/ Edge
        // cốc cốc/ Opera/ brave - work Around

        /*ChromeOptions option = new ChromeOptions();
        option.setBinary(""); // => 1h16p ?
        driver = new ChromeDriver(option);*/

        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", username, password).getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(By.xpath(
                "//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());


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
