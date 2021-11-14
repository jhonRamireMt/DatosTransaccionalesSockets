package cliente.conexion;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SocketCliente socketCliente = new SocketCliente();
        socketCliente.startCajero();
    }
}
