package Practical_11;
import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;
    transient String password;

    Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        System.out.println("--------------------");
    }
}

public class SaveLoad {
    public static void main(String[] args) {

        Student[] students = {
                new Student(1, "Riya", "1234"),
                new Student(2, "Amit", "9876"),
                new Student(3, "Neha", "xy123z")
        };

        System.out.println("Before Serialization:");
        for (Student s : students) {
            s.display();
        }

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream("students.dat"))) {

            out.writeObject(students);
            System.out.println("Objects saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream("students.dat"))) {

            Student[] loadedStudents =
                    (Student[]) in.readObject();

            System.out.println("\nAfter Deserialization:");
            for (Student s : loadedStudents) {
                s.display();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
