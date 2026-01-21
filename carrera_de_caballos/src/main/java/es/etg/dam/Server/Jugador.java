package es.etg.dam.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import es.etg.dam.Conexion;

public class Jugador {

    public static void main(String[] args) throws Exception {

        try (Socket s = new Socket(Conexion.HOST, Conexion.PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

            int total = 0;
            String line;
            while ((line = in.readLine()) != null) {
                if (Conexion.MSG_WIN.equals(line)) { 
                    System.out.println(Conexion.MSG_GANADOR);
                }
                if (Conexion.MSG_LOSE.equals(line)) { 
                    System.out.println(Conexion.MSG_PERDEDOR);
                }
                try { total += Integer.parseInt(line.trim()); System.out.println("+" + line + Conexion.MSG_TOTAL + total); }
                catch (Exception ignored) {}
            }
        }
    }
}