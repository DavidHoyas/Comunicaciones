package es.etg.dam;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Emisor {

    private static final String MSG = "Mensaje ";
    private static final String MSG_SEND = "ENviado: ";

    public static void main(String[] args) throws IOException, InterruptedException {
        final int puerto = 9999;
        InetAddress equipo = InetAddress.getByName("localhost");

        try (DatagramSocket sSocket = new DatagramSocket()) {
            for (int i = 1; i <= 10; i++) {
                String texto = MSG + i;
                byte[] mensaje = texto.getBytes();
                DatagramPacket msg = new DatagramPacket(mensaje, mensaje.length, equipo, puerto);
                sSocket.send(msg);
                System.out.println(MSG_SEND + texto);
                sSocket.send(msg);
            }

            sSocket.close();
        }

    }

}
