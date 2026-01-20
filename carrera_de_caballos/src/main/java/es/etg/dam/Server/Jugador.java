package es.etg.dam.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import es.etg.dam.Conexion;

public class Jugador {

    private static final String MSG_JUGADOR = "Uso: Jugador <nombre>";
    private static final String MSG_GANADOR = "Has ganado";
    private static final String MSG_PERDEDOR = "Has perdido";
    private static final String MSG_TOTAL = " total=";

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println(MSG_JUGADOR);
            return;
        }

        try (Socket s = new Socket(Conexion.HOST, Conexion.PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {

            int total = 0;
            String line;
            while ((line = in.readLine()) != null) {
                if (Conexion.MSG_WIN.equals(line)) { 
                    System.out.println(MSG_GANADOR);
                }
                if (Conexion.MSG_LOSE.equals(line)) { 
                    System.out.println(MSG_PERDEDOR);
                }
                try { total += Integer.parseInt(line.trim()); System.out.println("+" + line + MSG_TOTAL + total); }
                catch (Exception ignored) {}
            }
        }
    }
}