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


    /* QUERY TRAER TODOS LOS DATOS DE  RELACION ENTIDADES PAIS - CUENTA - CLIENTE*/
    public List<Pais> getDatosClientes() {
        List<Pais> listaDatosClientes = new ArrayList<>();
        String sql ="select * from pais \n" +
                    "inner join cuenta \n" +
                    "on cuenta.cliente_numero_documento = cliente_numero_documento \n" +
                    "inner join cliente\n" +
                    "on cuenta.cliente_numero_documento = cliente_numero_documento;";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                listaDatosClientes.add(
                    new Pais(
                        rs.getString("nombre_pais"),
                            new Ciudad(
                                rs.getString("ciudad_nombre_ciudad")),
                                    new Cuenta(
                                        rs.getInt("numero_cuenta"),
                                        rs.getDouble("saldo"),
                                        rs.getDate("fecha_creado"),
                                        rs.getString("tipo_cuenta")),
                                            new Cliente(
                                                rs.getInt("numero_documento"),
                                                rs.getString("nombres"),
                                                rs.getString("apellidos"),
                                                rs.getDate("fecha_creado"),
                                                rs.getInt("numero_telefono"),
                                                rs.getInt("clave"))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaDatosClientes;
    }

    /*METODO PARA REALIZAR CONSULTA DE SALDO CON IDENTIFICACION Y CLAVE COMO PARAMETROS*/
    public List<Cliente> getSaldo(int identificacion, int clave){
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

    public String setDeposito(Cuenta cuenta , int monto){

        try {
            con.setAutoCommit(false);

            ResultSet rsValidator = prep.executeQuery();
            if(rsValidator.equals("")){
                return "Datos no encontrados";
            }else{
                String sqlMov = "INSERT INTO `cajero`.`movimiento` (`tipo_movimiento`, `monto`, `numero_cuenta`)" +
                        " VALUES ('DEPOSITO', ?, ?);";
                String sqlCuenta = "UPDATE `cajero`.`cuenta` SET `saldo` = ? WHERE (`numero_cuenta` = ?);";
                prep = con.prepareStatement(sqlMov);
                prep.setInt(1,monto);
                prep.setInt(2,cuenta.getNumero_cuenta());
                prep = con.prepareStatement(sqlCuenta);
                prep.setInt(1,monto);
                prep.setInt(2,cuenta.getNumero_cuenta());
                prep.executeUpdate();
                con.commit();
                System.out.println("Registro exitoso");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }


    public Cliente getNumCuenta(int id, int clave){
       return getSaldo(id, clave).get(3);
    }

    /* ESTE METODO MUESTRA TODOS LOS DATOS DE LOS CLIENTES CREADOS*/
    public void mostarDatosClientes(){
        for(Pais pais: getDatosClientes()){
            pais.mostrarDatosClientes();
        }
    }

    /* ESTE METODO TRAE LA CONSULTA DE SALDO MEDIANTE IDENTIFICACION Y CLAVE*/
    public String consultarSaldo(int id, int clave){
        for(Cliente cliente: getSaldo(id,clave)){
            return cliente.mostrarConsultaDeSaldo();
        }
        return null;
    }

}
