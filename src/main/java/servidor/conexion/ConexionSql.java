package servidor.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ESTA CLASE REALIZARA LA CONEXION A BASE DE DATOS
 * SE UTILIZARA LA DEPENDENCIA DE MYSQL PARA REALIZAR LA CONEXION
 */
public class ConexionSql {

    private String bd = "cajero";
    private String usuario = "root";
    private String password = "Asdk2021**@@";
    private String url = "jdbc:mysql://localhost:3306/"+bd;
    private Connection conexion = null;

    /* Este constructor inicializa los datos de la conexion a la BD*/
    public ConexionSql(){
        try {
           //Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,password);
            System.out.println("conexion exitosa");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*ESTE METODO RETORNA LA CONEXION REALIZADA EN EL CONSTRUCTOR*/
    public Connection getConexion(){ //retorna el objeto de la conexion
        return conexion;
    }
}
