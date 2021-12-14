package servidor.conexion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * ESTA CLASE CREA EL SOCKET SERVIDOR EL CUAL ESCUCHARA POR EL PUERTO 8888
 * SE IMPLEMENTARA LA INTERFAZ DE Runnable, LA CUAL NOS PERMITIRA
 * MANTENER EN UN HILO NUESTRO SERVICIO SOCKET SERVIDOR
 */
public class ConexionSocket implements Runnable {

    private int puerto = 8888;
    private Socket socket;
    private ServerSocket socketServidor;
    private DataOutputStream dataOutput;
    private DataInputStream dataInput;
    private Thread hiloSocket;
    private Dao dao = new Dao();

    private String entrada;
    private String opcion;
    private int cuenta;
    private int clave;
    private int monto;
    private String[] data;


    /* ESTE CONSTRUCTOR INICIALIZA LA DEPENDENCIA DE ACCESO A DATOS Y EL HILO DEL SOCKET*/
    public ConexionSocket() throws IOException {

        hiloSocket = new Thread();
        hiloSocket.start();
    }

    // METODO PARA TENER UN OBJETO DE LECTURA DE DATOS DE ENTRADA POR TECLADO
    public Scanner read() {
        return new Scanner(System.in);
    }

    /**
     * ESTE ES EL METODO QUE IMPLEMENTA EL HILO DE LA CLASE Thread
     * SE ENCARGARA DE MANTENER ABIERTO EN UN SUBPROCESO EL SERVICIO
     * DEL SOCKET SERVIDOR
     */
    @Override
    public void run() {
        System.out.println("Servicio ATM Banco XYZ Iniciado...");
        try {
            socketServidor = new ServerSocket(puerto); // CREACION DEL SOCKET SERVIDOR
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
           while(true) {

               socket = socketServidor.accept(); // SE UTILIZA UN SOCKET PARA ACEPTAR LLAMADOS AL SERVER
               dataInput = new DataInputStream(socket.getInputStream()); // OBJETO DE RECEPCION DE DATOS
               dataOutput = new DataOutputStream(socket.getOutputStream()); // OBJETO DE ENVIO DE DATOS

               entrada = dataInput.readUTF();

               String dat = entrada.replaceAll("[^a-zA-Z0-9\\\\s]", " ").trim();
               String cadena = dat.replaceAll("  ", " ");
               data = cadena.split(" ");

               opcion = data[0].trim();
               if(opcion.equals("1")){ // ESTE CASO EJECUTA UNA CONSULTA DE SALDO
                   cuenta = Integer.parseInt(data[1].trim());
                   clave = Integer.parseInt(data[2].trim());
                   if(dao.consultarSaldo(cuenta,clave) != null){
                       dataOutput.writeUTF("Detalle Consulta: "+ "\n"+dao.consultarSaldo(cuenta,clave));
                   }else{
                       dataOutput.writeUTF("Error. Identificacion o clave erroneos");
                   }
               }
               if(opcion.equals("2")){ // ESTE CASO EJECUTA UN DEPOSITO O CONSIGNACION
                   cuenta = Integer.parseInt(data[1].trim());
                   clave = Integer.parseInt(data[2].trim());
                   monto = Integer.parseInt(data[3].trim());
                   if(monto<=0){
                       dataOutput.writeUTF("Error en el monto");
                   }else{
                       int res=dao.depositar(monto,cuenta,clave);
                       if(res ==1){
                           dataOutput.writeUTF("Trasnsaccion de deposito exitosa. Por favor consulte su nuevo saldo");
                       }
                       if(res==2){
                           dataOutput.writeUTF("error usuario y contraseña");
                       }
                       if(res==3){
                           dataOutput.writeUTF("error no esperado");
                       }
                   }
               }
               if(opcion.equals("3")){ // ESTE CASO EJECUTA UNA TRANSACCION DE RETIRO
                   cuenta = Integer.parseInt(data[1].trim());
                   clave = Integer.parseInt(data[2].trim());
                   monto = Integer.parseInt(data[3].trim());
                   if(monto<=0){
                       dataOutput.writeUTF("Error en el monto");
                   }else{
                       int res=dao.retirar(monto,cuenta,clave);
                       if(res ==1){
                           dataOutput.writeUTF("Trasnsaccion de retiro exitosa. Por favor consulte su nuevo saldo.");
                       }
                       if(res==2){
                           dataOutput.writeUTF("Error usuario y/o contraseña invalidos");
                       }
                       if(res==3){
                           dataOutput.writeUTF("Saldo insuficiente");
                       }
                   }
               }
               if(opcion.equals("4")){ // ESTE CASO EJECUTA UNA CONSULTA DE MOVIMIENTO
                   cuenta = Integer.parseInt(data[1].trim());
                   clave = Integer.parseInt(data[2].trim());
                   String consultaMovimientos=dao.mostrarMovimientos(cuenta,clave);
                   dataOutput.writeUTF(consultaMovimientos);
               }
               if(opcion.equals("5")){ // ESTE CASO EJECUTA UNA CONSULTA DE MOVIMIENTO
                   dataOutput.writeUTF("RESPUESTA DESDE EL SERVER: Proceso finalizado de manera segura");
               }

               }
           } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
