package Practical_2;

class CinemaShow
{
    private String title;
    private int seatsAvailable;

    private final int capacity;

    private static int totalBooked = 0;

    CinemaShow(String title, int capacity)
    {
        this.title = title;
        this.capacity = capacity;
        seatsAvailable = capacity;
    }

    CinemaShow(String title)
    {
        this(title, 100);
    }

    boolean book(int n)
    {
        if(n <= seatsAvailable)
        {
            seatsAvailable = seatsAvailable - n;
            totalBooked = totalBooked + n;
            return true;
        }
        else
        {
            return false;
        }
    }

    void cancel(int n)
    {
        seatsAvailable = seatsAvailable + n;

        if(seatsAvailable > capacity)
        {
            seatsAvailable = capacity;
        }
    }

    int getSeatsAvailable()
    {
        return seatsAvailable;
    }

    static int getTotalBooked()
    {
        return totalBooked;
    }

    public static void main(String args[])
    {
        CinemaShow show = new CinemaShow("Avengers", 50);

        System.out.println("Book 20 Seats : " + show.book(20));
        System.out.println("Seats Left : " + show.getSeatsAvailable());

        System.out.println();

        System.out.println("Book 25 Seats : " + show.book(25));
        System.out.println("Seats Left : " + show.getSeatsAvailable());

        System.out.println();

        System.out.println("Book 10 Seats : " + show.book(10));
        System.out.println("Seats Left : " + show.getSeatsAvailable());

        System.out.println();

        show.cancel(15);
        System.out.println("After Cancel 15 Seats");
        System.out.println("Seats Left : " + show.getSeatsAvailable());

        System.out.println();

        System.out.println("Book 10 Seats : " + show.book(10));
        System.out.println("Seats Left : " + show.getSeatsAvailable());

        System.out.println();

        System.out.println("Total Booked Seats : " + CinemaShow.getTotalBooked());
    }
}









