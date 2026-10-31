package Practical_1;


import java.util.Scanner;

record Vehicle(String number, String type) {}

public class TollBooth {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int totalToll = 0;
        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;

        while (true) {

            System.out.print("Enter Vehicle Number: ");
            String number = sc.next();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter Vehicle Type (bike/car/truck): ");
            String type = sc.next().toLowerCase();

            Vehicle vehicle = new Vehicle(number, type);

            int toll = switch (vehicle.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };

            totalToll += toll;

            switch (vehicle.type()) {
                case "bike" -> bikeCount++;
                case "car" -> carCount++;
                case "truck" -> truckCount++;
            }
        }

        System.out.println("Total Toll = " + totalToll);

        if (bikeCount >= carCount && bikeCount >= truckCount)
            System.out.println("Most Frequent = bike");
        else if (carCount >= bikeCount && carCount >= truckCount)
            System.out.println("Most Frequent = car");
        else
            System.out.println("Most Frequent = truck");

        sc.close();
    }
}



