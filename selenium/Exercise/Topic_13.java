package Exercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void Login_01_Success() {

    }

    @Test
    public void Login_02_Invalid_Email() {

    }

    @Test
    public void Login_03_Invalid_Password() {

    }

    @Test
    public void Login_04_Incorrect_Email_And_Password() {

    }

    @Test
    public void Login_04_Logout() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
