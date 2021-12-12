package cliente.conexion;

/* ESTAS SON LAS LIBRERIAS ESCENCIALES PARA LA CREACION DE UN SOCKET CLIENTE*/
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/* OBJETO PARA DETECTAR DATOS DESDE TECLADO*/
import java.util.Scanner;

/**
 * Esta clase sera el socket cliente,
 * En los atributos de la clase se especificaran:
 * puerto de conexion, direccion ip de conexion
 * Objeto de entrada de datos, objeto de salida de datos
 * Objeto Socket el cual creara la conexion como un socket cliente
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
             socket = new Socket(ip,puerto); // Inicializacion del objeto Socket
             System.out.println("conexion del socket cliente exitosa"); // impresion de verificacion
        } catch (IOException e) {
            System.out.println(e.getMessage()); // Impresion de exepcion
        }
    }

    /* METODO PARA RETORNAR EL SOCKET CON LA CONEXION REALIZADA EN EL CONSTRUCTOR*/
    public Socket runSocket(){
        return this.socket;
    }

    /* METODO PARA TENER UN OBJETO DE LECTURA DE DATOS DE ENTRADA POR TECLADO*/
    public Scanner read(){
        return new Scanner(System.in);
    }

    /* METODO PARA ENVIAR FLUJO DE  DATOS AL SOCKET SERVIDOR*/
    public DataOutputStream enviarDatos() throws IOException {
        return new DataOutputStream(runSocket().getOutputStream());
    }

    /* METODO PARA RECIBIR FLUJO DE DATOS DESDE EL SOCKET SERVIDOR*/
    public DataInputStream recibirDatos() throws IOException {
        return new DataInputStream(runSocket().getInputStream());
    }

    /* LOGICA DE INTERFAZ PARA EL PROGRAMA DEL BANCO XYZ */
    public void startCajero() throws IOException {
        boolean aux = true;
        while(aux){
            System.out.println(recibirDatos().readUTF());
            String dato = read().nextLine();
            enviarDatos().writeUTF(dato);

            if(dato.equals("1")){ // EJECUCION DE CONSULTA DE SALDO

                System.out.println(recibirDatos().readUTF()); // MUESTRA LOS DATOS RECIBIDOS EN CONSOLA
                String id = read().nextLine();
                enviarDatos().writeUTF(id); //ENVIO DE FLUJO DE DATOS PARA VALIDAR IDENTIFICACION
                System.out.println(recibirDatos().readUTF()); // MUESTRA LOS DATOS RECIBIDOS EN CONSOLA
                String pass = read().nextLine();
                enviarDatos().writeUTF(pass); //ENVIO DE FLUJO DE DATOS PARA VALIDAR CLAVE

                System.out.println(recibirDatos().readUTF()); // MUESTRA RESPUESTA DEL SERVIDOR
                System.out.println("");
                System.out.println("Gracias por usar nuestro servicio");
                System.out.println("Fin de Operacion");
                aux = false;

            }if(dato.equals("2")){ // EJECUCION DE CONSIGNACION O DEPOSITO

                System.out.println(recibirDatos().readUTF()); // MUESTRA LOS DATOS RECIBIDOS EN CONSOLA
                String id = read().nextLine();
                enviarDatos().writeUTF(id); //ENVIO DE FLUJO DE DATOS PARA VALIDAR IDENTIFICACION
                System.out.println(recibirDatos().readUTF()); // MUESTRA LOS DATOS RECIBIDOS EN CONSOLA
                String pass = read().nextLine();
                enviarDatos().writeUTF(pass); //ENVIO DE FLUJO DE DATOS PARA VALIDAR CLAVE

                System.out.println(recibirDatos().readUTF()); // MUESTRA RESPUESTA DEL SERVIDOR
                String monto = read().nextLine();
                enviarDatos().writeUTF(monto); //ENVIO DE FLUJO DE DATOS PARA DEPOSITAR MONTO
                System.out.println(recibirDatos().readUTF()); // MUESTRA RESPUESTA DEL SERVIDOR
                System.out.println("");
                System.out.println("Gracias por usar nuestro servicio");
                System.out.println("Fin de Operacion");
                aux = false;

            }if(dato.equals("4")){ // EJECUCION DE CONSULTA DE SALDO

                System.out.println(recibirDatos().readUTF()); // MUESTRA LOS DATOS RECIBIDOS EN CONSOLA
                String entrada = read().nextLine();
                enviarDatos().writeUTF(entrada); //ENVIO DE FLUJO DE DATOS PARA VALIDAR IDENTIFICACION
                System.out.println(recibirDatos().readUTF()); // MUESTRA LOS DATOS RECIBIDOS EN CONSOLA

                System.out.println(recibirDatos().readUTF()); // MUESTRA RESPUESTA DEL SERVIDOR
                System.out.println("");
                System.out.println("Gracias por usar nuestro servicio");
                System.out.println("Fin de Operacion");
                aux = false;

            }
        }
    }
}
