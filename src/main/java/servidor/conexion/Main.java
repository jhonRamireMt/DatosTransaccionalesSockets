package servidor.conexion;


import java.io.IOException;

/**
 * ESTA CLASE IMPLEMENTARA EL METODO MAIN
 * SE LLAMA A LA CLASE QUE IMPLEMENTA EL SOCKET SERVER Y
 * SE LLAMARA AL HILO METODO Run()
 */
public class Main {

    public static void main(String[] args) throws IOException {

         // CORRER EL SOCKET SERVER
        ConexionSocket conexionSocket = new ConexionSocket();
        conexionSocket.run();






//        Presentado por:
//        Jhon Alexander Ramírez Bermeo, Código: 100234054
//        Julián Rojas Bustamante, Código: 2011025228
//        Carlos Andrés Castillo Pájaro, Código: 100238069
//        Diana Carolina Tovar Hernández, Código: 100235804
//        Héctor Enrique Cabra Donoso, Código 100196744


    }
}
