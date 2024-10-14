package moedas.conversor.challenge;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Pair> log = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        RequestAPI requestAPI = new RequestAPI();
        Pair pair;

        int menuOption = 1;
        double amount;

        // Loop do menu
        do {
            // Display menu
            displayMenu();
            try {
                menuOption = input.nextInt();
                switch (menuOption){
                    case 1: // Conversões comuns
                        int submenuOption = 0;
                        // Loop do submenu
                        do {
                            displayOption1();
                            try {
                                submenuOption = input.nextInt();
                                switch (submenuOption){

                                    case 1: // Real (BRL) >> Dólar Americano (USD)
                                        System.out.print("Digite o valor a ser convertido: ");
                                        amount = input.nextDouble();
                                        pair = requestAPI.requestPair("BRL", "USD");
                                        System.out.println(pair.getConversionMessage(amount));
                                        log.add(pair);
                                        break;
                                    case 2: // Real (BRL) >> Euro (EUR)
                                        System.out.print("Digite o valor a ser convertido: ");
                                        amount = input.nextDouble();
                                        pair = requestAPI.requestPair("BRL", "EUR");
                                        System.out.println(pair.getConversionMessage(amount));
                                        log.add(pair);
                                        break;
                                    case 3: // Real (BRL) >> Pesos Argentinos (ARS)
                                        System.out.print("Digite o valor a ser convertido: ");
                                        amount = input.nextDouble();
                                        pair = requestAPI.requestPair("BRL", "ARS");
                                        System.out.println(pair.getConversionMessage(amount));
                                        log.add(pair);
                                        break;
                                    case 4: // Dólar Americano (USD) >> Euro (EUR)
                                        System.out.print("Digite o valor a ser convertido: ");
                                        amount = input.nextDouble();
                                        pair = requestAPI.requestPair("USD", "EUR");
                                        System.out.println(pair.getConversionMessage(amount));
                                        log.add(pair);
                                        break;
                                    case 5: // Iene Japonês (JPD) >> Dólar Americano (USD)
                                        System.out.print("Digite o valor a ser convertido: ");
                                        amount = input.nextDouble();
                                        pair = requestAPI.requestPair("JPY", "USD");
                                        System.out.println(pair.getConversionMessage(amount));
                                        log.add(pair);
                                        break;
                                    case 6: // Real (BRL) >> Iene Japonês (JPD)
                                        System.out.print("Digite o valor a ser convertido: ");
                                        amount = input.nextDouble();
                                        pair = requestAPI.requestPair("BRL", "JPY");
                                        System.out.println(pair.getConversionMessage(amount));
                                        log.add(pair);
                                        break;
                                    case 0: // Voltar ao menu
                                        break;
                                    default: // Quando uma opção inválida for inserida no submenu
                                        System.out.println("Opção inválida");
                                        break;
                                }
                            } catch (RuntimeException e){
                                input.nextLine(); // Limpa a leitura
                                System.out.println("Entrada inválida");
                            }
                        } while (submenuOption != 0);
                        break;
                    case 2: // Conversão qualquer
                        System.out.print("Digite o código da moeda que deseja converter: ");
                        input.nextLine();
                        String baseCode = input.nextLine();

                        System.out.print("Digite o código da moeda alvo: ");
                        String targetCode = input.nextLine();

                        System.out.print("Digite o valor a ser convertido: ");
                        amount = input.nextDouble();

                        try {
                            pair = requestAPI.requestPair(baseCode, targetCode);
                            System.out.println(pair.getConversionMessage(amount));
                            log.add(pair);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:  // Histórico
                        if (!log.isEmpty()){
                            for (Pair p : log){
                                String msg = p.baseCode() + " >> " + p.targetCode();
                                System.out.println(msg);
                            }
                        } else {
                            System.out.println("Histórico vazio");
                        }
                        break;
                    case 0: // Sair
                        break;
                    default: // Quando uma opção diferente for inserida
                        System.out.println("Opção inválida");
                }
            } catch (RuntimeException e)
            {
                input.nextLine();
                System.out.println("Entrada inválida");
            }
        } while (menuOption != 0);
    }

    public static void displayMenu(){
        System.out.print("""
                ----------------------------
                1. Conversões mais comuns
                2. Conversão qualquer
                3. Histórico de conversões
                0. Sair
                >\s"""
        );
    }

    public static void displayOption1(){
        System.out.print("""
                -----------------------------------------------
                1. Real (BRL) >> Dólar Americano (USD)
                2. Real (BRL) >> Euro (EUR)
                3. Real (BRL) >> Pesos Argentinos (ARS)
                4. Dólar Americano (USD) >> Euro (EUR)
                5. Iene Japonês (JPD) >> Dólar Americano (USD)
                6. Real (BRL) >> Iene Japonês (JPD)
                0. Voltar
                >\s"""
        );
    }
}
