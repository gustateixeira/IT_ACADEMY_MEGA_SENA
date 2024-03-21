package org.example;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Node;
import org.junit.jupiter.api.Test;
import org.junit.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void checkResultsTest() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        ArrayList<Ticket> tickets = new ArrayList<>();
        int [] tick = {1,2,3,4,5};
        int [] tick2 = {1,2,3,4,6};
        int [] tick3 = {1,2,3,4,7};

        Gambler g = new Gambler("Pedro", "60138525021");
        Gambler h = new Gambler("Zedro", "60138525021");
        Gambler i = new Gambler("Alex Pedro", "60138525021");



        Ticket ti = new Ticket(tick, g );
        Ticket ti2 = new Ticket(tick2, h );
        Ticket ti3 = new Ticket(tick3, i );

        tickets.add(ti);
        tickets.add(ti2);
        tickets.add(ti3);
        Collections.sort(tickets);
        System.out.println(tickets);
        int [][]n = new int [3][3];
        for (int j = 0; j < n.length; j++) {
            for (int k = 0; k < n.length; k++) {
                n[j][k] = 2;
            }
        }
        System.out.println("Quantidade de números: ".length());
    }


    @Test
    public void removeTest(){
        ArrayList<Integer> n = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            n.add(i);
        }
        n.remove(3);
        System.out.println(n);
    }
    @Test
    public void fillArrayListTest(){
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 0; i <= 50; i++){
            if(i % 2 == 0) {
                a.add(i);
                a.add(i);
                a.add(i);
                a.add(i);
            }
            else{
                a.add(i);
            }


        }
        ArrayList<LinkedList<Integer>> n = new ArrayList<>();
        for(int i= 0; i < 50; i++){
            LinkedList<Integer> l = new LinkedList<>();
            l.add(i+1);
            int finalI = i;
            l.add((int) a.stream().filter(k -> k == finalI).count());
            n.add(l);
        }
        for(LinkedList<Integer> i : n){
            System.out.println(Arrays.toString(i.toArray()));
        }
        System.out.println(n);
    }
}
