package es.etg.dam;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    static final String HOST = "localhost";
    static final int PUERTO = 8888;
    static final String MSG_CLIENTE = "Hola desde el cliente";

    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket cliente = new Socket(HOST, PUERTO);
        InputStream aux = cliente.getInputStream();
        DataInputStream input = new DataInputStream(aux);
        String msg = input.readUTF();
        System.out.println(msg);

        System.out.println(MSG_CLIENTE);

        cliente.close();
    }
}