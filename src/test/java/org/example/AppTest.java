package org.example;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void mainTest() {
        Draw d = new Draw();
        int [] i = {1,2,3,4,5};
        Gambler g = new Gambler("Pedro José", "60138525021");
        Ticket t = new Ticket(i, g);
        int []  n = d.toDraw();
        System.out.println(n);
    }

}
