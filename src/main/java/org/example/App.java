package org.example;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @Author :  Gustavo Hillesheim Teixeira
 * @Email :  g.hillesheim@edu.pucrs.br
 * Dell IT Academy turma 20.
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
                ArrayList<Integer> numbers;
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
                sc.close();
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
                int i =0;
                if(winners.isEmpty()){
                    while(i < 25){
                        d.extraDraw();
                        numbers = d.getDRAW();
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
                ArrayList<Integer> allNumbers = storeElements(d.getTICKETS());
                Collections.sort(winners);
                int [][] c = sort(allNumbers);
                Arrays.sort(c[0]);
                System.out.println(data(winners, numbers, i,c));
                saveAll(d.getTICKETS());
            }
    }

    /**
     * método para o modo "Surpresinha"
     * @return retorna uma array de inteiros gerados pseudoaleatóriamente.
     */
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

    /**
     * método para salvar todos os números dos tickets gerados em uma Lista
     * @param tickets
     * @return ArrayList de inteiros, referente à todos os tickets gerados
     */
    public static ArrayList<Integer> storeElements(ArrayList<Ticket> tickets){
        ArrayList<Integer> allNumbers  = new ArrayList<>();
        for(Ticket t : tickets){
            for(int i : t.getNUMBERS()){
                allNumbers.add(i);
            }
        }
        return allNumbers;
    }

    /**
     *
     * @return vetor de inteiros com os números selecionados pelo usuário
     */

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

    /**
     *
     * @param numbers ArrayList<Integer> de todos os números possíveis
     * @param tickets ArrayList<Ticket> de todos os tickets criados
     * @return ArrayList<Ticket> dos tickets vencedores
     */

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
    public static int [][] sort(ArrayList<Integer> allNumbers) {
        int[][] data = new int[50][2];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = i + 1;
        }
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            data[i][1] = (int) allNumbers.stream().filter(k -> k == finalI + 1).count();
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][1] > data[j][1]) {
                    int[] tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                }
            }
        }
        return data;
    }

    /**
     *
     * @param w ArrayList dos tickets vencedores
     * @param n ArrayList dos números sorteados
     * @param r número de rodadas feitas até um vencedor ou até 25
     * @param
     * @return
     */
    public static String data(ArrayList<Ticket> w, ArrayList<Integer> n, int r, int [][] numbers ){
        String aux = "";
        for (int [] i:  numbers) {
            aux += Arrays.toString(i)+ "\n";
        }
        return "Números sorteados: " +  n + "\n" +
                "Número de rodadas: " + r + "\n" +
                "Número de apostas vencedoras: " + w.size() + "\n" +
                "Apostas vencedoras: " + w + "\n" +
                "Quantidade de números: " +"\n" + aux +  "\n";
    }
       public static void saveAll(ArrayList<Ticket> tickets) {
        try {
            FileWriter fileWriter = new FileWriter("save.csv");
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            for (Ticket t : tickets) {
                StringBuilder linha = new StringBuilder();
                linha.append(t.getOWNER().getNAME()).append(" ").append(Arrays.toString(t.getNUMBERS()));
                csvWriter.writeNext(linha.toString().split(","));
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            }
       }
}



