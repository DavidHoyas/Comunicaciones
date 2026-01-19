package es.etg.dam.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import es.etg.dam.Conexion;

public class Servidor {

    private static final String MSG_ESCUCHANDO = "Servidor escuchando en puerto ";
    private static final String MSG_REGISTRADO = "Registrado: ";
    private static final String MSG_PARTIDA = "Partida finalizada.";
    private static final String MSG_ERROR = "Error servidor: ";

    public static void main(String[] args) {
        Carrera carrera = new Carrera();
        System.out.println(MSG_ESCUCHANDO + Conexion.PORT);

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
                System.out.println(MSG_REGISTRADO + name.trim() + " (" + carrera.size() + "/" + Conexion.MAX_CABALLOS + ")");
            }

            carrera.startRace();
            System.out.println(MSG_PARTIDA);

        } catch (IOException e) {
            System.err.println(MSG_ERROR + e.getMessage());
            e.printStackTrace();
        }
    }
}