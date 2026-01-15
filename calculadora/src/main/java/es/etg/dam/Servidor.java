package es.etg.dam;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Servidor {

    Conexion conexion;
    static final int PUERTO = 8888;
    static final String MSG_SERVIDOR = "Servidor escuchando en ";

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PUERTO);
        System.out.println(MSG_SERVIDOR + PUERTO);

        while (true) {
            conexion = new Conexion(server);
            Socket cliente = server.accept();
            cliente.close();
            OutputStream aux = cliente.getOutputStream();
            DataOutputStream output = new DataOutputStream(aux);
            output.writeUTF();

            cliente.close();

        }

        }

}