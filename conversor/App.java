package conversor;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Converter moeda");
            System.out.println("2. Converter número para romano");
            System.out.println("3. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    
                    System.out.print("Digite a moeda de origem (ex: brl): ");
                    String moedaOrigem = scanner.nextLine();
                    
                    System.out.print("Digite a moeda de destino (ex: usd): ");
                    String moedaDestino = scanner.nextLine();
                    
                    System.out.print("Digite o valor a ser convertido: ");
                    double valor = scanner.nextDouble();
                    
                    
                    System.out.println("Resultado: " + Cambio.convert(valor, moedaOrigem, moedaDestino));
                    break;

                case 2:
                    
                    System.out.print("Digite um número inteiro para converter em romano: ");
                    int numero = scanner.nextInt();
                    System.out.println("Resultado: " + ConvRomano.toRoman(numero));
                    break;

                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}
