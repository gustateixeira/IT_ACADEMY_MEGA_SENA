package org.example;

import java.util.Arrays;

public class Ticket {
    private static int idCounter = 1000;
    private final Gambler OWNER;
    private final int [] NUMBERS;
    private final int ID;

    public Ticket( int [] t, Gambler g){
        if(t.length != 5){
            throw new IllegalArgumentException("Não é possível selecionar mais ou menos de 5 números");
        }
        this.OWNER = g;
        this.NUMBERS= t;
        this.ID = idCounter;
        idCounter++;
    }

    public int getID() {
        return ID;
    }
    public int [] getNUMBERS(){
        return this.NUMBERS;
    }
    public Gambler getOWNER(){
        return OWNER;
    }
    public String toString(){
        return  OWNER.toString() + Arrays.toString(NUMBERS);
    }
}
