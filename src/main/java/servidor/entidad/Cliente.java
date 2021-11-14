package servidor.entidad;

public class Cliente {
    private int cedula;
    private String nombre;
    private int sucursal_idSucursal;

    public Cliente(int cedula, String nombre, int sucursal_idSucursal) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sucursal_idSucursal = sucursal_idSucursal;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSucursal_idSucursal() {
        return sucursal_idSucursal;
    }

    public void setSucursal_idSucursal(int sucursal_idSucursal) {
        this.sucursal_idSucursal = sucursal_idSucursal;
    }

    public void mostrarSelectCliente(){
        System.out.println("nombre: " + nombre + " cedula: "+cedula+" sucursal: "+sucursal_idSucursal);
    }
}
