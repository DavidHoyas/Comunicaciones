package es.etg.dam.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static final int PUERTO = 8888;

    public static final String SPLITER = ";";
    private static final String MSG_ESCUCHANDO = "Escuchando...";

    public static void main(String[] args) throws IOException {
        Ink ink = new Ink();
        try (ServerSocket server = new ServerSocket(PUERTO)) {
            System.out.println(MSG_ESCUCHANDO);
            while (true) {
                Socket cliente = server.accept();
                Thread hilo = new Thread(new Printer(ink, cliente));
                hilo.start();
            }
        }
    }
    
}
