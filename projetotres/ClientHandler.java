//package projetotres;

import java.net.Socket;
import java.util.Set;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter escritor;
    private Set<PrintWriter> listaDeEscritores;

    public ClientHandler(Socket socket, Set<PrintWriter> escritores) {
        this.socket = socket;
        listaDeEscritores = escritores;
    }

    @Override
    public void run() {
        try {
            // Envolva o InputStreamReader no BufferedReader
            BufferedReader leitor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escritor = new PrintWriter(socket.getOutputStream(), true);

            // Adicionar escritor à lista de escritores de forma sincronizada
            synchronized (listaDeEscritores) {
                listaDeEscritores.add(escritor);
            }

            String usuario = leitor.readLine();
            transmitir(usuario + " entrou no chat");

            String mensagem;
            // Correção na verificação da mensagem
            while ((mensagem = leitor.readLine()) != null) {
                if (mensagem.equals("Sair")){
                    System.out.println(usuario + "saiu do chat");
                    break;  
                } 

                transmitir(usuario + ": " + mensagem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Remover o escritor da lista quando o cliente sair
            synchronized (listaDeEscritores) {
                listaDeEscritores.remove(escritor);
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void transmitir(String mensagem) {
        // Transmitir mensagem para todos os escritores
        synchronized (listaDeEscritores) {
            for (PrintWriter escritor : listaDeEscritores) {
                escritor.println(mensagem);
            }
        }
    }
}
