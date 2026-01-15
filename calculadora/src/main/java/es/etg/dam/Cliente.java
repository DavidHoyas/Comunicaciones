package es.etg.dam;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Cliente {

    static final String HOST = "localhost";
    static final int PUERTO = 8888;

    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket cliente = new Socket(HOST, PUERTO);

        cliente.close();
        
    }
    
}
