package servidor.conexion;

import servidor.entidad.Cliente;

public class Main {

    public static void main(String[] args) {
        ConexionSql conexionSql = new ConexionSql();
        Dao dao = new Dao();
        dao.mostrar();



    }

}
