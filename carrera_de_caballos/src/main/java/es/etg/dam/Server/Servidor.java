package es.etg.dam.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import es.etg.dam.Common.Comun;

public class Servidor {

    public static void main(String[] args) {
        Carrera carrera = new Carrera();
        System.out.println(Comun.MSG_ESCUCHANDO + Comun.PORT);

        try (ServerSocket server = new ServerSocket(Comun.PORT)) {

            while (carrera.size() < Comun.MAX_CABALLOS) {
                Socket s = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                String name = in.readLine();
                if (name == null || name.trim().isEmpty()) {
                    out.println(Comun.MSG_ERR_NAME);
                    s.close();
                    continue;
                }

                carrera.register(name.trim(), s, out);
                out.println(Comun.MSG_OK);
                System.out.println(Comun.MSG_REGISTRADO + name.trim() + " (" + carrera.size() + "/" + Comun.MAX_CABALLOS + ")");
            }

            carrera.startRace();
            System.out.println(Comun.MSG_PARTIDA);

        } catch (IOException e) {
            System.err.println(Comun.MSG_ERROR + e.getMessage());
            e.printStackTrace();
        }
    }
}