package servidor.conexion;


import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // CORRER EL SOCKET SERVER
        ConexionSocket conexionSocket = new ConexionSocket();
        conexionSocket.run();

//        verificar numero de cuenta y saldo
//        Dao dao = new Dao();
//        System.out.println(dao.obtenerSaldo(1019123456,2255));
//        System.out.println(dao.obtenerNumeroCuenta(1019123456,2255));

        //validar existencia del cliente
//        Dao dao = new Dao();
//        System.out.println(dao.verificarCliente(1019008139,2233));
//        dao.mostarDatosClientes();



    }

}
