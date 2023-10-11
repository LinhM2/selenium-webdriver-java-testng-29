package javaTester;

public class Topic_07_String {
    public static void main(String[] args) {
        String firstName = "Auto";
        String lastName = "Test";

        // cách 1
        String fullName = firstName + " " + lastName ;
        System.out.println(fullName);

        // cách 2
        fullName = firstName.concat(" ").concat(lastName);
        System.out.println(fullName);

        String hotelMsg = "Welcome " + fullName + " to ...";
        System.out.println(hotelMsg);


    }

}
