package org.example;

import javax.print.DocFlavor;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pressione '1' para Iniciar");
        System.out.println("Pressione '0' para Finalizar a fase de apostas");
        int input = sc.nextInt();
            if (input == 1) {
                Draw d = new Draw();
                ArrayList<Integer> numbers = new ArrayList<>();
                while (input != 0) {
                    System.out.println("Pressione '2' para registrar nova aposta");
                    System.out.println("Pressione '0' para Finalizar a fase de apostas");
                    input = sc.nextInt();
                    System.out.println();
                    if (input == 2) {
                        System.out.println("Preencha seus dados!");
                        System.out.print("Nome: ");
                        String nome = sc.next();
                        System.out.print("CPF: ");
                        String cpf = sc.next();
                        Gambler g = new Gambler(nome, cpf);
                        if(cpf.length() != 11){
                            throw new IllegalArgumentException();
                        }
                        System.out.println("Pressione '1' para escolher os números!");
                        System.out.println("Pressione '2' para o modo 'SURPRESINHA!'");
                        System.out.println();
                        switch (sc.nextInt()) {
                            case 1:
                                System.out.println("Quantas apostas deseja fazer?");
                                input = sc.nextInt();
                                System.out.printf("%d apostas serão criadas. ", input);
                                System.out.println("Selecione 5 números de 1 à 50, \n" + "OBS: Não são permitidos números repetidos.");
                                for(int i =0 ; i < input; i++) {
                                    int[] t = createTicket();
                                    System.out.println(Arrays.toString(t));
                                    Ticket ticket = new Ticket(t, g);
                                    d.addTickets(ticket);
                                }
                                break;
                            case 2:
                                System.out.println("Quantas apostas deseja fazer?");
                                input = sc.nextInt();
                                System.out.printf("%d apostas surpresas serão criadas. ", input);
                                for(int i =0; i < input; i++) {
                                    int[] ti = surprise();
                                    System.out.println(Arrays.toString(ti));
                                    Ticket t = new Ticket(ti, g);
                                    d.addTickets(t);
                                }
                                break;
                        }
                    }
            }
                System.out.println("Fase de apostas encerrada!");
                System.out.println("Fase de sorteio será iniciada em: ");
                for(int i = 5; i > 0; i--){
                    System.out.printf("%d%n", i);
                    Thread.sleep(1000);
                }
                System.out.println("Sorteio INICIADO!");
                Thread.sleep(500);
                d.toDraw();
                numbers = d.getDRAW();
                for (int i: numbers) {
                    System.out.printf("#%d%n", i);
                    Thread.sleep(250);
                }
                System.out.println(numbers);
                ArrayList<Ticket> winners = checkResults(numbers,d.getTICKETS());
                System.out.println(winners);
            }
    }
    public static int[] surprise(){
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 50; i ++) {
                numbers.add(i+1);
        }
        Random r = new Random();
        int [] t = new int[5];
        int x;
        for(int i = 0; i < 5; i++){
            x = r.nextInt(50 - i);
            t[i] = numbers.remove(x);
        }
        return t;
    }

    public static int[] createTicket(){
        int [] t = new int[5];
        Scanner sc = new Scanner(System.in);
        for(int i =0; i < 5; i++){
            int n = sc.nextInt();
            for(int j =0; j < 5; j++){
                if(n == t[j]){
                    throw new IllegalArgumentException("Não é possível selecionar dois números iguais");
                } else if (n > 50 || n < 0) {
                    throw new IllegalArgumentException("Selecione números entre 1 e 50");
                }
            }
            t[i] = n;
        }
        return t;
    }

    public static ArrayList<Ticket> checkResults(ArrayList<Integer> numbers, ArrayList<Ticket> tickets){
        ArrayList<Ticket> winners = new ArrayList<>();
        for(int i = 0; i < tickets.size(); i++){
            int equals =0;
            Ticket t = tickets.get(i);
            int [] n = t.getNUMBERS();
            for(int j = 0; j < n.length; j++){
                if(numbers.get(j) == n[j]){
                    equals++;
                }
            }
            if(equals == 5){
                winners.add(t);
            }
        }
        return winners;
    }
}


