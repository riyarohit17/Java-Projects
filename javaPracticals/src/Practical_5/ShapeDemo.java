package Practical_5;

import java.util.Scanner;

abstract class Shape {

    abstract double area();
}

class Circle extends Shape {

    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {

    double length;
    double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double area() {
        return length * width;
    }
}

class Triangle extends Shape {

    double base;
    double height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    double area() {
        return 0.5 * base * height;
    }
}

public class ShapeDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Circle input
        System.out.print("Enter radius of Circle: ");
        double radius = sc.nextDouble();

        // Rectangle input
        System.out.print("Enter length of Rectangle: ");
        double length = sc.nextDouble();

        System.out.print("Enter width of Rectangle: ");
        double width = sc.nextDouble();

        // Triangle input
        System.out.print("Enter base of Triangle: ");
        double base = sc.nextDouble();

        System.out.print("Enter height of Triangle: ");
        double height = sc.nextDouble();

        Shape[] shapes = {
                new Circle(radius),
                new Rectangle(length, width),
                new Triangle(base, height)
        };

        double total = 0;
        double largest = 0;

        for (Shape s : shapes) {

            double a = s.area();

            System.out.println("Area = " + a);

            total = total + a;

            if (a > largest) {
                largest = a;
            }
        }

        System.out.println("Total area = " + total);
        System.out.println("Largest area = " + largest);

        sc.close();
    }
}


