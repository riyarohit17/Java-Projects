package Practical_2;

class ParkingLot
{
    private int twoWheelers;
    private int fourWheelers;

    private final int twoCap;
    private final int fourCap;

    private static long revenue = 0;

    ParkingLot(int twoCap, int fourCap)
    {
        this.twoCap = twoCap;
        this.fourCap = fourCap;

        twoWheelers = 0;
        fourWheelers = 0;
    }

    void park(String type)
    {
        if(type.equals("two"))
        {
            if(twoWheelers < twoCap)
            {
                twoWheelers++;
                revenue = revenue + 20;
                System.out.println("Two-wheeler parked.");
            }
            else
            {
                System.out.println("Full");
            }
        }
        else if(type.equals("four"))
        {
            if(fourWheelers < fourCap)
            {
                fourWheelers++;
                revenue = revenue + 40;
                System.out.println("Four-wheeler parked.");
            }
            else
            {
                System.out.println("Full");
            }
        }
    }

    void leave(String type)
    {
        if(type.equals("two"))
        {
            if(twoWheelers > 0)
            {
                twoWheelers--;
                System.out.println("Two-wheeler left.");
            }
        }
        else if(type.equals("four"))
        {
            if(fourWheelers > 0)
            {
                fourWheelers--;
                System.out.println("Four-wheeler left.");
            }
        }
    }

    public static void main(String args[])
    {
        ParkingLot p = new ParkingLot(2, 2);

        p.park("two");
        p.park("two");
        p.park("two");

        p.park("four");
        p.park("four");
        p.park("four");

        p.leave("two");
        p.leave("four");

        p.park("two");
        p.park("four");

        System.out.println();

        System.out.println("Two-wheelers Parked : " + p.twoWheelers);
        System.out.println("Four-wheelers Parked : " + p.fourWheelers);
        System.out.println("Revenue : " + revenue);
    }
}