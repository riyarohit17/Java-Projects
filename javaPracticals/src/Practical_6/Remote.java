package Practical_6;

import java.util.Scanner;

interface Switchable {

    void on();
    void off();

    default void toggle() {
        System.out.println("Device toggle");
        on();
    }
}

class Fan implements Switchable {

    public void on() {
        System.out.println("Fan ON");
    }

    public void off() {
        System.out.println("Fan OFF");
    }
}

class Light implements Switchable {

    public void on() {
        System.out.println("Light ON");
    }

    public void off() {
        System.out.println("Light OFF");
    }
}


// Functional Interface
@FunctionalInterface
interface SwitchRule {

    boolean canSwitchOn(Switchable device, int hour);
}


public class Remote {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Fan fan = new Fan();
        Light light = new Light();

        Switchable[] devices = {fan, light};

        System.out.println("1. Fan");
        System.out.println("2. Light");

        System.out.print("Enter device choice: ");
        int choice = sc.nextInt();

        Switchable device;

        if (choice == 1) {
            device = fan;
        } else {
            device = light;
        }


        System.out.println("\n......Toggle.......");

        device.toggle();

        System.out.print("\nEnter hour (0-23): ");
        int hour = sc.nextInt();

        SwitchRule rule1 = new SwitchRule() {

            public boolean canSwitchOn(Switchable device, int hour) {

                return hour >= 6 && hour <= 22;
            }
        };


        SwitchRule rule2 =
                (device1, time) -> time >= 6 && time <= 22;


        System.out.println("\n......Anonymous Class.......");

        if (rule1.canSwitchOn(device, hour)) {
            System.out.println("Device can be switched ON");
        } else {
            System.out.println("Device cannot be switched ON");
        }


        System.out.println("\n......Lambda.......");

        if (rule2.canSwitchOn(device, hour)) {
            System.out.println("Device can be switched ON");
        } else {
            System.out.println("Device cannot be switched ON");
        }

        sc.close();
    }
}