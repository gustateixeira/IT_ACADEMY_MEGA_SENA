package org.example;
import java.util.ArrayList;

public class Gambler {
    private final String NAME;
    private final String CPF;
    public Gambler(String name, String cpf) {
        this.CPF = cpf;
        this.NAME = name;
    }
    public String getNAME(){
        return NAME;
    }
}
