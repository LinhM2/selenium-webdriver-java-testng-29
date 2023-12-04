package javaTester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_11_List {
    @Test
    public void testList() {
        List<String> studentName = new ArrayList<String>();
        studentName.add("A");
        studentName.add("B");
        studentName.add("C");

        // 3 element trong list
        System.out.println(studentName.size());

        System.out.println(studentName.get(0));
        System.out.println(studentName.get(1));
        System.out.println(studentName.get(2));

        // lấy thằng cuối cùng = tổng - 1
        System.out.println(studentName.get(studentName.size() - 1 ));

    }

}
