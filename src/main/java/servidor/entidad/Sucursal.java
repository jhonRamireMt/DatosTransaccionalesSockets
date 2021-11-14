package servidor.entidad;

/**
 * ENTIDAD SUCURSAL
 * SE REALIZA SOBRECARGA DE CONSTRUCTORES PARA MAPEAR LAS RELACIONES DE LAS ENTIDADES ORIENTADO A OBJETOS
 */
public class Sucursal {
    private int idSucursal;
    private String pais;
    private String ciudad;
    private Cliente cliente;
    private Cuenta cuenta;

    public Sucursal(int idSucursal, String pais, String ciudad, Cliente cliente, Cuenta cuenta) {
        this.idSucursal = idSucursal;
        this.pais = pais;
        this.ciudad = ciudad;
        this.cliente = cliente;
        this.cuenta = cuenta;
    }

    public Sucursal(int idSucursal, String pais, String ciudad, Cliente cliente) {
        this.idSucursal = idSucursal;
        this.pais = pais;
        this.ciudad = ciudad;
        this.cliente = cliente;
    }

    public Sucursal(int idSucursal, String pais, String ciudad) {
        this.idSucursal = idSucursal;
        this.pais = pais;
        this.ciudad = ciudad;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void mostrarSucursales(){
        System.out.println("id: "+idSucursal+" pais: "+pais+" ciudad: "+ciudad);
    }

    public void mostrarSucursalCliente(){
        System.out.println("id: "+idSucursal+", pais: "+pais+", ciudad: "+ciudad+", cedula: "
                + cliente.getCedula() + ", nombre Cliente: " + cliente.getNombre());
    }

    public void mostrarSucursalClienteCuenta(){
        System.out.println("Id: "+idSucursal+", Pais: "+pais+", Ciudad: "+ciudad+", Cedula: "
                + cliente.getCedula() + ", Nombre Cliente: " + cliente.getNombre()
                + " Numero de Cuenta: " + cuenta.getNumero() + ", Tipo de cuenta: " + cuenta.getTipo());
    }


}
