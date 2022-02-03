package servidor.conexion;


import servidor.entidad.Cliente;
import servidor.entidad.Cuenta;
import servidor.entidad.Movimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ESTA CLASE ES LA CAPA DE ACCESO A DATOS, DONDE SE CREARAN LOS METODOS QUE EJECUTARAN
 * PETICIONES O CONSULTAS A LA BASE DE DATOS
 */
public class Dao {
    private ConexionSql conexionSql = new ConexionSql();
    private Connection con = conexionSql.getConexion();
    private PreparedStatement prep;
    private int saldo;
    private int numeroDeCuenta;


    /* QUERY TRAER TODOS LOS DATOS DE LA TABLA CLIENTE*/
    public List<Cliente> obtenerDatosClientes() {
        List<Cliente> listaDatosClientes = new ArrayList<>();
        String sql ="SELECT * FROM cliente;";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                listaDatosClientes.add(new Cliente(
                        rs.getInt("numero_documento"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_creado"),
                        rs.getInt("numero_telefono"),
                        rs.getInt("clave")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaDatosClientes;
    }

    /* QUERY TRAER TODOS LOS DATOS DE UN CLIENTE ESPECIFICO*/
    public List<Cliente> obtenerCliente(int numCuenta, int clave) {
        List<Cliente> listaDatosClientes = new ArrayList<>();
        String sql ="select * from cliente, cuenta " +
                "where numero_cuenta ="+numCuenta+" and cliente.clave ="+clave+";";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                listaDatosClientes.add(new Cliente(
                        rs.getInt("numero_documento"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_creado"),
                        rs.getInt("numero_telefono"),
                        rs.getInt("clave")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaDatosClientes;
    }

    /* QUERY TRAER TODOS LOS MOVIMIENTOS DEL CLIENTE VALIDADOS POR NUMERO DE CUENTA*/
    public List<Movimiento> obtenerMovimientos(int cuentaNumero, int clave) {
        List<Movimiento> listaDatosClientes = new ArrayList<>();
        String sql ="select movimiento.tipo_movimiento as \"TIPO DE MOVIMIENTO\",\n" +
                " movimiento.monto as \"MONTO\",\n" +
                " movimiento.fecha_creado as \"FECHA\",\n" +
                " cuenta.numero_cuenta as \"CUENTA NUMERO\" ,\n" +
                " cuenta.saldo as \"SALDO DISPONIBLE\" ,\n" +
                " cliente.nombres as \"NOMBRES CLIENTE\",\n" +
                " cliente.apellidos as \"APELLIDOS CLIENTE\",\n" +
                " cliente.numero_documento as \"No IDENTIFICACION\"\n" +
                "from movimiento, cuenta, cliente" +
                " where cuenta.numero_cuenta = "+cuentaNumero+" and cliente.clave = "+clave+"; ";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                listaDatosClientes.add(new Movimiento(rs.getString("TIPO DE MOVIMIENTO"),
                        rs.getInt("MONTO"),
                        rs.getDate("FECHA"),
                        new Cuenta(rs.getInt("CUENTA NUMERO"),
                                rs.getInt("SALDO DISPONIBLE")),
                                new Cliente(rs.getString("NOMBRES CLIENTE"),
                                        rs.getString("APELLIDOS CLIENTE"),
                                        rs.getInt("No IDENTIFICACION"))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaDatosClientes;
    }

    /*METODO PARA REALIZAR UNA CONSULTA DE SALDO MEDIANTE USO DE NUMERO DE CUENTA Y CLAVE */
    public List<Cliente> consultaDeSaldo(int cuenta_numero, int clave){
        List<Cliente> listaSaldo= new ArrayList<>();
        int num_cuenta = cuenta_numero;
        int password = clave;
        String sql = "select cliente.numero_documento as cedula, cliente.nombres, cliente.apellidos,\n" +
                "cuenta.numero_cuenta as \"numero de cuenta\",\n" +
                "cuenta.saldo as \"saldo disponible\",\n" +
                "cuenta.tipo_cuenta as \"tipo de cuenta\"\n" +
                "from cliente, cuenta \n" +
                "where numero_cuenta = "+num_cuenta+" and clave = "+password+";";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs =prep.executeQuery();
            if(rs.equals("")){
                System.out.println("Error de identificacion o clave");
            }else{
                while(rs.next()){
                    listaSaldo.add(new Cliente(rs.getInt("Cedula"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            new Cuenta(rs.getInt("numero de cuenta"),
                                    rs.getInt("saldo disponible"),
                                    rs.getString("tipo de cuenta"))));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaSaldo;
    }

    /*ESTE METODO OBTIENE UNICAMENTE EL VALOR DEL SALDO, SIENDO VERIFICADO QUE CORRESPONDA AL USUARIO CON
    * NUMERO DE IDENTIFICACION Y CLAVE DADAS POR PARAMETRO*/
    public int obtenerSaldo(int id, int clave){
        int saldoValidado = 0;
        String sql = "select cuenta.saldo as \"saldo\" " +
                "from cliente, cuenta  " +
                "where numero_cuenta = "+id+" and clave = "+clave+"; ";
        try {
            prep = con.prepareStatement(sql); // PREPARACION DEL QUERY
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                saldoValidado = rs.getInt("saldo"); // OBTENCION DEL SALDO
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            saldo = saldoValidado;
        return saldo;
    }

    /*ESTE METODO OBTIENE EL NUMERO DE CUENTA A PARTIR DE LA VALIDACION DEL NUMERO DE IDENTIFICACION Y CLAVE*/
    public int obtenerNumeroCuenta(int id, int clave){
        Integer numCuenta = null;
        String sql = "select cuenta.numero_cuenta as \"numero de cuenta\" " +
                     "from cliente, cuenta " +
                     "where numero_documento = "+id+" " +
                     "and clave = "+clave+"; ";
        try {
            prep = con.prepareStatement(sql); // PREPARACION DE LA CONSULTA
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                numCuenta = rs.getInt("numero de cuenta"); // OPTENCION DEL NUMERO DE CUENTA
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.numeroDeCuenta = numCuenta;
        return numeroDeCuenta;
    }

    /*METODO QUE REALIZA LA TRANSACCION DE DEPOSITAR O CONSIGNAR EN LA CUENTA
    * ES NECESARIO PASAR POR PARAMETROS EL MONTO A DEPOSITAR, EL NUMERO DE IDENTIFICACION Y CLAVE*/
    public int depositar(int monto, int numCuenta,int clave) throws SQLException {
        System.out.println("alcanza a entrar");
        try {
            if(!obtenerCliente(numCuenta,clave).isEmpty()){
                if(monto<=0){
                    return 4;
                }else{
                    int saldoActual = obtenerSaldo(numCuenta,clave); // OBTENCION DEL SALDO ACTUAL
                    con.setAutoCommit(false); // AUTO COMMIT OFF !!
                    String sql1 = "CALL sp_consignacion(?,?,?);"; // QUERY PROCEDURE
                    PreparedStatement prep1 = con.prepareStatement(sql1); // QUERY PREPARADO
                    prep1.setInt(1,monto); //SE ENVIA EL MONTO A DEPOSITAR
                    prep1.setInt(2,saldoActual); // SE ENVIA EL SALDO ACTUAL CONSULTADO
                    prep1.setInt(3,numCuenta); // SE ENVIA NUMERO DE CUENTA
                    prep1.executeUpdate(); // EJECUCION QUERY INSERT
                    con.commit(); // COMMIT OK !!
                    System.out.println("1");
                    return  1;
                }
            }
            return  2;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("3");
            return 3;
        }
    }

    /* ESTE METODO REALIZA UN RETIRO EN LA BASE DE DATOS
    * SE DESACTIVA EL AUTO COMMIT*/
    public int retirar(int monto, int numCuenta,int clave) throws SQLException {
        System.out.println("alcanza a entrar");
        try {
            if(!obtenerCliente(numCuenta,clave).isEmpty()){
                int saldoActual = obtenerSaldo(numCuenta,clave); // OBTENCION DEL SALDO ACTUAL
                if(saldoActual<=1){ //valida que el saldo actual no sea negativo
                    return 5; // Devuelve mediante un codigo un mensaje al cliente
                }else{
                    if(monto>saldoActual){ // validacion del monto
                        return 3; // Devuelve mediante un codigo un mensaje al cliente
                    }else{
                        if(monto<=1){
                            return 6; // Devuelve mediante un codigo un mensaje al cliente
                        }else{
                            con.setAutoCommit(false); // AUTO COMMIT OFF !!
                            String sql1 = "CALL sp_retiro(?,?,?);"; // QUERY PROCEDURE
                            PreparedStatement prep1 = con.prepareStatement(sql1); // QUERY PREPARADO
                            prep1.setInt(1,monto); //SE ENVIA EL MONTO A DEPOSITAR
                            prep1.setInt(2,saldoActual); // SE ENVIA EL SALDO ACTUAL CONSULTADO
                            prep1.setInt(3,numCuenta); // SE ENVIA NUMERO DE CUENTA
                            prep1.executeUpdate(); // EJECUCION QUERY INSERT
                            con.commit(); // COMMIT OK !!
                            System.out.println("1");
                            return  1; // Devuelve mediante un codigo un mensaje al cliente
                        }
                    }
                }
            }
            return  2; // Devuelve mediante un codigo un mensaje al cliente
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("3");
            return 4; // Devuelve mediante un codigo un mensaje al cliente
        }
    }


    /* ESTE METODO MUESTRA TODOS LOS DATOS DE LOS CLIENTES CREADOS*/
    public void mostarDatosClientes(){
        for(Cliente cliente: obtenerDatosClientes()){
            cliente.mostrarClientes();
        }
    }

    /* ESTE METODO TRAE LA CONSULTA DE SALDO MEDIANTE IDENTIFICACION Y CLAVE*/
    public String consultarSaldo(int id, int clave){
        for(Cliente cliente: consultaDeSaldo(id,clave)){
            return cliente.mostrarConsultaDeSaldo();
        }
        return null;
    }

    /*METODO PARA MOSTRAR LOS DATOS DE LOS MOVIMIENTOS REALIZADOS EN UNA CUENTA*/
    public String mostrarMovimientos(int numCuenta, int clave){
        ArrayList<String> data = new ArrayList<>();
        for(Movimiento movimiento: obtenerMovimientos(numCuenta,clave)){
             data.add(movimiento.mostrarMovimientos());
        }
        return data.toString();
    }
}
