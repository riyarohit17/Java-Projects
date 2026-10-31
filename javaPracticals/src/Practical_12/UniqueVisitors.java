package Practical_12;
import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {

        String[] names = {
                "Riya", "Amit", "Neha", "Riya",
                "Rahul", "Amit", "Priya", "Neha",
                "Karan", "Rahul"
        };

        HashSet<String> uniqueUsers = new HashSet<>();
        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>();

        for (String name : names) {
            uniqueUsers.add(name);
            orderedUsers.add(name);
        }

        System.out.println("Total Unique Visitors: "
                + uniqueUsers.size());
        System.out.println("First Three Distinct Names:");

        int count = 0;
        for (String name : orderedUsers) {
            if (count == 3) {
                break;
            }
            System.out.println(name);
            count++;
        }
    }
}
