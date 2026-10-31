package Practical_6;

import java.util.Scanner;

@FunctionalInterface
interface Notifier {
    void send(String message);
}


interface Urgent {
}


class EmailSender implements Urgent {

    void sendEmail(String message) {
        System.out.println("Email: " + message);
    }
}


class SMSSender {

    void sendSMS(String message) {
        System.out.println("SMS: " + message);
    }
}


public class Notification {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String message = sc.nextLine();


        EmailSender email = new EmailSender();

        SMSSender sms = new SMSSender();


        Notifier emailNotifier = (msg) -> {
            email.sendEmail(msg);
        };


        Notifier smsNotifier = (msg) -> {
            sms.sendSMS(msg);
        };


        Notifier[] notifiers = {
                emailNotifier,
                smsNotifier
        };


        System.out.println("\n......Broadcasting Message.......");

        for (Notifier notifier : notifiers) {

            if (notifier == emailNotifier && email instanceof Urgent) {

                notifier.send(message);
                notifier.send(message);

            } else {

                notifier.send(message);
            }
        }


        sc.close();
    }
}