package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Draw {
    private final ArrayList<Integer> NUMBERS = new ArrayList<>();
    private ArrayList<Ticket> TICKETS = new ArrayList<>();
    private final ArrayList<Integer> DRAW = new ArrayList<>();
    public Draw(){
        for (int i = 0; i < 50; i++) {
            this.NUMBERS.add(i+1);
        }

    }
    public void toDraw(){
        Random r = new Random();
        int x;
        for(int i = 0; i < 5; i++){
            x = r.nextInt(50 - i);
            DRAW.add(NUMBERS.remove(x));
        }
    }

    public ArrayList<Ticket> getTICKETS(){
        return this.TICKETS;
    }
//    public void extraDraw(){
//        Random r = new Random();
//        int x = r.nextInt(50) + 1;
//        if(NUMBERS.contains(x)){
//            x = r.nextInt(50) + 1;
//        }
//        NUMBERS.add(NUMBERS.size()- 1, x);
//    }
    public ArrayList<Integer> getDRAW(){
        return DRAW;
    }
    public void addTickets(Ticket t){
        this.TICKETS.add(t);
    }
}

