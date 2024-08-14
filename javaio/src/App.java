import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int condicao;
        List<Pessoa> ListaPessoa = new LinkedList<>();
        Pessoa pessoaAtual = new Pessoa();
        /*ListaPessoa.add(new Pessoa("Lucas", 30, 23));
        ListaPessoa.add(new Pessoa("Roberto", 45, 90));
        ListaPessoa.add(new Pessoa("Vanessa", 20, 50));*/
        try (Scanner entrada = new Scanner(System.in)) {
            do {
                mostraMenu();
                condicao = entrada.nextInt();
                switch (condicao) {
                    case 1 -> {
                        entrada.nextLine();
                        String tempNome;
                        float tempPeso;
                        int tempIdade;

                        escreve("Digite o nome");
                        tempNome = entrada.nextLine();
                        escreve("Digite o idade");
                        tempIdade = entrada.nextInt();
                        escreve("Digite o peso");
                        tempPeso = entrada.nextFloat();
                        Pessoa pessoa = new Pessoa(tempNome, tempIdade, tempPeso);
                        escreve(pessoa.ToString());

                        entrada.nextLine();

                        ListaPessoa.add(pessoa);
                        pessoaAtual = ListaPessoa.get(ListaPessoa.size() - 1);
                        System.out.println("Cadastro bem sucedido, aperte enter para continuar...");
                        entrada.nextLine();
                    }
                    case 2 -> {
                        escreve(pessoaAtual.ToString());
                        entrada.nextLine();
                    }
                    case 3 -> {
                        escreve(pessoaAtual.toString());
                        System.out.println(pessoaAtual.ToString());
                        for (Pessoa pessoalup : ListaPessoa) {
                            escreve(pessoalup.ToString());
                        }
                    }
                    case 4 -> {
                        // grava em csv
                        GravaArquivo arquivo = new GravaArquivo();
                        for (Pessoa pessoa2 : ListaPessoa) {
                            arquivo.gravaArquivo(pessoa2.pessoaToCsv());
                        }
                        for (int i = ListaPessoa.size() - 1; i > -1; --i) {
                            ListaPessoa.remove(i);
                        }
                        System.out.println(
                                "Cadastro bem sucedido, todas as pessoas foram tranferiadas para o arquivo, aperte enter para continuar...");
                        entrada.nextLine();
                    }
                    case 5 -> {
                        // lipa lista
                        for (int i = ListaPessoa.size() - 1; i > -1; --i) {
                            ListaPessoa.remove(i);
                        }
                        System.out.println("todos as pessoas foram apagadas aperte enter para continuar...");
                        entrada.nextLine();
                    }
                    case 0 -> {
                        System.out.println("Sistema finalizado aperte enter para sair...");
                        entrada.nextLine();
                    }
                    default -> System.out.println("Opcao nao encontrada");
                }

            } while (condicao != 0);
        }
    }

    public static void mostraMenu() {
        System.out.println("1 - Cadastrar pessoa");
        System.out.println("2 - Mostrar ultima pessoa cadastrada");
        System.out.println("3 - Exibe todas as pessoas cadastradas");
        System.out.println("4 - salva arquivo em csv");
        System.out.println("5 - exclui todas as pessas cadastradas");
        System.out.println("0 - Sair do sistema");
    }

    public static void escreve(String mensagemString) {
        System.out.println(mensagemString);
    }
}