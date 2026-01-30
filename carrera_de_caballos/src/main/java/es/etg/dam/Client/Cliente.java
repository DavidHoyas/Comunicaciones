package es.etg.dam.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import es.etg.dam.Common.Comun;

public class Cliente {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println(Comun.MSG_JUGADOR);
            return;
        }
        
        String nombreCaballo = args[0];
        
        try (Socket s = new Socket(Comun.HOST, Comun.PORT);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

            // ENVIAR nombre del caballo
            out.println(nombreCaballo);
            
            // ESPERAR confirmación del servidor
            String respuesta = in.readLine();
            if (!Comun.MSG_OK.equals(respuesta)) {
                System.err.println("Error: " + respuesta);
                return;
            }
            
            System.out.println("Registrado como " + nombreCaballo + ". Esperando carrera...");
            
            // RECIBIR mensajes de la carrera
            int total = 0;
            String linea;
            while ((linea = in.readLine()) != null) {
                if (Comun.MSG_WIN.equals(linea)) {
                    System.out.println("🏆 " + Comun.MSG_GANADOR);
                    break;
                }
                if (Comun.MSG_LOSE.equals(linea)) {
                    System.out.println("💀 " + Comun.MSG_PERDEDOR);
                    break;
                }
                try {
                    int avance = Integer.parseInt(linea.trim());
                    total += avance;
                    System.out.println("🐎 +" + avance + Comun.MSG_TOTAL + total);
                } catch (NumberFormatException ignored) {}
            }
        }
        System.out.println("Conexión cerrada.");
    }
}
