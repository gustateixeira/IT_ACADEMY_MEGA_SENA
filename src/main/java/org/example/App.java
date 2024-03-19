package org.example;

import javax.print.DocFlavor;
import java.sql.SQLOutput;
import java.util.*;

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
                while (true) {
                    if(input == 0){
                        System.out.println("DESEJA ENCERRAR A FASE DE APOSTAS?");
                        System.out.println("    SIM - '1'       NÃO - '2' ");
                        input = sc.nextInt();
                        if(input == 1){
                            break;
                        }
                    }
                    System.out.println("Pressione '2' para registrar nova aposta");
                    System.out.println("Pressione '3' listar apostas");
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
                                System.out.printf("%d apostas surpresas serão criadas.%n", input);
                                for(int i =0; i < input; i++) {
                                    int[] ti = surprise();
                                    System.out.println(Arrays.toString(ti));
                                    Ticket t = new Ticket(ti, g);
                                    d.addTickets(t);
                                }
                                break;

                        }
                    }
                    else if(input == 3){
                        for (Ticket t : d.getTICKETS()){
                            System.out.println(t);
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
                System.out.println(numbers);
                ArrayList<Ticket> winners = checkResults(numbers,d.getTICKETS());
                ArrayList<Integer> data = new ArrayList<>();
                int i =0;
                if(winners.isEmpty()){
                    while(i < 25){
                        d.extraDraw();
                        numbers = d.getDRAW();
                        Thread.sleep(500);
                        winners =  checkResults(numbers, d.getTICKETS());
                        if(winners.isEmpty()){
                            i++;
                        }
                        else{
                            System.out.println();
                            System.out.println(winners);
                            break;
                        }
                        if(i == 25){
                            System.out.println("O sorteio não teve vencedores.");
                        }
                    }
                }
                Collections.sort(winners);
                System.out.println(data(winners, numbers, i));

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
        for (Ticket ticket : tickets) {
            int equals = 0;
            int[] n = ticket.getNUMBERS();
            for (int k : n) {
                if (numbers.contains(k)) {
                    equals++;
                }
            }
            if (equals == 5) {
                winners.add(ticket);
            }
        }
        return winners;
    }
    public static String data(ArrayList<Ticket> w,ArrayList<Integer> n, int r){


        return "Números sorteados: " +  n + "\n" +
                "Número de rodadas: " + r + "\n" +
                "Número de apostas vencedoras: " + w.size() + "\n" +
                "Apostas vencedoras: " + w + "\n";
    }
}


