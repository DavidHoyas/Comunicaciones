package es.etg.dam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Conexion {
    
    public static void enviar(Socket socket, String msg) throws IOException {

        OutputStream os = socket.getOutputStream();
        DataOutputStream output = new DataOutputStream(os);

        output.writeUTF(msg);
        
    }

    public static String recibir(Socket socket) throws IOException {

        InputStream is = socket.getInputStream();
        DataInputStream input = new DataInputStream(is);

        String ret = input.readUTF();

        return ret;
    }
    
}
