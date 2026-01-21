package es.etg.dam.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import es.etg.dam.Conexion;

public class Servidor {

    public static void main(String[] args) {
        Carrera carrera = new Carrera();
        System.out.println(Conexion.MSG_ESCUCHANDO + Conexion.PORT);

        try (ServerSocket server = new ServerSocket(Conexion.PORT)) {

            while (carrera.size() < Conexion.MAX_CABALLOS) {
                Socket s = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                String name = in.readLine();
                if (name == null || name.trim().isEmpty()) {
                    out.println(Conexion.MSG_ERR_NAME);
                    s.close();
                    continue;
                }

                carrera.register(name.trim(), s, out);
                out.println(Conexion.MSG_OK);
                System.out.println(Conexion.MSG_REGISTRADO + name.trim() + " (" + carrera.size() + "/" + Conexion.MAX_CABALLOS + ")");
            }

            carrera.startRace();
            System.out.println(Conexion.MSG_PARTIDA);

        } catch (IOException e) {
            System.err.println(Conexion.MSG_ERROR + e.getMessage());
            e.printStackTrace();
        }
    }
}