package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void mainTest() {
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
}
