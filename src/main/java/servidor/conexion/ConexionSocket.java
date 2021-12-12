package servidor.conexion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

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
    private Dao dao;

    /* ESTE CONSTRUCTOR INICIALIZA LA DEPENDENCIA DE ACCESO A DATOS Y EL HILO DEL SOCKET*/
    public ConexionSocket() throws IOException {
        dao = new Dao();
        hiloSocket = new Thread();
        hiloSocket.start();
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
           while(true){
                socket = socketServidor.accept(); // CSE UTILIZA UN SOCKET PARA ACEPTAR LLAMADOS AL SERVER
                dataInput = new DataInputStream(socket.getInputStream()); // OBJETO DE RECEPCION DE DATOS
                dataOutput = new DataOutputStream(socket.getOutputStream()); // OBJETO DE ENVIO DE DATOS

               /* ESTA LOGICA DE INTERFAZ SE IMPLEMENTA EN EL SERVIDOR PERO SE OBSERVARA EN EL SOCKET CLIENTE*/
                dataOutput.writeUTF("Bienvenido al Banco XYZ. Digite:"+"\n"+
                        "-> 1 para Consultar saldo" +"\n"+
                        "-> 2 para Realizar un Deposito"+"\n"+
                        "-> 3 para Realizar un Deposito"+"\n"+
                        "-> 4 para Consultar Movimientos"+"\n");
                String operacion = dataInput.readUTF();

                switch (operacion){

                    case "1": // ESTE CASO EJECUTA UNA CONSULTA DE SALDO

                        dataOutput.writeUTF("Ingrese su Numero identificacion");
                        String dato1 = dataInput.readUTF();
                        dataOutput.writeUTF("Ingrese su clave");
                        String dato2 = dataInput.readUTF();

                        if(dato1.equals("") || dato2.equals("")){
                            dataOutput.writeUTF("Error. Identificacion y clave necesarios para continuar.");
                        }else{
                            int id = Integer.parseInt(dato1);
                            int pass = Integer.parseInt(dato2);
                            if(dao.consultarSaldo(id,pass) != null){
                                dataOutput.writeUTF("Detalle Consulta: "+ "\n"+dao.consultarSaldo(id,pass));
                            }else{
                                dataOutput.writeUTF("Error. Identificacion o clave erroneos");
                            }
                        }
                    case "2": // ESTE CASO EJECUTA UN DEPOSITO O CONSIGNACION

                        dataOutput.writeUTF("Ingrese su Numero identificacion");
                        String idDep = dataInput.readUTF();
                        dataOutput.writeUTF("Ingrese su clave");
                        String passDep = dataInput.readUTF();

                        if(idDep.equals("") || passDep.equals("")){
                            dataOutput.writeUTF("Error. Identificacion y clave necesarios para continuar.");
                        }else{
                            int id = Integer.parseInt(idDep);
                            int pass = Integer.parseInt(passDep);
                            if(!dao.verificarCliente(id,pass)){
                                dataOutput.writeUTF("Error. Identificacion no exitosa.");
                            }else{
                                dataOutput.writeUTF("Ingresar monto a Depositar");
                                String montoEntrante = dataInput.readUTF();
                                Double monto = Double.parseDouble(montoEntrante);
                                dao.setDeposito(monto,id,pass);
                                dataOutput.writeUTF("Operacion Exitosa.");
                            }
                        }
                    case "4": // ESTE CASO EJECUTA UNA CONSULTA DE MOVIMIENTO

                        dataOutput.writeUTF("Ingrese su Numero de cuenta");
                        String numCue = dataInput.readUTF();

                        if(numCue.equals("")){
                            dataOutput.writeUTF("Error. Identificacion y clave necesarios para continuar.");
                        }else{
                            int numCuenta = Integer.parseInt(numCue);

                            if(dao.obtenerMovimientos(numCuenta).isEmpty()){
                                dataOutput.writeUTF("Error. Cuenta no existe o no tiene movimientos.");
                            }else{
                                dataOutput.writeUTF(dao.mostrarMovimientos(numCuenta));
                                dataOutput.writeUTF("Operacion Exitosa.");
                            }
                        }
                }
           }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
