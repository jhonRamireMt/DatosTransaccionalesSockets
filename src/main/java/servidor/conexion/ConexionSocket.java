package servidor.conexion;

import servidor.entidad.Cliente;
import servidor.entidad.Cuenta;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ConexionSocket implements Runnable {

    private int puerto = 8888;
    private Socket socket;
    private ServerSocket socketServidor;
    private DataOutputStream dataOutput;
    private DataInputStream dataInput;
    private Thread hiloSocket;
    private Dao dao;


    public ConexionSocket() throws IOException {
        dao = new Dao();
        hiloSocket = new Thread();
        hiloSocket.start();
    }


    @Override
    public void run() {
        System.out.println("Servicio ATM Banco XYZ Iniciado...");
        try {
            socketServidor = new ServerSocket(puerto);
           while(true){
                socket = socketServidor.accept();
                dataInput = new DataInputStream(socket.getInputStream());
                dataOutput = new DataOutputStream(socket.getOutputStream());

                dataOutput.writeUTF("Bienvenido al Bnaco XYZ. Digite:"+"\n"+
                        "-> 1 para Consultar saldo" +"\n"+
                        "-> 2 para Realizar un Deposito"+"\n"+
                        "-> 3 para Realizar un Retiro"+"\n");
                String operacion = dataInput.readUTF();

                switch (operacion){

                    case "1":

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
                    case "2":

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
                }
           }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
