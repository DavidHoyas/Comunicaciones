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
        
        try (Socket s = new Socket(Comun.HOST, Comun.PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {

            out.println(args[0]);
            if (!Comun.MSG_OK.equals(in.readLine()));
        }
    }
    
}
