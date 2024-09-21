
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PORTA = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORTA);
             PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader leitor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao chat! Informe seu nome de usuário:");
            String usuario = console.readLine();
            escritor.println(usuario);
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

            
            String mensagem;
            while (!(mensagem = console.readLine()).equalsIgnoreCase("Sair")) {
                escritor.println(mensagem); 
            }

            escritor.println("Sair");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

