package es.etg.dam.Server;

import java.net.Socket;

import es.etg.dam.Conexion;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Printer extends Conexion implements Runnable {

    private static final int POS_MODE = 0;
    private static final int POS_PAGS = 1;

    private Ink ink;
    private Socket cliente;

    @Override
    public void run() {
        try {

            String[] data = recibir(cliente).split(Servidor.SPLITER);
            String respuesta = ink.print(data[POS_MODE], Integer.parseInt(data[POS_PAGS]));

            enviar(cliente, respuesta);
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
