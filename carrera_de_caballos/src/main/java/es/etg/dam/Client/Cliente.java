package es.etg.dam.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import es.etg.dam.Conexion;

public class Cliente {
    public static void main(String[] args) throws IOException {
        
        try (Socket s = new Socket(Conexion.HOST, Conexion.PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {

            out.println(args[0]);
            if (!Conexion.MSG_OK.equals(in.readLine()));
        }
    }
    
}
