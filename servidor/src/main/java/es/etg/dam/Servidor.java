package es.etg.dam;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    static final int PUERTO = 8888;
    static final String MENSAJE_SERVIDOR = "Servidor escuchando en ";

    public static void main(String[] args) throws IOException {
        
        ServerSocket server = new ServerSocket(PUERTO);

        System.out.println(MENSAJE_SERVIDOR + PUERTO);

        Socket cliente = server.accept(); 

        cliente.close();
        
    }
    
}
