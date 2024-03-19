package org.example;

public class Gambler implements Comparable<Gambler> {
    private final String NAME;
    private final String CPF;
    public Gambler(String name, String cpf) {
        this.CPF = cpf;
        this.NAME = name;
    }
    public String getNAME(){
        return this.NAME;
    }
    public String toString(){
        return this.NAME.toUpperCase() + "->" + "TICKET : ";
    }
    public int compareTo(Gambler s)
    {
        return this.NAME.compareTo(s.NAME);
    }

}
