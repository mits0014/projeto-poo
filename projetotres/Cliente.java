
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private static final String HOST = "localhost"; // Endereço do servidor
    private static final int PORTA = 8080; // Porta do servidor

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORTA);
             PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader leitor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao chat! Informe seu nome de usuário:");
            String usuario = console.readLine();
            escritor.println(usuario); // Enviar nome de usuário para o servidor

            // Thread para ler mensagens do servidor
            new Thread(() -> {
                try {
                    String mensagemRecebida;
                    while ((mensagemRecebida = leitor.readLine()) != null) {
                        System.out.println(mensagemRecebida);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Enviar mensagens para o servidor
            String mensagem;
            while (!(mensagem = console.readLine()).equalsIgnoreCase("Sair")) {
                escritor.println(mensagem); // Envia a mensagem para o servidor
            }

            escritor.println("Sair"); // Envia mensagem de saída para o servidor

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

