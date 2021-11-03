package servidor.entidad;

public class Cliente {
    private int idCliente;
    private String nombre;
    private int cedula;
    private String ciudad;
    private String pais;
    private String movimiento;
    private Double saldo;

    public Cliente(int idCliente, String nombre, int cedula, String ciudad, String pais, String movimiento, Double saldo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.cedula = cedula;
        this.ciudad = ciudad;
        this.pais = pais;
        this.movimiento = movimiento;
        this.saldo = saldo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void mostrarClientes(){
        System.out.println("idCliente "+idCliente);
        System.out.println("nombre "+nombre);
        System.out.println("cedula "+cedula);
        System.out.println("ciudad "+ciudad);
        System.out.println("pais "+pais);
        System.out.println("movimeinto "+movimiento);
        System.out.println("saldo "+saldo);
    }
}
