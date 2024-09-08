import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        GravaArquivo gravaArquivo = new GravaArquivo();
        List<String> linhas = new LinkedList<>();
        List<String> colunas = new LinkedList<>();

        int menu = 1;
        Scanner entrada = new Scanner(System.in);
        do {
            exibeMenu();
            menu = entrada.nextInt();
            entrada.nextLine();

            switch (menu) {
                case 1:
                    System.out.println("Defina as colunas separadas por vírgula:");
                    String colEntrada = entrada.nextLine();
                    String[] colArray = colEntrada.split(",");
                    for (String col : colArray) {
                        colunas.add(col);
                    }
                    gravaArquivo.gravaColuna(colArray);
                    break;

                case 2:
                    System.out.println("Digite as linhas separadas por vírgula:");
                    for (String string : colunas) {
                        System.out.print(string + "|");
                    }
                    System.out.print("\n");
                    String linentrada = entrada.nextLine();
                    String[] linArray = linentrada.split(",");
                    gravaArquivo.gravaLinhas(linArray);
                    linhas.clear();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (menu != 0);

        entrada.close();  // Fechar o scanner
    }
    public static void exibeMenu() {
        System.out.println("1 - Criar colunas");
        System.out.println("2 - Criar linhas");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
}
