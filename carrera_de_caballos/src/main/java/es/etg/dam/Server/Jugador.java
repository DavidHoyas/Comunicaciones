package es.etg.dam.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import es.etg.dam.Common.Comun;

public class Jugador {

    public static void main(String[] args) throws Exception {

        try (Socket s = new Socket(Comun.HOST, Comun.PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

            int total = 0;
            String line;
            while ((line = in.readLine()) != null) {
                if (Comun.MSG_WIN.equals(line)) { 
                    System.out.println(Comun.MSG_GANADOR);
                }
                if (Comun.MSG_LOSE.equals(line)) { 
                    System.out.println(Comun.MSG_PERDEDOR);
                }
                try { total += Integer.parseInt(line.trim()); System.out.println("+" + line + Comun.MSG_TOTAL + total); }
                catch (Exception ignored) {}
            }
        }
    }
}