package servidor.conexion;

import servidor.entidad.Cliente;
import servidor.entidad.Cuenta;
import servidor.entidad.Sucursal;

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

    /* QUERY DATOS TABLA CLIENTE*/
    public List<Cliente> obtenerTablaCliente(){
        List<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente;";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                lista.add(
                        new Cliente(
                                rs.getInt("cedula"),
                                rs.getString("nombre"),
                                rs.getInt("sucursal_idSucursal")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    /* QUERY OBTENER DATOS ENTIDAD SUCURSAL*/
    public List<Sucursal> obtenerTablaSucursal(){
        List<Sucursal> lista = new ArrayList<>();
        String sql = "select * from sucursal;";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                lista.add(
                        new Sucursal(
                                rs.getInt("idSucursal"),
                                rs.getString("pais"),
                                rs.getString("ciudad")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    /* QUERY RELACION ENTIDADES SUCURSAL - CLIENTE*/
    public List<Sucursal> getRelacionSucursalCliente() {
        List<Sucursal> listaSucursalCliente = new ArrayList<>();
        String sql ="select * from sucursal inner join cliente on cliente.sucursal_idSucursal = sucursal.idSucursal;";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                listaSucursalCliente.add(
                        new Sucursal(
                            rs.getInt("idSucursal"),
                            rs.getString("pais"),
                            rs.getString("ciudad"),
                                new Cliente(
                                    rs.getInt("cedula"),
                                    rs.getString("nombre"),
                                    rs.getInt("sucursal_idSucursal"))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaSucursalCliente;
    }

    /* QUERY RELACION ENTIDADES SUCURSAL - CLIENTE - CUENTA*/
    public List<Sucursal> getRelacionSucursalClienteCuenta() {
        List<Sucursal> listaSucursalCliente = new ArrayList<>();
        String sql ="select * from sucursal inner join cliente on cliente.sucursal_idSucursal = sucursal.idSucursal" +
                    " inner join cuenta on cuenta.cliente_cedula = cliente.cedula;";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                listaSucursalCliente.add(
                        new Sucursal(
                            rs.getInt("idSucursal"),
                            rs.getString("pais"),
                            rs.getString("ciudad"),
                                new Cliente(
                                    rs.getInt("cedula"),
                                    rs.getString("nombre"),
                                    rs.getInt("sucursal_idSucursal")),
                                        new Cuenta(
                                            rs.getInt("numero"),
                                            rs.getString("tipo"),
                                            rs.getInt("cliente_cedula"))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaSucursalCliente;
    }

    /* METODO PARA MOSTRAR TABLA ENTIDAD CLIENTE*/
    public void mostrarTablaCliente(){
        for(Cliente cliente: obtenerTablaCliente()){
            cliente.mostrarSelectCliente();
        }
    }

    /* METODO PARA MOSTRAR TABLA ENTIDAD SUCURSAL*/
    public void mostrarTablaSucursal(){
        for(Sucursal sucursal:obtenerTablaSucursal()){
            sucursal.mostrarSucursales();
        }
    }

    /* MOSTRAR LA REALCION ENTRE TABLAS SUCURSAL - CLIENTE */
    public void mostrarRelacionSucursalCliente(){
        for(Sucursal sucursal: getRelacionSucursalCliente()){
            sucursal.mostrarSucursalCliente();
        }
    }

    /* MOSTRAR LA REALCION ENTRE TABLAS SUCURSAL - CLIENTE */
    public void mostrarRelacionSucursalClienteCuenta(){
        for(Sucursal sucursal: getRelacionSucursalClienteCuenta()){
            sucursal.mostrarSucursalClienteCuenta();
        }
    }

}
