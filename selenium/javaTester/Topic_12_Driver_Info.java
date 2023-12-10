package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_12_Driver_Info {
    WebDriver driver;
    @Test
    public void testDriverInfomation() {
        driver = new FirefoxDriver();
        // ở trên hệ đều hành nào
        // brower nào
        // drower class
        // ID cửa drive là gì
        // FirefoxDrive: firefox on windows (8ea214d6-311d-4370-b1be-ba046480d65b)

        System.out.println(driver.toString());

        if (driver.toString().contains("firefox")){
            // Scroll
        }

        driver.quit();
    }

}
