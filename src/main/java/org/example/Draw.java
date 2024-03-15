package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Draw {
    private final int [] NUMBERS = new int[5];
    private final ArrayList<Ticket> TICKETS = new ArrayList<>();
    public Draw(){

    }

    public void addTickets(Ticket t){
        TICKETS.add(t);
    }

    public int [] toDraw(){
        Random r = new Random();
        for (int i =0; i < NUMBERS.length; i++) {
            int x = r.nextInt(50) + 1;
            for (int number : NUMBERS) {
                if (x == number) {
                    x = r.nextInt(50) + 1;
                }
            }
            NUMBERS[i] = x;
        }
        return NUMBERS;
    }
}

