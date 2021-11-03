package cliente.conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketCliente {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private final int PORT = 9000;
    private final String IP = "127.0.0.1";

    public void getConexionCliente(){
        try {
            socket =  new Socket(IP,PORT);
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
            Scanner entrada = new Scanner(System.in);
            System.out.println("ingrese un numero");
            String dato = entrada.nextLine();

            dataOut.writeUTF(dato);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
