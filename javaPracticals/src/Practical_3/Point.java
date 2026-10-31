package Practical_3;

import java.util.Scanner;
import java.util.Objects;

public class Point
{
    private int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Object obj)
    {
        Point p = (Point) obj;

        if(x == p.x && y == p.y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x : ");
        int x = sc.nextInt();

        System.out.print("Enter y : ");
        int y = sc.nextInt();

        Point point = new Point(x, y);

        System.out.println("Point = " + point);
    }
}