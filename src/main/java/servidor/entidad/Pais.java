package servidor.entidad;

public class Pais {
    private String nombre_pais;
    private Ciudad ciudad;
    private Cuenta cuenta;
    private Cliente cliente;
    private Movimiento movimiento;

    public Pais(String nombre_pais, Ciudad ciudad) {
        this.nombre_pais = nombre_pais;
        this.ciudad = ciudad;
    }

    public Pais(String nombre_pais, Ciudad ciudad, Cuenta cuenta) {
        this.nombre_pais = nombre_pais;
        this.ciudad = ciudad;
        this.cuenta = cuenta;
    }

    public Pais(String nombre_pais, Ciudad ciudad, Cuenta cuenta, Cliente cliente) {
        this.nombre_pais = nombre_pais;
        this.ciudad = ciudad;
        this.cuenta = cuenta;
        this.cliente = cliente;
    }

    public Pais(String nombre_pais, Ciudad ciudad, Cuenta cuenta, Cliente cliente, Movimiento movimiento) {
        this.nombre_pais = nombre_pais;
        this.ciudad = ciudad;
        this.cuenta = cuenta;
        this.cliente = cliente;
        this.movimiento = movimiento;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public void mostrarDatosClientes(){
        System.out.println("Pais: "+nombre_pais );
        System.out.println("No Cuenta: " +cuenta.getNumero_cuenta());
        System.out.println("Saldo :" + cuenta.getSaldo() );
        System.out.println("Fecha creacion de la Cuenta:" + cuenta.getFecha_creado());
        System.out.println("Tipo de Cuenta: " + cuenta.getTipo_cuenta());
        System.out.println("Ciudad: "+ciudad.getNombre_ciudad());
        System.out.println("No Cedula: " + cliente.getNumero_documento());
        System.out.println("Nombre Cliente: " + cliente.getNombres()+" "+cliente.getApellidos());
        System.out.println("Fecha Creacion del cliente: "+cliente.getFecha_creado());
        System.out.println("Telefono: "+cliente.getNumero_telefono());
        System.out.println("Clave: ****");
    }
}
