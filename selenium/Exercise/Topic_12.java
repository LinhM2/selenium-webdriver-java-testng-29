package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_12 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Dropdown_Radio() {
        driver.get("https://demo.seleniumeasy.com/input-form-demo.html");

        // stateDropdown
        Select stateDropdown = new Select(driver.findElement(By.name("state")));
        stateDropdown.selectByVisibleText("Alabama");
        Assert.assertFalse(stateDropdown.isMultiple());
        Assert.assertEquals(stateDropdown.getOptions().size(), 52);

        // Radiobutton
        WebElement radio = driver.findElement(By.cssSelector("input[value='yes']"));
        Assert.assertTrue(isElementEnabled(radio));
        radio.click();
        Assert.assertTrue(isElementSelected(radio));

    }


    @AfterClass
    public void afterClass() {driver.quit();}

    public boolean isElementEnabled(WebElement element) {
        if (element.isEnabled()) {
            System.out.println("Element is enabled");
            return true;
        } else {
            System.out.println("Element is disabled");
            return false;
        }
    }

    public boolean isElementSelected(WebElement element) {
        if(element.isSelected()) {
            System.out.println("Element is selected");
            return true;
        }
        else {
            System.out.println("Element is de-selected");
            return false;
        }
    }
}
