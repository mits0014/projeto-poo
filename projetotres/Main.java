//package projetotres;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.util.Set;
import java.util.HashSet;

public class Main {

    private static final int PORTA = 8080; // Renomear PROTA para PORTA

    private static Set<PrintWriter> escritores = new HashSet<>();
    
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORTA)) {
            while (true) {
                new ClientHandler(server.accept(), escritores).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
