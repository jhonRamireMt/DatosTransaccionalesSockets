package servidor.conexion;

import servidor.entidad.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private ConexionSql conexionSql = new ConexionSql();
    private Connection con = conexionSql.getConexion();
    private PreparedStatement prep;

    public List<Cliente> obtenerBD(){
        List<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT * FROM clientes;";
        try {
            prep = con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                lista.add(new Cliente(rs.getInt("idCliente"),rs.getString("nombre"),
                        rs.getInt("cedula"),rs.getString("ciudad"),
                        rs.getString("pais"),rs.getString("movimiento"),rs.getDouble("saldo")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public void mostrar(){
        for(Cliente cliente: obtenerBD()){
            cliente.mostrarClientes();
        }
    }

}
