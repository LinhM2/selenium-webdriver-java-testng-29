package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Scope {
    // các biến được khai báo ở bên ngoài hàm -> phạm vi là Class
    // Biến Global (toàn cục)
    // có thể dùng cho tất cả cấc hàm ở trong 1 class
    WebDriver driver;

    String homPageUrl; // khai báo: Declare

    String fullName = "linh test"; // Khai báo + khởi tạo (Initial

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        // Các biến được khai báo ở trong 1 hàm/ blcok code -> phạm vi Local (cục bộ)
        // Dùng cho 1 cái hàm có được khai báo/ block code được sinh ra
        String homPageUrl = "https://www.facebook.com";

        // trong 1 hàm nếu như có 2 cái biển cũng tên (Global/ Local) thì nó sẽ ưu tiên lâ biến Local dùng
        // 1 biến Local nếu như gọi tới dùng mà chưa được khởi tạo thì nó sẽ bị lỗi
        driver.get(homPageUrl);

        // Nếu trong 1 hàm có 2 biến cùng tên (glocal/local) mà mình muốn lấy biến glocal ra để dùng
        // Từ khóa this
        //biên global chưa khởi tạo mà gọi ra dùng thì nó sẽ k báo lỗi ở level compile code
        // nhưng báo lỗi ở level runtime
        driver.get(this.homPageUrl);


    }

}
