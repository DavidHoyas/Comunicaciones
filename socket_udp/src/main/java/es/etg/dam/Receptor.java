package es.etg.dam;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receptor {

    private static final String MSG_ESCUCHANDO = "Receptor escuchando en puerto ";
    private static final String MSG_RECIBIDO = "Recibido (%d/%d): %s  desde %s:%d%n";
    private static final String MSG_RECEPTOR = "Receptor: ya recibí ";
    private static final String MSG_CERRANDO = " mensajes, cerrando.";

    public static void main(String[] args) throws IOException {

        final int puerto = 9999;
        final int MENSAJES = 10;
        byte[] cadena = new byte[1024];
        
        try (DatagramSocket sSocket = new DatagramSocket(puerto)) {
            System.out.println(MSG_ESCUCHANDO + puerto);
            for (int i = 1; i < MENSAJES; i++) {
                DatagramPacket msg = new DatagramPacket(cadena, cadena.length);
                sSocket.receive(msg);
                String datos = new String(msg.getData(), 0, msg.getLength());
                System.out.printf(MSG_RECIBIDO,
                                  i, MENSAJES, datos, msg.getAddress(), msg.getPort());
            }

            sSocket.close();
        }
        System.out.println(MSG_RECEPTOR + MENSAJES + MSG_CERRANDO);

    }
}