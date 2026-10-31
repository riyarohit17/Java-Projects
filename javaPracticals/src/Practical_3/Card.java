package Practical_3;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

public class Card
{
    private String rank, suit;

    public Card(String rank, String suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString()
    {
        return rank + " of " + suit;
    }

    public boolean equals(Object obj)
    {
        Card c = (Card) obj;

        if(rank.equals(c.rank) && suit.equals(c.suit))
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
        return Objects.hash(rank, suit);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cards : ");
        int n = sc.nextInt();

        Card cards[] = new Card[n];

        for(int i = 0; i < cards.length; i++)
        {
            System.out.println("\nEnter Card " + (i + 1));

            System.out.print("Enter Rank : ");
            String rank = sc.next();

            System.out.print("Enter Suit : ");
            String suit = sc.next();

            cards[i] = new Card(rank, suit);
        }

        boolean duplicate = false;

        for(int i = 0; i < cards.length; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(cards[i].equals(cards[j]))
                {
                    System.out.println("\nDuplicate found : " + cards[i]);
                    duplicate = true;
                    break;
                }
            }
        }

        if(duplicate == false)
        {
            System.out.println("\nNo Duplicate Card Found.");
        }
    }
}