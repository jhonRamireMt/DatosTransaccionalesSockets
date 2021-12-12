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
    private Double saldo;
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

    /* QUERY TRAER TODOS LOS MOVIMIENTOS DEL CLIENTE VALIDADOS POR NUMERO DE CUENTA*/
    public List<Movimiento> obtenerMovimientos(int cuentaNumero) {
        List<Movimiento> listaDatosClientes = new ArrayList<>();
        String sql ="select * from movimiento where movimiento.numero_cuenta = "+cuentaNumero+";";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                listaDatosClientes.add(new Movimiento(
                        rs.getInt("id_movimiento"),
                        rs.getString("tipo_movimiento"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha_creado"),
                        new Cuenta(rs.getInt("numero_cuenta"))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaDatosClientes;
    }

    /*METODO PARA REALIZAR CONSULTA DE SALDO CON IDENTIFICACION Y CLAVE COMO PARAMETROS
    * ESTE METODO RETORNA UN ARRAY LIST QUE UTILIZAREMOS PARA MAPEAR EN LAS ENTIDADES,
    * CON EL FIN DE TRAER LOS DATOS DE UNA CONSULTA DE SALDO */
    public List<Cliente> consultaDeSaldo(int identificacion, int clave){
        List<Cliente> listaSaldo= new ArrayList<>();
        int id = identificacion;
        int password = clave;
        String sql = "select cliente.numero_documento as cedula, cliente.nombres, cliente.apellidos,\n" +
                "cuenta.numero_cuenta as \"numero de cuenta\",\n" +
                "cuenta.saldo as \"saldo disponible\",\n" +
                "cuenta.tipo_cuenta as \"tipo de cuenta\"\n" +
                "from cliente, cuenta \n" +
                "where numero_documento = "+id+" and clave = "+password+";";

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
                                    rs.getDouble("saldo disponible"),
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
    public Double obtenerSaldo(int id, int clave){
        Double saldoValidado = null;
        String sql = "select cuenta.saldo as \"saldo\" " +
                "from cliente, cuenta  " +
                "where numero_documento = "+id+" and clave = "+clave+"; ";
        try {
            prep = con.prepareStatement(sql); // PREPARACION DEL QUERY
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                saldoValidado = rs.getDouble("saldo"); // OBTENCION DEL SALDO
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
    public String setDeposito(Double monto, int id,int clave) throws SQLException {

        //SE REALIZA LA OPERACION QUE SUMA EL MONTO AL SALDO ACTUAL
        Double nuevoSaldo = obtenerSaldo(id,clave) + monto;
        try {
            con.setAutoCommit(false); //SE DESACTIVA EL AUTO-COMMIT PARA PODER UTILIZAR ROLLBACK

            String sql1 = "INSERT INTO `cajero`.`movimiento` " +
                          "(`tipo_movimiento`, `monto`, `numero_cuenta`) " +
                          "VALUES ('DEPOSITO', ?, ?);";
            String sql2 = "UPDATE `cajero`.`cuenta` " +
                          "SET `saldo` = ? " +
                          "WHERE (`numero_cuenta` = ?);";

            PreparedStatement prep1 = con.prepareStatement(sql1); // QUERY DE TIPO INSERT EN LA TABLA MOVIMIENTO
            PreparedStatement prep2 = con.prepareStatement(sql2); // QUERY DE TIPO UPDATE EN LA TABLA CUENTA

            prep1.setDouble(1,monto); //PARAMETRO MONTO

            // SE OBTIENE NUMERO DE CUENTA Y SE PASA POR PARAMETRO
            prep1.setInt(2,obtenerNumeroCuenta(id,clave));

            prep2.setDouble(1,nuevoSaldo); //SE PASA EL NUEVO SALDO EL CUAL ACTUALIZA EN LA TABLA CUENTA

            // SE OBTIENE NUMERO DE CUENTA Y SE PASA POR PARAMETRO
            prep2.setInt(2,obtenerNumeroCuenta(id,clave));

            prep1.executeUpdate(); // EJECUCION QUERY INSERT
            prep2.executeUpdate(); // EJECUCION QUERY UPDATE

            con.commit(); // SI NO HAY ALGUN ERROR EN LOS QUERYS SE REALIZA EL COMMIT
            return  "Registro de saldo exitoso";
        } catch (SQLException e) {
            con.rollback(); // SI HAY ALGUN ERROR EN LOS QUERYS SE REALIZA UN ROLLBACK
            e.getMessage();
        }
        return null;
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

    /*METODO QUE VALIDA LA EXISTENCIA DEL CLIENTE MEDIANTE IDENTIFICACION Y CLAVE*/
    public boolean verificarCliente(int id, int clave){
        for(Cliente cliente:obtenerDatosClientes()){
            if(cliente.getNumero_documento() == id && cliente.getClave() == clave){
                return true;
            }
        }
        return false;
    }

    /*METODO PARA MOSTRAR LOS DATOS DE LOS MOVIMIENTOS REALIZADOS EN UNA CUENTA*/
    public String mostrarMovimientos(int numCuenta){
        for(Movimiento movimiento: obtenerMovimientos(numCuenta)){
            return movimiento.mostrarMovimientos();
        }
        return null;
    }
}
