package es.etg.dam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Conexion {

    Socket socket;

    public String leer() throws IOException {

        while (true) {
            BufferedReader entrada = new BufferedReader(new
                InputStreamReader(socket.getInputStream()));
            String msg = entrada.readLine();
            return msg;
            
        }

    } 
    
}
