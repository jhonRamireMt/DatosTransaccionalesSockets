package servidor.conexion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionSocket {

    private Socket socket;
    private ServerSocket serverSocket;
    private DataOutputStream dataOut;
    private DataInputStream dataIn;


    private int puerto = 8888;

    public void getConexionSocket() {

        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("se esta ejecutando el servidor..");

            socket = new Socket();
            socket = serverSocket.accept();

            dataOut = new DataOutputStream(socket.getOutputStream());
            dataIn = new DataInputStream(socket.getInputStream());


            if (dataIn.readUTF().equals("2")) {
                Dao dao = new Dao();
                dao.mostrarRelacionSucursalClienteCuenta();
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}