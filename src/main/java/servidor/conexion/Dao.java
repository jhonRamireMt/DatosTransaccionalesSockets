package servidor.conexion;

import servidor.entidad.Ciudad;
import servidor.entidad.Cliente;
import servidor.entidad.Cuenta;
import servidor.entidad.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase permite el acceso a datos
 * Crea los metodos de consulta a la BD MYSQL
 */
public class Dao {
    private ConexionSql conexionSql = new ConexionSql();
    private Connection con = conexionSql.getConexion();
    private PreparedStatement prep;
    private Double saldo;
    private int numeroDeCuenta;


    /* QUERY TRAER TODOS LOS DATOS DE  RELACION ENTIDADES PAIS - CUENTA - CLIENTE*/
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

    /*METODO PARA REALIZAR CONSULTA DE SALDO CON IDENTIFICACION Y CLAVE COMO PARAMETROS*/
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

    public Double obtenerSaldo(int id, int clave){
        Double saldoValidado = null;
        String sql = "select cuenta.saldo as \"saldo\" from cliente, cuenta  where numero_documento = "+id+" and clave = "+clave+"; ";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                saldoValidado = rs.getDouble("saldo");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            saldo = saldoValidado;
        return saldo;
    }

    public int obtenerNumeroCuenta(int id, int clave){
        Integer numCuenta = null;
        String sql = "select cuenta.numero_cuenta as \"numero de cuenta\" " +
                     "from cliente, cuenta " +
                     "where numero_documento = "+id+" " +
                     "and clave = "+clave+"; ";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                numCuenta = rs.getInt("numero de cuenta");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.numeroDeCuenta = numCuenta;
        return numeroDeCuenta;
    }

    /*METODO QUE REALIZA LA TRANSACCION DE DEPOSITAR EB LA CUENTA*/
    public String setDeposito(Double monto, int id,int clave) throws SQLException {
        Double nuevoSaldo = obtenerSaldo(id,clave) + monto;
        try {
            con.setAutoCommit(false); //SE DESACTIVA EL AUTO-COMMIT PARA PODER UTILIZAR ROLLBACK
            String sql1 = "INSERT INTO `cajero`.`movimiento` (`tipo_movimiento`, `monto`, `numero_cuenta`) " +
                    "VALUES ('DEPOSITO', ?, ?);";

            String sql2 = "UPDATE `cajero`.`cuenta` " +
                    "SET `saldo` = ? " +
                    "WHERE (`numero_cuenta` = ?);";

            PreparedStatement prep1 = con.prepareStatement(sql1);
            PreparedStatement prep2 = con.prepareStatement(sql2);
            prep1.setDouble(1,monto);
            prep1.setInt(2,obtenerNumeroCuenta(id,clave));

            prep2.setDouble(1,nuevoSaldo);
            prep2.setInt(2,obtenerNumeroCuenta(id,clave));
            prep1.executeUpdate();
            prep2.executeUpdate();
            con.commit();
            return  "Registro de saldo exitoso";

        } catch (SQLException e) {
            con.rollback();
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

    public boolean verificarCliente(int id, int clave){
        for(Cliente cliente:obtenerDatosClientes()){
            if(cliente.getNumero_documento() == id && cliente.getClave() == clave){
                return true;
            }
        }
        return false;
    }
}
