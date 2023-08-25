package javaTester;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Topic_01_Data_type {
    // Kiểu dữ liệu trong java: 2 nhóm
    // Nhóm 1: Kiểu dữ liệu Nguyên Thủy (primitive type)
    // Số Nguyên: byte - short -  int - long
    byte sNumber = 40;
    short sNumber = 3000;
    int sNumber = 15635658;
    long sNumber = 234343400;

    // Số thực: float - double
    float fNumber = 9.99f;
    double fNumber = 35.800789d;

    // Kí tự: char
    // đại diện cho 1 kí tự
    char c = '$';
    char d = 'i';

    // logic boolean (luận lý)
    // kết quả bài test: pass/fail (không ngoại lệ)
    boolean status = false;

    // Nhóm 2: Kiểu dữ liệu tham chiếu (Reference type)
    // Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Select select = new Topic_01_Data_type();

    By by;

    // Interface
    webDriver driver;

    // Object
    Object name = "test FC";

    // Array (kiểu nguyên thủy/tham chiếu)
    int[] studentAge = {15, 20, 8};
    String[] studentName = {"test1", "test2"};

    // Colection: List/ Set/ Queue
    List<String> stuadentAddress = new ArrayList<String>();

    // String - Chuooic kí tự
    String fullName = "Nguyễn Văn A";

}
