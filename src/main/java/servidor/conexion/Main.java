package servidor.conexion;


import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ConexionSocket conexionSocket = new ConexionSocket();
        conexionSocket.run();



    }

}
