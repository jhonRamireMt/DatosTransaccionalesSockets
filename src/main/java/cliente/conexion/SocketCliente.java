package cliente.conexion;

/* ESTAS SON LAS LIBRERIAS ESCENCIALES PARA LA CREACION DE UN SOCKET CLIENTE*/

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/* OBJETO PARA DETECTAR DATOS DESDE TECLADO*/
import java.util.ArrayList;
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
  public SocketCliente() {
//    try {
//
//      System.out.println("Conexion del socket cliente exitosa!"); // Impresion de verificacion
//    } catch (IOException e) {
//      System.out.println(e.getMessage()); // Impresion de exepcion
//    }
  }

  // METODO PARA RETORNAR EL SOCKET CON LA CONEXION REALIZADA EN EL CONSTRUCTOR
  public Socket runSocket() {
    return this.socket;
  }

  // METODO PARA TENER UN OBJETO DE LECTURA DE DATOS DE ENTRADA POR TECLADO
  public Scanner read() {
    return new Scanner(System.in);
  }

  // METODO PARA ENVIAR FLUJO DE  DATOS AL SOCKET SERVIDOR
  public DataOutputStream enviarDatos() throws IOException {
    return new DataOutputStream(runSocket().getOutputStream());
  }

  // METODO PARA RECIBIR FLUJO DE DATOS DESDE EL SOCKET SERVIDOR
  public DataInputStream recibirDatos() throws IOException {
    return new DataInputStream(runSocket().getInputStream());
  }

  // LOGICA DE INTERFAZ PARA EL PROGRAMA DEL BANCO XYZ
  public void startCajero() throws IOException {
    boolean aux = true;
      while(aux) {
        String entrada = "";
        String opcion = "";
        socket = new Socket(ip, puerto); // Inicializacion del objeto Socket
        System.out.println("-------------------------------------");
        System.out.println("Bienvenido al Banco XYZ. Digite:" + "\n" +
                "-> 1 para Consultar saldo" + "\n" +
                "-> 2 para Realizar un Deposito" + "\n" +
                "-> 3 para Realizar un Retiro" + "\n" +
                "-> 4 para Consultar Movimientos" + "\n" +
                "-> 5 para Salir" + "\n");

        opcion = read().nextLine();

        // EJECUCION DE CONSULTA DE SALDO
        if (opcion.equals("1")) {
          entrada = pedirDatos("1", false);
          enviarDatosAlServidor(entrada);
          System.out.println(recibirDatos().readUTF()); // Muestra respuesta del servidor
          finDeOperacion();
        }

        // EJECUCION DE CONSIGNACION O DEPOSITO
        if (opcion.equals("2")) {
          entrada = pedirDatos("2", true);
          enviarDatosAlServidor(entrada);
          System.out.println(recibirDatos().readUTF()); // Muestra respuesta del servidor
          finDeOperacion();
        }

        // EJECUCION DE CONSIGNACION O DEPOSITO
        if (opcion.equals("3")) {
          entrada = pedirDatos("3", true);
          enviarDatosAlServidor(entrada);
          System.out.println(recibirDatos().readUTF()); // Muestra respuesta del servidor
          finDeOperacion();
        }

        // EJECUCION DE CONSULTA DE MOVIMIENTOS
        if (opcion.equals("4")) {
          entrada = pedirDatos("4", false);
          enviarDatosAlServidor(entrada);
          System.out.println(recibirDatos().readUTF()); // Muestra respuesta del servidor
          finDeOperacion();
        }

        // NO SE INGRESA OPCION
        if (opcion.equals("")) {
          System.out.println("No se ingreso ninguna opcion...");
          finDeOperacion();
        }

        // SALIR
        if (opcion.equals("5")) {
          enviarDatosAlServidor(opcion);
          System.out.println(recibirDatos().readUTF());
          aux = false;
          finDeOperacion();
          socket.close();
        }
     }
  }


  // Pide las credenciales del usuario
  public String pedirDatos(String opcion, boolean conMonto) throws IOException {
    ArrayList<String> datos = new ArrayList<>();
    datos.add(opcion);

    System.out.println("Numero de Cuenta:"); // Pedir identificacion
    datos.add(read().nextLine());

    System.out.println("Clave:"); // Pedir contraseña
    datos.add(read().nextLine());

    if (conMonto) {
      System.out.println("Monto:"); // Pedir monto
      datos.add(read().nextLine());
    }

    return datos.toString();
  }

  // Enviar los datos al servidor
  public void enviarDatosAlServidor(String entrada) throws IOException {
    enviarDatos().writeUTF(entrada); // Enviar datos al servidor
  }


  // Muestra mensaje de finalizacion de operacion
  public void finDeOperacion() {
    System.out.println("");
    System.out.println("Gracias por usar nuestro servicio.");
    System.out.println("Fin de Operacion.");
    System.out.println("=========================");
  }
}

