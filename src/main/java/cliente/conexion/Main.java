package cliente.conexion;

import java.io.IOException;

/**
 * ESTA CLASE IMPLEMENTA EL METODO MAIN DEL SOCKET CLIENTE
 */
public class Main {
    public static void main(String[] args) throws IOException {
        SocketCliente socketCliente = new SocketCliente();
        socketCliente.startCajero(); // LLAMA AL METODO QUE INICIA EL CAJERO
    }
}
