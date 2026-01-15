package es.etg.dam.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import es.etg.dam.Conexion;

public class Cliente extends Conexion {

    private static final String MSG_PRINT = "Modo: %s\nPrecio: %s";

    private static final String SPLITER = ";";

    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    
    private static final int POS_MODE = 0;
    private static final int POS_PRICE = 1;

    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Socket cliente = new Socket(HOST, PORT);
        Scanner sc = new Scanner(System.in);

        enviar(cliente,args[0]);

        String[] in = recibir(cliente).split(SPLITER);

        System.out.println(String.format(MSG_PRINT, in[POS_MODE], in[POS_PRICE]));

        sc.close();
        cliente.close();
    }
    
}
