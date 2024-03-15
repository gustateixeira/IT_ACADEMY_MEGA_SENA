package org.example;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        Draw d = new Draw();
        int [] i = {1,2,3,4,5};
        Gambler g = new Gambler("Pedro José", "60138525021");
        Ticket t = new Ticket(i, g);
        int []  n = d.toDraw();
        System.out.println(Arrays.toString(n));
    }


}
