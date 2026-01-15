package es.etg.dam;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    static final String MESSAGE = "Hola!";
    static final String MSG_SERVIDOR = "Servidor escuchando en ";
    static final int PUERTO = 8888;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PUERTO);
        System.out.println(MSG_SERVIDOR + PUERTO );

        while (true) {
            Socket cliente = server.accept();
            OutputStream aux = cliente.getOutputStream();
            DataOutputStream output = new DataOutputStream(aux);
            output.writeUTF(MESSAGE);

            cliente.close();

        }
    }

}
