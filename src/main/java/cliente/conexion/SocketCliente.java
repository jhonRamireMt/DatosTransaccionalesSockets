package cliente.conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Esta clase sera el socket cliente, contendra metodos para acceso a los datos
 * en los atributos se especificaran la direccion ip y el puerto para la conexion
 */
public class SocketCliente {
    int puerto = 8888;
    String ip = "127.0.0.1";
    DataInputStream dataIn;
    DataOutputStream dataOut;
    Socket socket;

    /* EL CONSTRUCTOR INICIALIZARA LA COMUNICACION*/
    public SocketCliente(){
        try {
             socket = new Socket(ip,puerto);
             System.out.println("conexion del socket cliente exitosa");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /* METODO PARA RETORNAR EL SOCKET CON LA CONEXION REALIZADA*/
    public Socket runSocket(){

        return this.socket;

    }

    /* METODO PARA TENER UN OBJETO DE LECTURA DE DATOS POR TECLADO*/
    public Scanner read(){
        return new Scanner(System.in);
    }

    /* METODO PARA ENVIAR DATOS AL SOCKET SERVIDOR*/
    public DataOutputStream enviarDatos() throws IOException {
        return new DataOutputStream(runSocket().getOutputStream());
    }

    /* METODO PARA RECIBIR DATOS DESDE EL SOCKET SERVIDOR*/
    public DataInputStream recibirDatos() throws IOException {
        return new DataInputStream(runSocket().getInputStream());
    }

    /* METODO QUE EJECUTA EL PROGRAMA DEL BANCO XYZ*/
    public void startCajero() throws IOException {
        boolean aux = true;
        while(aux){
            System.out.println("***********************");
            System.out.println("Bienvenido al Banco XYZ");
            System.out.println("Por favor escoja una opcion: " +
                    "1 -> Consultar Clientes" + "2 -> Consultar Sucursales");
            String dato = read().nextLine();
            enviarDatos().writeUTF(dato);
            aux = false;
        }
    }
}
